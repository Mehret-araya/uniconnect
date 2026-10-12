package com.example.data.model

enum class EventCategory(val labelEn: String, val labelAm: String) {
  ACADEMIC("Academic", "ትምህርታዊ"),
  SPORTS("Sports", "ስፖርት"),
  CULTURAL("Cultural", "ባህላዊ"),
  SOCIAL("Social", "ማህበራዊ"),
  CLUB("Club", "የክለብ")
}

data class Event(
  val id: String,
  val title: String,
  val titleAm: String? = null,
  val description: String,
  val descriptionAm: String? = null,
  val date: String,
  val time: String,
  val location: String,
  val category: EventCategory,
  val organizer: String,
  val clubId: String? = null,
  val attendeeCount: Int = 0,
  val isAttending: Boolean = false,
  val isBookmarked: Boolean = false,
  val bannerUrl: String? = null
)
