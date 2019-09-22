package io.github.oybek.etrambot

import cats.Monad
import cats.effect.{Concurrent, IO, Sync}
import io.github.oybek.etrambot.repo.{DoobieRepo, Repo}
import monix.eval.Task
import telegramium.bots.client.Api
import fs2.Stream

import scala.concurrent.duration.Duration

class TramBot[F[_]](bot: Api[F], repo: Repo[F])(implicit syncF: Sync[F]) extends LongPollGate[F](bot) {

  import cats.syntax.functor._
  import telegramium.bots._
  import telegramium.bots.client._

  override def onMessage(msg: Message): F[Unit] = {
    repo.findAll.take(3).compile.toVector.map { stop =>
      bot.sendMessage(SendMessageReq(
        chatId = ChatIntId(msg.chat.id),
        text = stop.toString,
      )).void
    }
  }

  override def onInlineQuery(query: InlineQuery): F[Unit] = {
    bot.answerInlineQuery(
      AnswerInlineQueryReq(
        inlineQueryId = query.id,
        results = query.query.split(" ").zipWithIndex.map{ case (word, idx) =>
          InlineQueryResultArticle(
            id = idx.toString,
            title = word,
            inputMessageContent = InputTextMessageContent(messageText = word),
          )
        }.toList
      )
    ).void
  }

  override def onChosenInlineResult(inlineResult: ChosenInlineResult): F[Unit] = {
    import telegramium.bots.CirceImplicits._
    import io.circe.syntax._
    syncF.delay {
      println(inlineResult.asJson.spaces4)
    }
  }

}
