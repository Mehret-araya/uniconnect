package com.example.data.model

data class User(
  val id: String,
  val name: String,
  val email: String,
  val university: String,
  val department: String,
  val yearOfStudy: String,
  val photoUrl: String? = null,
  val bio: String = "",
  val clubs: List<String> = emptyList(),
  val rsvpEvents: List<String> = emptyList(),
  val bookmarkedEvents: List<String> = emptyList()
)
