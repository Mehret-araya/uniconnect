package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Event
import com.example.data.model.EventCategory
import com.example.ui.localization.AppLanguage
import com.example.ui.localization.AppStrings
import com.example.ui.theme.CategoryAcademic
import com.example.ui.theme.CategoryClub
import com.example.ui.theme.CategoryCultural
import com.example.ui.theme.CategorySocial
import com.example.ui.theme.CategorySports

@Composable
fun EventCard(
  event: Event,
  language: AppLanguage,
  strings: AppStrings,
  onEventClick: () -> Unit,
  onToggleRsvp: () -> Unit,
  onToggleBookmark: () -> Unit,
  modifier: Modifier = Modifier
) {
  val title = if (language == AppLanguage.AMHARIC && !event.titleAm.isNullOrBlank()) event.titleAm else event.title
  val desc = if (language == AppLanguage.AMHARIC && !event.descriptionAm.isNullOrBlank()) event.descriptionAm else event.description
  val categoryLabel = if (language == AppLanguage.AMHARIC) event.category.labelAm else event.category.labelEn

  val categoryColor = when (event.category) {
    EventCategory.ACADEMIC -> CategoryAcademic
    EventCategory.SPORTS -> CategorySports
    EventCategory.CULTURAL -> CategoryCultural
    EventCategory.SOCIAL -> CategorySocial
    EventCategory.CLUB -> CategoryClub
  }

  Card(
    modifier = modifier
      .fillMaxWidth()
      .clickable(onClick = onEventClick)
      .testTag("event_card_${event.id}"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      // Category badge + Bookmark button
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .background(categoryColor.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
          Text(
            text = categoryLabel,
            color = categoryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
          )
        }

        IconButton(
          onClick = onToggleBookmark,
          modifier = Modifier.testTag("bookmark_button_${event.id}")
        ) {
          Icon(
            imageVector = if (event.isBookmarked) Icons.Default.Bookmark else Icons.Outlined.BookmarkBorder,
            contentDescription = if (event.isBookmarked) strings.bookmarked else strings.bookmark,
            tint = if (event.isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Title
      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(6.dp))

      // Description excerpt
      Text(
        text = desc,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
      )

      Spacer(modifier = Modifier.height(12.dp))

      // Date and Location details
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
      ) {
        Icon(
          imageVector = Icons.Default.CalendarToday,
          contentDescription = strings.date,
          modifier = Modifier.size(16.dp),
          tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "${event.date} • ${event.time}",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
      ) {
        Icon(
          imageVector = Icons.Default.LocationOn,
          contentDescription = strings.location,
          modifier = Modifier.size(16.dp),
          tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = event.location,
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Action row: Attendee count + RSVP button
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.People,
            contentDescription = strings.attending,
            modifier = Modifier.size(18.dp),
            tint = MaterialTheme.colorScheme.primary
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "${event.attendeeCount} ${strings.attendingCount}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
          )
        }

        if (event.isAttending) {
          Button(
            onClick = onToggleRsvp,
            colors = ButtonDefaults.buttonColors(
              containerColor = MaterialTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.testTag("rsvp_button_${event.id}")
          ) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = null,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = strings.attending, fontSize = 12.sp)
          }
        } else {
          OutlinedButton(
            onClick = onToggleRsvp,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.testTag("rsvp_button_${event.id}")
          ) {
            Text(text = strings.attend, fontSize = 12.sp)
          }
        }
      }
    }
  }
}
