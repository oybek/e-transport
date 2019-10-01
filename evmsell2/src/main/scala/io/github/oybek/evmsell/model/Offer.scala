package io.github.oybek.evmsell.model

case class Offer(id: Long,
                 fromId: Long,
                 date: Long,
                 ttype: Option[String],
                 text: String,
                 price: Option[Long],
                 latitude: Option[Float] = None,
                 longitude: Option[Float] = None,
                 sold: Boolean = false)
