package com.example.data.model

data class Club(
  val id: String,
  val name: String,
  val nameAm: String? = null,
  val category: String,
  val description: String,
  val descriptionAm: String? = null,
  val memberCount: Int = 0,
  val isJoined: Boolean = false,
  val adminName: String,
  val meetingSchedule: String,
  val roomLocation: String,
  val members: List<String> = emptyList(),
  val iconEmoji: String = "🎓"
)

data class Announcement(
  val id: String,
  val title: String,
  val titleAm: String? = null,
  val content: String,
  val contentAm: String? = null,
  val date: String,
  val department: String,
  val isUrgent: Boolean = false
)
