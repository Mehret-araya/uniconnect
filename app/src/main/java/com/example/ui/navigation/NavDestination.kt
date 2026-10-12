package com.example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
  data object Splash : Screen("splash")
  data object SignIn : Screen("sign_in")
  data object SignUp : Screen("sign_up")
  data object ForgotPassword : Screen("forgot_password")
  data object Main : Screen("main")
  data object EventDetail : Screen("event_detail/{eventId}") {
    fun createRoute(eventId: String) = "event_detail/$eventId"
  }
  data object ClubDetail : Screen("club_detail/{clubId}") {
    fun createRoute(clubId: String) = "club_detail/$clubId"
  }
  data object EditProfile : Screen("edit_profile")
  data object Bookmarks : Screen("bookmarks")
  data object Settings : Screen("settings")
}

enum class BottomTab(
  val route: String,
  val titleEn: String,
  val titleAm: String,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector
) {
  HOME("tab_home", "Home", "መነሻ", Icons.Filled.Home, Icons.Outlined.Home),
  EVENTS("tab_events", "Events", "ክንውኖች", Icons.Filled.DateRange, Icons.Outlined.DateRange),
  CLUBS("tab_clubs", "Clubs", "ክለቦች", Icons.Filled.Groups, Icons.Outlined.Groups),
  CHATS("tab_chats", "Chats", "መልዕክቶች", Icons.Filled.ChatBubble, Icons.Outlined.ChatBubbleOutline),
  PROFILE("tab_profile", "Profile", "መገለጫ", Icons.Filled.Person, Icons.Outlined.Person)
}
