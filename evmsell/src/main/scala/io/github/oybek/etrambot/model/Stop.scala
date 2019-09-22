package io.github.oybek.etrambot.model

case class Stop(id: String,
                names: List[String],
                direction: String,
                latitude: Float,
                longitude: Float,
                typ: String)
