package com.example.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Announcement
import com.example.data.model.Club
import com.example.data.model.Event
import com.example.data.model.User
import com.example.ui.components.ClubCard
import com.example.ui.components.EventCard
import com.example.ui.localization.AppLanguage
import com.example.ui.localization.AppStrings

@Composable
fun HomeScreen(
  user: User?,
  events: List<Event>,
  clubs: List<Club>,
  announcements: List<Announcement>,
  language: AppLanguage,
  strings: AppStrings,
  onNavigateToEvents: () -> Unit,
  onNavigateToClubs: () -> Unit,
  onEventClick: (String) -> Unit,
  onClubClick: (String) -> Unit,
  onToggleRsvp: (String) -> Unit,
  onToggleBookmark: (String) -> Unit,
  onToggleJoinClub: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("home_screen"),
    contentPadding = PaddingValues(bottom = 24.dp)
  ) {
    // 1. Hero Campus Banner
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.primary
              )
            )
          )
          .padding(20.dp)
          .testTag("home_hero_banner")
      ) {
        Column {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "${strings.welcomeBack},",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f)
              )
              Text(
                text = user?.name ?: "Student",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }

            Box(
              modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.2f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.School,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "${user?.department ?: "Technology"} • ${user?.university ?: "Addis Ababa University"}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.85f)
          )

          Spacer(modifier = Modifier.height(16.dp))

          // Quick Campus Activity Pills
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            QuickStatBadge(
              icon = Icons.Default.Event,
              count = user?.rsvpEvents?.size ?: 0,
              label = strings.attending,
              modifier = Modifier.weight(1f)
            )
            QuickStatBadge(
              icon = Icons.Default.Groups,
              count = user?.clubs?.size ?: 0,
              label = strings.myClubs,
              modifier = Modifier.weight(1f)
            )
          }
        }
      }
    }

    // 2. Announcements Section
    item {
      Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Campaign,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(22.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = strings.campusAnnouncements,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        announcements.forEach { ann ->
          AnnouncementCard(announcement = ann, language = language)
          Spacer(modifier = Modifier.height(8.dp))
        }
      }
    }

    // 3. Upcoming Events Header & Horizontal Cards
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = strings.upcomingEvents,
          style = MaterialTheme.typography.titleLarge,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onBackground
        )

        TextButton(
          onClick = onNavigateToEvents,
          modifier = Modifier.testTag("home_view_all_events")
        ) {
          Text(text = strings.viewAll, fontWeight = FontWeight.SemiBold)
          Spacer(modifier = Modifier.width(4.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }

    item {
      LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        items(events) { evt ->
          Box(modifier = Modifier.width(310.dp)) {
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

    // 4. Featured Clubs Header & List
    item {
      Spacer(modifier = Modifier.height(16.dp))
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = strings.featuredClubs,
          style = MaterialTheme.typography.titleLarge,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onBackground
        )

        TextButton(
          onClick = onNavigateToClubs,
          modifier = Modifier.testTag("home_view_all_clubs")
        ) {
          Text(text = strings.viewAll, fontWeight = FontWeight.SemiBold)
          Spacer(modifier = Modifier.width(4.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }

    items(clubs.take(2)) { club ->
      Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
        ClubCard(
          club = club,
          language = language,
          strings = strings,
          onClubClick = { onClubClick(club.id) },
          onToggleJoin = { onToggleJoinClub(club.id) }
        )
      }
    }
  }
}

@Composable
fun QuickStatBadge(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  count: Int,
  label: String,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White.copy(alpha = 0.2f)
    )
  ) {
    Row(
      modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier.size(18.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Column {
        Text(
          text = count.toString(),
          color = Color.White,
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp
        )
        Text(
          text = label,
          color = Color.White.copy(alpha = 0.9f),
          fontSize = 11.sp
        )
      }
    }
  }
}

@Composable
fun AnnouncementCard(
  announcement: Announcement,
  language: AppLanguage,
  modifier: Modifier = Modifier
) {
  val title = if (language == AppLanguage.AMHARIC && !announcement.titleAm.isNullOrBlank()) announcement.titleAm else announcement.title
  val content = if (language == AppLanguage.AMHARIC && !announcement.contentAm.isNullOrBlank()) announcement.contentAm else announcement.content

  Card(
    modifier = modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = if (announcement.isUrgent) {
        MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.35f)
      } else {
        MaterialTheme.colorScheme.surfaceVariant
      }
    )
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = announcement.department,
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.primary,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = announcement.date,
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
      )

      Spacer(modifier = Modifier.height(2.dp))

      Text(
        text = content,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }
  }
}
