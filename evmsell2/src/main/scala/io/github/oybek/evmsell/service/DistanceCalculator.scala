package io.github.oybek.evmsell.service

import io.github.oybek.evmsell.vk.Coord

trait DistanceCalcular {
  def calculateDistanceInKilometer(p1: Coord, p2: Coord): Long
}

class DistanceCalculatorImpl extends DistanceCalcular {
  private val AVERAGE_RADIUS_OF_EARTH_KM = 6371

  override def calculateDistanceInKilometer(p1: Coord, p2: Coord): Long = {
    val latDistance = Math.toRadians(p1.latitude - p2.latitude)
    val lngDistance = Math.toRadians(p1.longitude - p2.longitude)
    val sinLat = Math.sin(latDistance / 2)
    val sinLng = Math.sin(lngDistance / 2)
    val a = sinLat * sinLat +
      (Math.cos(Math.toRadians(p1.latitude)) *
        Math.cos(Math.toRadians(p2.latitude)) *
        sinLng * sinLng)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    (AVERAGE_RADIUS_OF_EARTH_KM * c).toInt
  }
}
