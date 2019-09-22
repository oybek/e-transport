
import fs2.Stream

val s0 = Stream.empty

val s1 = Stream.emit(1)

import cats.effect.IO

val eff = Stream.eval(IO { println("BEING RUN!!"); 1+1 })

