package com.example.ui.screens.events

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EventBusy
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Event
import com.example.data.model.EventCategory
import com.example.ui.components.EventCard
import com.example.ui.localization.AppLanguage
import com.example.ui.localization.AppStrings

@Composable
fun EventsScreen(
  events: List<Event>,
  language: AppLanguage,
  strings: AppStrings,
  onEventClick: (String) -> Unit,
  onToggleRsvp: (String) -> Unit,
  onToggleBookmark: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedCategory by remember { mutableStateOf<EventCategory?>(null) }

  val filteredEvents = events.filter { evt ->
    val matchesCategory = selectedCategory == null || evt.category == selectedCategory
    val matchesSearch = searchQuery.isBlank() ||
      evt.title.contains(searchQuery, ignoreCase = true) ||
      (evt.titleAm?.contains(searchQuery, ignoreCase = true) == true) ||
      evt.location.contains(searchQuery, ignoreCase = true) ||
      evt.organizer.contains(searchQuery, ignoreCase = true)
    matchesCategory && matchesSearch
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("events_screen")
  ) {
    // Search bar
    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      placeholder = { Text(strings.searchPlaceholder, fontSize = 14.sp) },
      leadingIcon = {
        Icon(Icons.Default.Search, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
      },
      singleLine = true,
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .testTag("events_search_input"),
      shape = RoundedCornerShape(12.dp)
    )

    // Category Filter Chips (All, Academic, Sports, Cultural, Social, Club)
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState())
        .padding(horizontal = 16.dp, vertical = 4.dp),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      FilterChip(
        selected = selectedCategory == null,
        onClick = { selectedCategory = null },
        label = { Text(strings.allCategories) },
        colors = FilterChipDefaults.filterChipColors(
          selectedContainerColor = MaterialTheme.colorScheme.primary,
          selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier.testTag("filter_all")
      )

      EventCategory.entries.forEach { cat ->
        val label = if (language == AppLanguage.AMHARIC) cat.labelAm else cat.labelEn
        FilterChip(
          selected = selectedCategory == cat,
          onClick = { selectedCategory = if (selectedCategory == cat) null else cat },
          label = { Text(label) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
          ),
          modifier = Modifier.testTag("filter_${cat.name.lowercase()}")
        )
      }
    }

    Spacer(modifier = Modifier.height(4.dp))

    if (filteredEvents.isEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(32.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Icon(
            imageVector = Icons.Default.EventBusy,
            contentDescription = null,
            modifier = Modifier.size(56.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
          )
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = strings.emptyEvents,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium
          )
        }
      }
    } else {
      LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        items(filteredEvents) { evt ->
          EventCard(
            event = evt,
            language = language,
            strings = strings,
            onEventClick = { onEventClick(evt.id) },
            onToggleRsvp = { onToggleRsvp(evt.id) },
            onToggleBookmark = { onToggleBookmark(evt.id) }
          )
        }
      }
    }
  }
}
