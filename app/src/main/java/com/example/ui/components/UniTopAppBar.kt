package com.example.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import com.example.ui.localization.AppLanguage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniTopAppBar(
  title: String,
  currentLanguage: AppLanguage,
  canNavigateBack: Boolean = false,
  onNavigateBack: () -> Unit = {},
  onToggleLanguage: () -> Unit = {},
  onOpenBookmarks: (() -> Unit)? = null,
  onOpenSettings: (() -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  TopAppBar(
    modifier = modifier.testTag("uni_top_app_bar"),
    title = {
      Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
      )
    },
    navigationIcon = {
      if (canNavigateBack) {
        IconButton(
          onClick = onNavigateBack,
          modifier = Modifier.testTag("nav_back_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back"
          )
        }
      }
    },
    actions = {
      // Language switch button (English <-> Amharic)
      IconButton(
        onClick = onToggleLanguage,
        modifier = Modifier.testTag("language_toggle_button")
      ) {
        Icon(
          imageVector = Icons.Default.Language,
          contentDescription = "Toggle Language (${currentLanguage.displayName})",
          tint = MaterialTheme.colorScheme.primary
        )
      }

      onOpenBookmarks?.let {
        IconButton(
          onClick = it,
          modifier = Modifier.testTag("top_bar_bookmarks_button")
        ) {
          Icon(
            imageVector = Icons.Default.Bookmark,
            contentDescription = "Bookmarks"
          )
        }
      }

      onOpenSettings?.let {
        IconButton(
          onClick = it,
          modifier = Modifier.testTag("top_bar_settings_button")
        ) {
          Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings"
          )
        }
      }
    },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.surface,
      titleContentColor = MaterialTheme.colorScheme.onSurface,
      actionIconContentColor = MaterialTheme.colorScheme.onSurface
    )
  )
}
