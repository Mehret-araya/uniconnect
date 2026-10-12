package com.example.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.data.repository.CampusRepository
import com.example.ui.localization.AppLanguage
import com.example.ui.localization.getStrings
import com.example.ui.screens.auth.ForgotPasswordScreen
import com.example.ui.screens.auth.SignInScreen
import com.example.ui.screens.auth.SignUpScreen
import com.example.ui.screens.bookmarks.BookmarksScreen
import com.example.ui.screens.clubs.ClubDetailScreen
import com.example.ui.screens.events.EventDetailScreen
import com.example.ui.screens.main.MainScreen
import com.example.ui.screens.profile.EditProfileScreen
import com.example.ui.screens.settings.SettingsScreen
import com.example.ui.screens.splash.SplashScreen
import com.example.ui.theme.MyApplicationTheme

sealed class AppDestination {
  data object Splash : AppDestination()
  data object SignIn : AppDestination()
  data object SignUp : AppDestination()
  data object ForgotPassword : AppDestination()
  data object Main : AppDestination()
  data class EventDetail(val eventId: String) : AppDestination()
  data class ClubDetail(val clubId: String) : AppDestination()
  data object EditProfile : AppDestination()
  data object Bookmarks : AppDestination()
  data object Settings : AppDestination()
}

@Composable
fun UniConnectApp(
  repository: CampusRepository = remember { CampusRepository.instance }
) {
  val systemDark = isSystemInDarkTheme()
  var isDarkTheme by remember { mutableStateOf(systemDark) }
  var currentLanguage by remember { mutableStateOf(AppLanguage.ENGLISH) }
  val strings = getStrings(currentLanguage)

  // Navigation Stack State
  var currentDestination by remember { mutableStateOf<AppDestination>(AppDestination.Splash) }
  var previousDestination by remember { mutableStateOf<AppDestination?>(null) }
  var currentBottomTab by remember { mutableStateOf(BottomTab.HOME) }

  // Observables from Repository
  val currentUser by repository.currentUser.collectAsState()
  val events by repository.events.collectAsState()
  val clubs by repository.clubs.collectAsState()
  val announcements by repository.announcements.collectAsState()

  fun navigateTo(destination: AppDestination) {
    previousDestination = currentDestination
    currentDestination = destination
  }

  fun navigateBack() {
    currentDestination = when (currentDestination) {
      is AppDestination.EventDetail,
      is AppDestination.ClubDetail,
      AppDestination.EditProfile,
      AppDestination.Bookmarks,
      AppDestination.Settings -> AppDestination.Main

      AppDestination.SignUp,
      AppDestination.ForgotPassword -> AppDestination.SignIn

      else -> AppDestination.Main
    }
  }

  fun toggleLanguage() {
    currentLanguage = if (currentLanguage == AppLanguage.ENGLISH) AppLanguage.AMHARIC else AppLanguage.ENGLISH
  }

  MyApplicationTheme(darkTheme = isDarkTheme) {
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .testTag("app_root_surface"),
      color = MaterialTheme.colorScheme.background
    ) {
      when (val dest = currentDestination) {
        AppDestination.Splash -> {
          SplashScreen(
            currentLanguage = currentLanguage,
            onNavigateNext = {
              if (currentUser != null) {
                currentDestination = AppDestination.Main
              } else {
                currentDestination = AppDestination.SignIn
              }
            }
          )
        }

        AppDestination.SignIn -> {
          SignInScreen(
            currentLanguage = currentLanguage,
            onToggleLanguage = { toggleLanguage() },
            onSignInSuccess = { currentDestination = AppDestination.Main },
            onNavigateToSignUp = { navigateTo(AppDestination.SignUp) },
            onNavigateToForgotPassword = { navigateTo(AppDestination.ForgotPassword) },
            onSignIn = { email, pass -> repository.signIn(email, pass) }
          )
        }

        AppDestination.SignUp -> {
          BackHandler { navigateBack() }
          SignUpScreen(
            currentLanguage = currentLanguage,
            onSignUpSuccess = { currentDestination = AppDestination.Main },
            onNavigateToSignIn = { navigateBack() },
            onSignUp = { name, email, uni, dept, yr ->
              repository.signUp(name, email, uni, dept, yr)
            }
          )
        }

        AppDestination.ForgotPassword -> {
          BackHandler { navigateBack() }
          ForgotPasswordScreen(
            currentLanguage = currentLanguage,
            onNavigateBack = { navigateBack() }
          )
        }

        AppDestination.Main -> {
          MainScreen(
            currentTab = currentBottomTab,
            onTabSelected = { currentBottomTab = it },
            user = currentUser,
            events = events,
            clubs = clubs,
            announcements = announcements,
            currentLanguage = currentLanguage,
            strings = strings,
            onToggleLanguage = { toggleLanguage() },
            onNavigateToBookmarks = { navigateTo(AppDestination.Bookmarks) },
            onNavigateToSettings = { navigateTo(AppDestination.Settings) },
            onNavigateToEventDetail = { eventId -> navigateTo(AppDestination.EventDetail(eventId)) },
            onNavigateToClubDetail = { clubId -> navigateTo(AppDestination.ClubDetail(clubId)) },
            onNavigateToEditProfile = { navigateTo(AppDestination.EditProfile) },
            onToggleRsvp = { eventId -> repository.toggleRsvp(eventId) },
            onToggleBookmark = { eventId -> repository.toggleBookmark(eventId) },
            onToggleJoinClub = { clubId -> repository.toggleJoinClub(clubId) },
            onSignOut = {
              repository.signOut()
              currentDestination = AppDestination.SignIn
            }
          )
        }

        is AppDestination.EventDetail -> {
          BackHandler { navigateBack() }
          val event = events.find { it.id == dest.eventId }
          EventDetailScreen(
            event = event,
            language = currentLanguage,
            strings = strings,
            onNavigateBack = { navigateBack() },
            onToggleRsvp = { repository.toggleRsvp(dest.eventId) },
            onToggleBookmark = { repository.toggleBookmark(dest.eventId) }
          )
        }

        is AppDestination.ClubDetail -> {
          BackHandler { navigateBack() }
          val club = clubs.find { it.id == dest.clubId }
          ClubDetailScreen(
            club = club,
            language = currentLanguage,
            strings = strings,
            onNavigateBack = { navigateBack() },
            onToggleJoin = { repository.toggleJoinClub(dest.clubId) }
          )
        }

        AppDestination.EditProfile -> {
          BackHandler { navigateBack() }
          EditProfileScreen(
            user = currentUser,
            language = currentLanguage,
            strings = strings,
            onNavigateBack = { navigateBack() },
            onSaveProfile = { name, dept, yr, bio ->
              repository.updateProfile(name, dept, yr, bio)
            }
          )
        }

        AppDestination.Bookmarks -> {
          BackHandler { navigateBack() }
          BookmarksScreen(
            events = events,
            language = currentLanguage,
            strings = strings,
            onNavigateBack = { navigateBack() },
            onEventClick = { eventId -> navigateTo(AppDestination.EventDetail(eventId)) },
            onToggleRsvp = { eventId -> repository.toggleRsvp(eventId) },
            onToggleBookmark = { eventId -> repository.toggleBookmark(eventId) }
          )
        }

        AppDestination.Settings -> {
          BackHandler { navigateBack() }
          SettingsScreen(
            isDarkTheme = isDarkTheme,
            onToggleDarkTheme = { isDarkTheme = it },
            currentLanguage = currentLanguage,
            onChangeLanguage = { currentLanguage = it },
            strings = strings,
            onNavigateBack = { navigateBack() }
          )
        }
      }
    }
  }
}
