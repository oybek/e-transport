package io.github.oybek.geekbear.model

import io.github.oybek.geekbear.vk.Coord

case class Offer(id: Long,
                 groupId: Long,
                 fromId: Long,
                 date: Long,
                 ttype: Option[String],
                 text: String,
                 price: Option[Long],
                 city: Option[Int] = None,
                 sold: Option[Long] = None)
