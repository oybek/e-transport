package io.github.oybek.etrambot.repo

import io.github.oybek.etrambot.model.Stop
import fs2.Stream

trait Repo[F[_]] {
  def findAll: Stream[F, Stop]
}
