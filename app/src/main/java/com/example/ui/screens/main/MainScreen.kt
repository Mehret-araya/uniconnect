package com.example.ui.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.data.model.Announcement
import com.example.data.model.Club
import com.example.data.model.Event
import com.example.data.model.User
import com.example.ui.components.UniBottomNavBar
import com.example.ui.components.UniTopAppBar
import com.example.ui.localization.AppLanguage
import com.example.ui.localization.AppStrings
import com.example.ui.navigation.BottomTab
import com.example.ui.screens.chats.ChatsPlaceholderScreen
import com.example.ui.screens.clubs.ClubsScreen
import com.example.ui.screens.events.EventsScreen
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.profile.ProfileScreen

@Composable
fun MainScreen(
  currentTab: BottomTab,
  onTabSelected: (BottomTab) -> Unit,
  user: User?,
  events: List<Event>,
  clubs: List<Club>,
  announcements: List<Announcement>,
  currentLanguage: AppLanguage,
  strings: AppStrings,
  onToggleLanguage: () -> Unit,
  onNavigateToBookmarks: () -> Unit,
  onNavigateToSettings: () -> Unit,
  onNavigateToEventDetail: (String) -> Unit,
  onNavigateToClubDetail: (String) -> Unit,
  onNavigateToEditProfile: () -> Unit,
  onToggleRsvp: (String) -> Unit,
  onToggleBookmark: (String) -> Unit,
  onToggleJoinClub: (String) -> Unit,
  onSignOut: () -> Unit,
  modifier: Modifier = Modifier
) {
  val title = when (currentTab) {
    BottomTab.HOME -> strings.appName
    BottomTab.EVENTS -> strings.eventsTitle
    BottomTab.CLUBS -> strings.clubsTitle
    BottomTab.CHATS -> strings.navChats
    BottomTab.PROFILE -> strings.profileTitle
  }

  Scaffold(
    topBar = {
      UniTopAppBar(
        title = title,
        currentLanguage = currentLanguage,
        canNavigateBack = false,
        onToggleLanguage = onToggleLanguage,
        onOpenBookmarks = onNavigateToBookmarks,
        onOpenSettings = onNavigateToSettings
      )
    },
    bottomBar = {
      UniBottomNavBar(
        currentTab = currentTab,
        onTabSelected = onTabSelected,
        currentLanguage = currentLanguage
      )
    },
    modifier = modifier
      .fillMaxSize()
      .testTag("main_screen")
  ) { innerPadding ->
    val contentModifier = Modifier.padding(innerPadding)

    when (currentTab) {
      BottomTab.HOME -> {
        HomeScreen(
          user = user,
          events = events,
          clubs = clubs,
          announcements = announcements,
          language = currentLanguage,
          strings = strings,
          onNavigateToEvents = { onTabSelected(BottomTab.EVENTS) },
          onNavigateToClubs = { onTabSelected(BottomTab.CLUBS) },
          onEventClick = onNavigateToEventDetail,
          onClubClick = onNavigateToClubDetail,
          onToggleRsvp = onToggleRsvp,
          onToggleBookmark = onToggleBookmark,
          onToggleJoinClub = onToggleJoinClub,
          modifier = contentModifier
        )
      }
      BottomTab.EVENTS -> {
        EventsScreen(
          events = events,
          language = currentLanguage,
          strings = strings,
          onEventClick = onNavigateToEventDetail,
          onToggleRsvp = onToggleRsvp,
          onToggleBookmark = onToggleBookmark,
          modifier = contentModifier
        )
      }
      BottomTab.CLUBS -> {
        ClubsScreen(
          clubs = clubs,
          language = currentLanguage,
          strings = strings,
          onClubClick = onNavigateToClubDetail,
          onToggleJoin = onToggleJoinClub,
          modifier = contentModifier
        )
      }
      BottomTab.CHATS -> {
        ChatsPlaceholderScreen(
          language = currentLanguage,
          strings = strings,
          modifier = contentModifier
        )
      }
      BottomTab.PROFILE -> {
        ProfileScreen(
          user = user,
          events = events,
          clubs = clubs,
          language = currentLanguage,
          strings = strings,
          onNavigateToEditProfile = onNavigateToEditProfile,
          onNavigateToBookmarks = onNavigateToBookmarks,
          onEventClick = onNavigateToEventDetail,
          onClubClick = onNavigateToClubDetail,
          onSignOut = onSignOut,
          modifier = contentModifier
        )
      }
    }
  }
}
