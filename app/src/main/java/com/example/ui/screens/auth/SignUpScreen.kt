package com.example.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.localization.AppLanguage
import com.example.ui.localization.getStrings

@Composable
fun SignUpScreen(
  currentLanguage: AppLanguage,
  onSignUpSuccess: () -> Unit,
  onNavigateToSignIn: () -> Unit,
  onSignUp: (name: String, email: String, university: String, department: String, year: String) -> Boolean,
  modifier: Modifier = Modifier
) {
  val strings = getStrings(currentLanguage)

  var fullName by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var university by remember { mutableStateOf("Addis Ababa University") }
  var department by remember { mutableStateOf("") }
  var yearOfStudy by remember { mutableStateOf("3rd Year") }
  var password by remember { mutableStateOf("") }
  var confirmPassword by remember { mutableStateOf("") }
  var errorMessage by remember { mutableStateOf<String?>(null) }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .verticalScroll(rememberScrollState())
      .padding(24.dp)
      .testTag("sign_up_screen"),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = strings.createAccount,
      style = MaterialTheme.typography.headlineSmall,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.onBackground
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
      text = "Join your campus student network",
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Spacer(modifier = Modifier.height(24.dp))

    OutlinedTextField(
      value = fullName,
      onValueChange = { fullName = it; errorMessage = null },
      label = { Text(strings.fullName) },
      leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
      singleLine = true,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("full_name_input"),
      shape = RoundedCornerShape(12.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
      value = email,
      onValueChange = { email = it; errorMessage = null },
      label = { Text(strings.email) },
      leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
      modifier = Modifier
        .fillMaxWidth()
        .testTag("signup_email_input"),
      shape = RoundedCornerShape(12.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
      value = university,
      onValueChange = { university = it },
      label = { Text(strings.university) },
      leadingIcon = { Icon(Icons.Default.School, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
      singleLine = true,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("university_input"),
      shape = RoundedCornerShape(12.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
      value = department,
      onValueChange = { department = it },
      label = { Text(strings.department) },
      placeholder = { Text("e.g. Computer Science") },
      singleLine = true,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("department_input"),
      shape = RoundedCornerShape(12.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
      value = yearOfStudy,
      onValueChange = { yearOfStudy = it },
      label = { Text(strings.yearOfStudy) },
      singleLine = true,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("year_input"),
      shape = RoundedCornerShape(12.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
      value = password,
      onValueChange = { password = it; errorMessage = null },
      label = { Text(strings.password) },
      leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
      visualTransformation = PasswordVisualTransformation(),
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      modifier = Modifier
        .fillMaxWidth()
        .testTag("signup_password_input"),
      shape = RoundedCornerShape(12.dp)
    )

    Spacer(modifier = Modifier.height(12.dp))

    OutlinedTextField(
      value = confirmPassword,
      onValueChange = { confirmPassword = it; errorMessage = null },
      label = { Text(strings.confirmPassword) },
      leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
      visualTransformation = PasswordVisualTransformation(),
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      modifier = Modifier
        .fillMaxWidth()
        .testTag("signup_confirm_password_input"),
      shape = RoundedCornerShape(12.dp)
    )

    if (errorMessage != null) {
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = errorMessage ?: "",
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall
      )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Button(
      onClick = {
        if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
          errorMessage = "Please complete all required fields."
          return@Button
        }
        if (password != confirmPassword) {
          errorMessage = "Passwords do not match."
          return@Button
        }
        val ok = onSignUp(fullName, email, university, department.ifBlank { "General Studies" }, yearOfStudy)
        if (ok) {
          onSignUpSuccess()
        } else {
          errorMessage = "Registration failed."
        }
      },
      modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .testTag("sign_up_submit_button"),
      shape = RoundedCornerShape(12.dp),
      colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
      Text(
        text = strings.signUp,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    TextButton(
      onClick = onNavigateToSignIn,
      modifier = Modifier.testTag("navigate_to_signin_button")
    ) {
      Text(
        text = strings.alreadyHaveAccount,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
      )
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}
