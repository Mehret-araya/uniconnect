package com.example.ui.screens.profile

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Club
import com.example.data.model.Event
import com.example.data.model.User
import com.example.ui.localization.AppLanguage
import com.example.ui.localization.AppStrings

@Composable
fun ProfileScreen(
  user: User?,
  events: List<Event>,
  clubs: List<Club>,
  language: AppLanguage,
  strings: AppStrings,
  onNavigateToEditProfile: () -> Unit,
  onNavigateToBookmarks: () -> Unit,
  onEventClick: (String) -> Unit,
  onClubClick: (String) -> Unit,
  onSignOut: () -> Unit,
  modifier: Modifier = Modifier
) {
  val myRsvps = events.filter { it.isAttending }
  val myClubs = clubs.filter { it.isJoined }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .testTag("profile_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Digital Student ID Card
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("student_id_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "STUDENT IDENTITY CARD",
              style = MaterialTheme.typography.labelSmall,
              letterSpacing = 1.5.sp,
              color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f),
              fontWeight = FontWeight.Bold
            )
            Icon(
              imageVector = Icons.Default.School,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f),
              modifier = Modifier.size(20.dp)
            )
          }

          Spacer(modifier = Modifier.height(16.dp))

          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.2f)),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = (user?.name?.take(2) ?: "ST").uppercase(),
                color = MaterialTheme.colorScheme.onSecondary,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
              )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
              Text(
                text = user?.name ?: "Student",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
              )
              Text(
                text = user?.email ?: "student@aau.edu.et",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.85f)
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column {
              Text(
                text = "CAMPUS / DEPT",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.7f)
              )
              Text(
                text = "${user?.department ?: "Technology"}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondary
              )
            }

            Column(horizontalAlignment = Alignment.End) {
              Text(
                text = "STATUS / YEAR",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.7f)
              )
              Text(
                text = user?.yearOfStudy ?: "Undergraduate",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondary
              )
            }
          }

          if (!user?.bio.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
              text = user?.bio ?: "",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.9f)
            )
          }
        }
      }
    }

    // 2. Edit Profile & Bookmarks Buttons
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Button(
          onClick = onNavigateToEditProfile,
          modifier = Modifier
            .weight(1f)
            .testTag("profile_edit_button"),
          shape = RoundedCornerShape(12.dp),
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
          Icon(Icons.Default.Edit, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text(text = strings.editProfile, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.SemiBold)
        }

        OutlinedButton(
          onClick = onNavigateToBookmarks,
          modifier = Modifier
            .weight(1f)
            .testTag("profile_bookmarks_button"),
          shape = RoundedCornerShape(12.dp)
        ) {
          Icon(Icons.Default.Bookmark, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text(text = strings.savedEvents, fontWeight = FontWeight.SemiBold)
        }
      }
    }

    // 3. My Clubs Section
    item {
      Text(
        text = "${strings.myClubs} (${myClubs.size})",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
      )
    }

    if (myClubs.isEmpty()) {
      item {
        Text(
          text = "You haven't joined any campus clubs yet.",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    } else {
      items(myClubs.size) { index ->
        val club = myClubs[index]
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onClubClick(club.id) },
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Text(text = club.iconEmoji, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = if (language == AppLanguage.AMHARIC && !club.nameAm.isNullOrBlank()) club.nameAm else club.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "${club.memberCount} members • ${club.category}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    }

    // 4. My Attending Events
    item {
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "${strings.myRsvps} (${myRsvps.size})",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
      )
    }

    if (myRsvps.isEmpty()) {
      item {
        Text(
          text = "No event RSVPs yet. Browse upcoming campus events!",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    } else {
      items(myRsvps.size) { index ->
        val evt = myRsvps[index]
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onEventClick(evt.id) },
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.Event,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = if (language == AppLanguage.AMHARIC && !evt.titleAm.isNullOrBlank()) evt.titleAm else evt.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "${evt.date} • ${evt.location}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    }

    // 5. Sign Out
    item {
      Spacer(modifier = Modifier.height(8.dp))
      OutlinedButton(
        onClick = onSignOut,
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp)
          .testTag("profile_sign_out_button"),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
      ) {
        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = strings.signOut, fontWeight = FontWeight.Bold)
      }
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}
