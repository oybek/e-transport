package io.github.oybek.catpc.model

import io.github.oybek.catpc.vk.Coord

case class Offer(id: Long,
                 groupId: Long,
                 fromId: Long,
                 date: Long,
                 ttype: Option[String],
                 text: String,
                 price: Option[Long],
                 city: Option[Int] = None,
                 sold: Option[Long] = None)
