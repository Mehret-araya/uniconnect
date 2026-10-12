package com.example.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.localization.AppLanguage
import com.example.ui.localization.getStrings

@Composable
fun SignInScreen(
  currentLanguage: AppLanguage,
  onToggleLanguage: () -> Unit,
  onSignInSuccess: () -> Unit,
  onNavigateToSignUp: () -> Unit,
  onNavigateToForgotPassword: () -> Unit,
  onSignIn: (email: String, pass: String) -> Boolean,
  modifier: Modifier = Modifier
) {
  val strings = getStrings(currentLanguage)
  var email by remember { mutableStateOf("yohannes.b@aau.edu.et") }
  var password by remember { mutableStateOf("Student2026!") }
  var passwordVisible by remember { mutableStateOf(false) }
  var isLoading by remember { mutableStateOf(false) }
  var errorMessage by remember { mutableStateOf<String?>(null) }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background)
      .verticalScroll(rememberScrollState())
      .padding(24.dp)
      .testTag("sign_in_screen"),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Language switch icon in header
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Spacer(modifier = Modifier.weight(1f))
      IconButton(
        onClick = onToggleLanguage,
        modifier = Modifier.testTag("auth_lang_toggle")
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Language,
            contentDescription = "Switch Language",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = currentLanguage.nativeName,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Box(
      modifier = Modifier
        .size(90.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colorScheme.primaryContainer),
      contentAlignment = Alignment.Center
    ) {
      Image(
        painter = painterResource(id = R.drawable.ic_uniconnect_logo_1791835122980),
        contentDescription = strings.appName,
        modifier = Modifier.size(70.dp)
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = strings.welcomeBack,
      style = MaterialTheme.typography.headlineSmall,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.onBackground
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
      text = strings.slogan,
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Spacer(modifier = Modifier.height(28.dp))

    OutlinedTextField(
      value = email,
      onValueChange = { email = it; errorMessage = null },
      label = { Text(strings.email) },
      leadingIcon = {
        Icon(Icons.Default.Email, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
      },
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
      modifier = Modifier
        .fillMaxWidth()
        .testTag("email_input"),
      shape = RoundedCornerShape(12.dp)
    )

    Spacer(modifier = Modifier.height(14.dp))

    OutlinedTextField(
      value = password,
      onValueChange = { password = it; errorMessage = null },
      label = { Text(strings.password) },
      leadingIcon = {
        Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
      },
      trailingIcon = {
        IconButton(onClick = { passwordVisible = !passwordVisible }) {
          Icon(
            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
            contentDescription = null
          )
        }
      },
      visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      modifier = Modifier
        .fillMaxWidth()
        .testTag("password_input"),
      shape = RoundedCornerShape(12.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Spacer(modifier = Modifier.weight(1f))
      TextButton(
        onClick = onNavigateToForgotPassword,
        modifier = Modifier.testTag("forgot_password_button")
      ) {
        Text(
          text = strings.forgotPassword,
          color = MaterialTheme.colorScheme.primary,
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold
        )
      }
    }

    if (errorMessage != null) {
      Text(
        text = errorMessage ?: "",
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(vertical = 4.dp)
      )
    }

    Spacer(modifier = Modifier.height(18.dp))

    Button(
      onClick = {
        if (email.isBlank() || password.isBlank()) {
          errorMessage = "Please enter email and password"
          return@Button
        }
        isLoading = true
        val success = onSignIn(email, password)
        isLoading = false
        if (success) {
          onSignInSuccess()
        } else {
          errorMessage = "Failed to sign in. Check credentials."
        }
      },
      enabled = !isLoading,
      modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .testTag("sign_in_submit_button"),
      shape = RoundedCornerShape(12.dp),
      colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
      if (isLoading) {
        CircularProgressIndicator(
          modifier = Modifier.size(24.dp),
          color = MaterialTheme.colorScheme.onPrimary
        )
      } else {
        Text(
          text = strings.signIn,
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    TextButton(
      onClick = onNavigateToSignUp,
      modifier = Modifier.testTag("navigate_to_signup_button")
    ) {
      Text(
        text = strings.dontHaveAccount,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
      )
    }
  }
}
