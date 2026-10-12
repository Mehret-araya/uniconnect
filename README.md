# UniConnect — Smart Campus App for University Students

An Android app that connects university students, campus clubs, and student communities. Discover events, join clubs, communicate with classmates, and manage campus life — all in one place.

![Platform](https://img.shields.io/badge/Platform-Android-green)
![Language](https://img.shields.io/badge/Language-Kotlin-purple)
![UI](https://img.shields.io/badge/UI-Jetpack%20Compose-blue)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-orange)
![License](https://img.shields.io/badge/License-MIT-yellow)

## Overview

UniConnect is a mobile platform built for university students. It brings together the tools students actually need: campus event discovery, club membership, student profiles, and community features. Built with an offline-first design, the app works reliably even on unstable campus Wi-Fi.

This is my second Android portfolio project, built to demonstrate skills that complement my first app (FarmAI) — specifically real-time communication, multi-user backend architecture, and social/community features.

## Features

### Phase 1 (Current — MVP)
- **Authentication** — Email/password sign up, sign in, password reset, and user profiles
- **Campus Events** — Browse, filter by category, view details, RSVP, and bookmark events
- **Clubs & Communities** — Discover clubs, view details, join/leave, see members
- **Student Profiles** — Public profiles showing name, university, department, year, and clubs
- **Bilingual Interface** — Full English + Amharic support
- **Dark Mode** — Material 3 theming with light and dark variants

### Phase 2 (Planned)
- **Real-Time Messaging** — 1-on-1 and group chats between students and clubs
- **Push Notifications** — Event reminders, club announcements, and new messages
- **AI Study Assistant** — Chat-based AI helper for study questions (Gemini API with offline fallback)

## Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM (ViewModel + Repository) |
| Local Database | Room SQLite |
| Remote Backend | Firebase Firestore |
| Authentication | Firebase Authentication |
| Real-Time | Firebase Realtime Database |
| Async | Kotlin Coroutines + StateFlow |
| Notifications | Firebase Cloud Messaging |
| Build | Gradle Kotlin DSL |

## Architecture

The app follows **MVVM (Model-View-ViewModel)** with a repository layer:

- **UI Layer** — Jetpack Compose screens with type-safe navigation
- **ViewModel Layer** — Exposes state via `StateFlow`
- **Repository Layer** — Abstracts data sources (local + remote)
- **Data Layer** — Room SQLite for offline cache, Firebase for cloud sync

**Offline-first design:** Data is cached locally in Room for instant access. When the network is available, changes sync to Firebase. If offline, the app continues to work with cached data and queues writes for later.

## Project Structure


app/
├── src/main/java/com/example/uniconnect/
│ ├── data/
│ │ ├── local/ # Room entities, DAOs, database
│ │ ├── remote/ # Firebase sources
│ │ └── repository/ # Data access abstraction
│ ├── domain/
│ │ └── model/ # Domain models
│ ├── ui/
│ │ ├── screens/ # Splash, Auth, Home, Events, Clubs, Chat, Profile
│ │ ├── components/ # Reusable Compose components
│ │ ├── navigation/ # Nav graph
│ │ └── theme/ # Material 3 theming
│ └── viewmodel/ # Feature ViewModels
└── src/main/res/ # Resources, strings (EN + AM)

text

## Screens

| Screen | Purpose |
|--------|---------|
| Splash / Onboarding | First launch and intro |
| Sign In / Sign Up | Authentication |
| Home / Dashboard | Upcoming events, announcements, quick actions |
| Events List | Browse and filter campus events |
| Event Detail | Full event info, RSVP, bookmark |
| Clubs List | Browse campus clubs |
| Club Detail | Club info, members, events, join/leave |
| Chats (Preview) | Placeholder for Phase 2 messaging |
| Profile | Own + public student profiles |
| Bookmarks | Saved events and items |
| Settings | Preferences, language, theme |

## Getting Started

### Prerequisites
- Android Studio (latest stable)
- JDK 17+
- Android SDK 24+
- Firebase project (for backend features)

### Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/Mehret-araya/uniconnect.git
   cd uniconnect
Set up Firebase:

Create a Firebase project at console.firebase.google.com

Enable Authentication (Email/Password)

Enable Firestore Database

Enable Realtime Database

Download google-services.json and place it in app/

Build and run:

bash
./gradlew assembleDebug
Or open the project in Android Studio and click Run.

Known Limitations
Phase 2 features not yet implemented — Real-time messaging, notifications, and AI study assistant are planned but not yet built

Firebase setup required — The app requires your own Firebase project to run with real data

No automated tests yet — Test coverage is planned for critical flows

Chat screen is a preview only — It shows the planned UI, not working messaging

What I'd Build Next
Phase 2 — Real-time messaging via Firebase Realtime Database or Socket.IO

Push notifications — Firebase Cloud Messaging for events and messages

AI study assistant — Gemini API integration with offline fallback

Automated tests — Unit tests for ViewModels, integration tests for auth flow

Analytics — Firebase Analytics for feature usage insights

Author
Mehret Araya Gebreslassie — Fullstack Developer

GitHub: @Mehret-araya

LinkedIn: mehret-araya

Related Projects
FarmAI — AI-powered agriculture assistant for Ethiopian farmers: github.com/Mehret-araya/farm-ai

License
MIT

text

**COPY TO HERE **

---

##  How to Add It to GitHub

### Step 1: Go to your repo
Open: `https://github.com/Mehret-araya/uniconnect`

### Step 2: Create the README
1. Click **"Add a README"** button (if shown)
   - OR click **"Add file"** → **"Create new file"**
   - Filename: `README.md`
2. In the editor:
   - Press **`Ctrl + A`** → **`Delete`** (clear any template)
   - **Paste the README above** (directly into GitHub — do NOT go through Notepad)

### Step 3: Commit
1. Scroll to the bottom
2. Commit message: `docs: add professional README`
3. Make sure **"Commit directly to the main branch"** is selected
4. Click **"Commit changes"**

---

## ⚠️ Remember — No Notepad

When you did the FarmAI README, the text got corrupted because you pasted it through Notepad first. This time:

**✅ Copy from this chat → Paste DIRECTLY into the GitHub editor**
**❌ Don't paste into Notepad, Word, or any other app first**

---

## After You Commit

**Verify:**
1. Go to `https://github.com/Mehret-araya/uniconnect`
2. Scroll down — the README should render below the file list
3. Check that:
   - The title displays correctly (`# UniConnect — Smart Campus App`)
   - Bullet points work
   - Tables render
   - No `вҖ”` or `рҹ` gibberish

**If everything looks good** → done 

**If corrupted** → tell me, and we'll redo it.

---

##  Priority Reminder

**Before you spend time on UniConnect's README polish**, please confirm:

- ✅ FarmAI screenshots taken?
- ✅ FarmAI CV updated?
- ✅ Complete package sent to Hermela?

**If any is NO** — do those first. **Two live GitHub repos is great, but the recruiter wants the FarmAI package first.**

---

**Paste the README, commit it, verify it renders correctly, then report back.** 

