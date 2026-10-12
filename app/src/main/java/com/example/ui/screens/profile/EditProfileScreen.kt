package com.example.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.User
import com.example.ui.localization.AppLanguage
import com.example.ui.localization.AppStrings
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
  user: User?,
  language: AppLanguage,
  strings: AppStrings,
  onNavigateBack: () -> Unit,
  onSaveProfile: (name: String, department: String, year: String, bio: String) -> Unit,
  modifier: Modifier = Modifier
) {
  var name by remember { mutableStateOf(user?.name ?: "") }
  var department by remember { mutableStateOf(user?.department ?: "") }
  var yearOfStudy by remember { mutableStateOf(user?.yearOfStudy ?: "") }
  var bio by remember { mutableStateOf(user?.bio ?: "") }

  val snackbarHostState = remember { SnackbarHostState() }
  val scope = rememberCoroutineScope()

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(strings.editProfile, fontWeight = FontWeight.Bold) },
        navigationIcon = {
          IconButton(
            onClick = onNavigateBack,
            modifier = Modifier.testTag("edit_profile_back_button")
          ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
      )
    },
    snackbarHost = { SnackbarHost(snackbarHostState) },
    modifier = modifier.testTag("edit_profile_screen")
  ) { padding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(padding)
        .verticalScroll(rememberScrollState())
        .padding(20.dp)
    ) {
      OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text(strings.fullName) },
        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        singleLine = true,
        modifier = Modifier
          .fillMaxWidth()
          .testTag("edit_name_input"),
        shape = RoundedCornerShape(12.dp)
      )

      Spacer(modifier = Modifier.height(14.dp))

      OutlinedTextField(
        value = department,
        onValueChange = { department = it },
        label = { Text(strings.department) },
        leadingIcon = { Icon(Icons.Default.Book, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        singleLine = true,
        modifier = Modifier
          .fillMaxWidth()
          .testTag("edit_department_input"),
        shape = RoundedCornerShape(12.dp)
      )

      Spacer(modifier = Modifier.height(14.dp))

      OutlinedTextField(
        value = yearOfStudy,
        onValueChange = { yearOfStudy = it },
        label = { Text(strings.yearOfStudy) },
        leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        singleLine = true,
        modifier = Modifier
          .fillMaxWidth()
          .testTag("edit_year_input"),
        shape = RoundedCornerShape(12.dp)
      )

      Spacer(modifier = Modifier.height(14.dp))

      OutlinedTextField(
        value = bio,
        onValueChange = { bio = it },
        label = { Text("About Me / Bio") },
        minLines = 3,
        maxLines = 5,
        modifier = Modifier
          .fillMaxWidth()
          .testTag("edit_bio_input"),
        shape = RoundedCornerShape(12.dp)
      )

      Spacer(modifier = Modifier.height(24.dp))

      Button(
        onClick = {
          onSaveProfile(name, department, yearOfStudy, bio)
          scope.launch {
            snackbarHostState.showSnackbar("Profile updated successfully")
          }
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .testTag("save_profile_button"),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
      ) {
        Text(text = strings.saveChanges, fontSize = 16.sp, fontWeight = FontWeight.Bold)
      }
    }
  }
}
