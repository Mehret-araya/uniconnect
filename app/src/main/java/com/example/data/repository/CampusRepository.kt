package com.example.data.repository

import com.example.data.model.Announcement
import com.example.data.model.Club
import com.example.data.model.Event
import com.example.data.model.EventCategory
import com.example.data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CampusRepository {

  // Current logged in user (null if signed out)
  private val _currentUser = MutableStateFlow<User?>(
    User(
      id = "stu_001",
      name = "Yohannes Berhanu",
      email = "yohannes.b@aau.edu.et",
      university = "Addis Ababa University",
      department = "Software Engineering",
      yearOfStudy = "4th Year",
      bio = "Passionate about mobile development, open source algorithms, and robotics.",
      clubs = listOf("club_1", "club_2"),
      rsvpEvents = listOf("evt_1"),
      bookmarkedEvents = listOf("evt_1", "evt_3")
    )
  )
  val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

  // Campus Events
  private val _events = MutableStateFlow<List<Event>>(
    listOf(
      Event(
        id = "evt_1",
        title = "AI & Tech Innovation Hackathon 2026",
        titleAm = "የሰው ሰራሽ አስተውሎት እና የቴክኖሎጂ ፈጠራ ውድድር 2026",
        description = "Join top engineering minds for a 48-hour hackathon tackling real-world Ethiopian transportation, agriculture, and campus solutions with smart systems.",
        descriptionAm = "በኢትዮጵያ የትራንስፖርት፣ ግብርና እና የግቢ ችግሮችን ለመፍታት የተዘጋጀ የ48 ሰዓት የቴክኖሎጂ ውድድር።",
        date = "Oct 24, 2026",
        time = "09:00 AM - 06:00 PM",
        location = "Science & Technology Hall, Room 402",
        category = EventCategory.ACADEMIC,
        organizer = "Google Developer Student Club & IEEE AAU",
        clubId = "club_1",
        attendeeCount = 142,
        isAttending = true,
        isBookmarked = true
      ),
      Event(
        id = "evt_2",
        title = "Inter-College Football Championship Final",
        titleAm = "የኮሌጆች የእግር ኳስ ፍጻሜ ዋንጫ ጨዋታ",
        description = "The grand finale between Faculty of Technology and Faculty of Medicine. Come support your campus department with free cheer banners and live campus commentary!",
        descriptionAm = "በቴክኖሎጂ ፋኩልቲ እና በህክምና ፋኩልቲ መካከል የሚካሄድ ታላቅ የፍጻሜ የእግር ኳስ ጨዋታ።",
        date = "Oct 26, 2026",
        time = "03:30 PM",
        location = "Main Campus Stadium",
        category = EventCategory.SPORTS,
        organizer = "AAU Sports Commission",
        attendeeCount = 380,
        isAttending = false,
        isBookmarked = false
      ),
      Event(
        id = "evt_3",
        title = "Traditional Ethiopian Cultural Night & Poetry",
        titleAm = "የባህል ምሽት፣ ስነ-ጽሁፍ እና ቅኔ ማዕድ",
        description = "An evening celebrating Ethiopian diversity with traditional music, folk dance, student poetry recitations, and campus art exhibitions.",
        descriptionAm = "የተለያዩ የኢትዮጵያ ባህላዊ ሙዚቃዎች፣ ጭፈራዎች፣ የተማሪዎች ግጥም እና የኪነጥበብ ስራዎች የሚቀርቡበት ደማቅ ምሽት።",
        date = "Oct 28, 2026",
        time = "05:00 PM",
        location = "Cultural Center Amphitheater",
        category = EventCategory.CULTURAL,
        organizer = "Campus Cultural Society",
        clubId = "club_3",
        attendeeCount = 210,
        isAttending = false,
        isBookmarked = true
      ),
      Event(
        id = "evt_4",
        title = "Freshman Orientation & Welcome Social",
        titleAm = "የአዳዲስ ተማሪዎች አቀባበል እና ትውውቅ ፕሮግራም",
        description = "Welcome incoming students! Meet faculty mentors, discover club booths, join ice-breaker games, and enjoy coffee and snacks.",
        descriptionAm = "ለአዳዲስ ተማሪዎች የተዘጋጀ የትውውቅ፣ የክለቦች ምዝገባ እና የካምፓስ መመሪያ ፕሮግራም።",
        date = "Nov 02, 2026",
        time = "10:00 AM",
        location = "Student Union Lawn",
        category = EventCategory.SOCIAL,
        organizer = "Student Union",
        attendeeCount = 520,
        isAttending = false,
        isBookmarked = false
      ),
      Event(
        id = "evt_5",
        title = "Autonomous Robotics Workshop & Demo",
        titleAm = "የሮቦቲክስ እና አውቶሜሽን ተግባራዊ ስልጠና",
        description = "Hands-on workshop with microcontrollers, ROS2, and computer vision. Build your first line-following robot with hardware kits provided.",
        descriptionAm = "በማይክሮኮንትሮለር እና በሮቦቲክስ ላይ የሚያተኩር ተግባራዊ የተግባር ስልጠና።",
        date = "Nov 05, 2026",
        time = "02:00 PM",
        location = "Engineering Block C, Lab 12",
        category = EventCategory.CLUB,
        organizer = "Robotics & Automation Club",
        clubId = "club_2",
        attendeeCount = 85,
        isAttending = false,
        isBookmarked = false
      )
    )
  )
  val events: StateFlow<List<Event>> = _events.asStateFlow()

  // Campus Clubs
  private val _clubs = MutableStateFlow<List<Club>>(
    listOf(
      Club(
        id = "club_1",
        name = "Google Developer Student Club (GDSC)",
        nameAm = "የጉግል ተማሪዎች የቴክኖሎጂ ክለብ (GDSC)",
        category = "Technology & Coding",
        description = "Community group for students interested in Google developer technologies. From mobile Android apps to Cloud & AI, we build for social good.",
        descriptionAm = "በጉግል የቴክኖሎጂ መሳሪያዎች ላይ የሚያተኩር፣ የሞባይል እና የክላውድ ስልጠናዎችን የሚሰጥ የተማሪዎች ማህበር።",
        memberCount = 284,
        isJoined = true,
        adminName = "Selamawit Tadesse",
        meetingSchedule = "Every Wednesday, 4:00 PM",
        roomLocation = "CS Lab 3, 4 Kilo Campus",
        members = listOf("Yohannes Berhanu", "Selamawit Tadesse", "Abebe Bekele", "Hiwot Girma"),
        iconEmoji = "💻"
      ),
      Club(
        id = "club_2",
        name = "Robotics & Embedded Systems Club",
        nameAm = "የሮቦቲክስ እና ኤምቤደድ ሲስተም ክለብ",
        category = "Engineering & Hardware",
        description = "Building autonomous rovers, drones, and smart IoT solutions for African universities and industrial challenges.",
        descriptionAm = "ሮቦቶችን፣ ድሮኖችን እና ዘመናዊ የሃርድዌር ሲስተሞችን የሚያበለጽግ የኢንጂነሪንግ ክለብ።",
        memberCount = 145,
        isJoined = true,
        adminName = "Dawit Mengistu",
        meetingSchedule = "Saturdays, 10:00 AM",
        roomLocation = "Mechanical Workshop 08",
        members = listOf("Yohannes Berhanu", "Dawit Mengistu", "Kidus Tesfaye"),
        iconEmoji = "🤖"
      ),
      Club(
        id = "club_3",
        name = "AAU Debate Society & Model UN",
        nameAm = "የአዲስ አበባ ዩኒቨርሲቲ ክርክር እና ሞዴል ዩኤን",
        category = "Public Speaking & Policy",
        description = "Sharpen your critical thinking, parliamentary debate, diplomacy, and global leadership skills in weekly moderated rounds.",
        descriptionAm = "የተማሪዎችን የክርክር፣ የንግግር እና የአመራር ክህሎት የሚያሳድግ የክርክር ማህበር።",
        memberCount = 98,
        isJoined = false,
        adminName = "Blen Alemayehu",
        meetingSchedule = "Thursdays, 5:00 PM",
        roomLocation = "Social Science Hall 101",
        members = listOf("Blen Alemayehu", "Samuel Kassa", "Marta Worku"),
        iconEmoji = "🎙️"
      ),
      Club(
        id = "club_4",
        name = "Green Campus Sustainability Initiative",
        nameAm = "የአረንጓዴ ካምፓስ የተፈጥሮ ጥበቃ ማህበር",
        category = "Environment & Community",
        description = "Promoting clean energy, recycling drives, tree planting, and zero-waste initiatives across all university campuses.",
        descriptionAm = "የካምፓስ ጽዳት፣ የዛፍ ተከላ እና የተፈጥሮ ጥበቃ ስራዎችን የሚያከናውን ንቁ ማህበር።",
        memberCount = 192,
        isJoined = false,
        adminName = "Natnael Fikru",
        meetingSchedule = "Bi-weekly Sundays, 9:00 AM",
        roomLocation = "Biology Greenhouse 2",
        members = listOf("Natnael Fikru", "Rahel Desta", "Ephrem Negash"),
        iconEmoji = "🌱"
      )
    )
  )
  val clubs: StateFlow<List<Club>> = _clubs.asStateFlow()

  // Campus Announcements
  private val _announcements = MutableStateFlow<List<Announcement>>(
    listOf(
      Announcement(
        id = "ann_1",
        title = "Semester Final Exam Schedule Released",
        titleAm = "የሴሚስተሩ የፍጻሜ ፈተና የጊዜ ሰሌዳ ይፋ ሆነ",
        content = "The official registrar has posted the final examination timetable on the student portal. Please review your departmental seating allotments.",
        contentAm = "የሴሚስተሩ የፈተና ፕሮግራም በፖርታል ላይ ተለጥፏል። ተማሪዎች የፈተና ክፍላቸውን አስቀድመው እንዲመለከቱ ተጠይቋል።",
        date = "Today",
        department = "University Registrar",
        isUrgent = true
      ),
      Announcement(
        id = "ann_2",
        title = "Library 24/7 Night Hours for Study Week",
        titleAm = "ቤተ-መጽሐፍት ለፈተና ሳምንት 24 ሰዓት ክፍት ይሆናል",
        content = "The main Science Library and digital research labs will remain open 24 hours daily starting this Monday.",
        contentAm = "ዋናው የሳይንስ ቤተ-መጽሐፍት እና የኮምፒውተር ላቦች ከሰኞ ጀምሮ ለ24 ሰዓት ክፍት ሆነው አገልግሎት ይሰጣሉ።",
        date = "Yesterday",
        department = "Library Services",
        isUrgent = false
      )
    )
  )
  val announcements: StateFlow<List<Announcement>> = _announcements.asStateFlow()

  // Actions
  fun toggleRsvp(eventId: String) {
    _events.update { list ->
      list.map { evt ->
        if (evt.id == eventId) {
          val newAttending = !evt.isAttending
          val countDiff = if (newAttending) 1 else -1
          evt.copy(
            isAttending = newAttending,
            attendeeCount = (evt.attendeeCount + countDiff).coerceAtLeast(0)
          )
        } else evt
      }
    }
    _currentUser.update { user ->
      if (user == null) return@update null
      val currentRsvps = user.rsvpEvents.toMutableList()
      if (currentRsvps.contains(eventId)) {
        currentRsvps.remove(eventId)
      } else {
        currentRsvps.add(eventId)
      }
      user.copy(rsvpEvents = currentRsvps)
    }
  }

  fun toggleBookmark(eventId: String) {
    _events.update { list ->
      list.map { evt ->
        if (evt.id == eventId) {
          evt.copy(isBookmarked = !evt.isBookmarked)
        } else evt
      }
    }
    _currentUser.update { user ->
      if (user == null) return@update null
      val bookmarks = user.bookmarkedEvents.toMutableList()
      if (bookmarks.contains(eventId)) {
        bookmarks.remove(eventId)
      } else {
        bookmarks.add(eventId)
      }
      user.copy(bookmarkedEvents = bookmarks)
    }
  }

  fun toggleJoinClub(clubId: String) {
    _clubs.update { list ->
      list.map { club ->
        if (club.id == clubId) {
          val newJoined = !club.isJoined
          val countDiff = if (newJoined) 1 else -1
          val userName = _currentUser.value?.name ?: "Student"
          val newMembers = club.members.toMutableList()
          if (newJoined && !newMembers.contains(userName)) {
            newMembers.add(userName)
          } else if (!newJoined) {
            newMembers.remove(userName)
          }
          club.copy(
            isJoined = newJoined,
            memberCount = (club.memberCount + countDiff).coerceAtLeast(0),
            members = newMembers
          )
        } else club
      }
    }
    _currentUser.update { user ->
      if (user == null) return@update null
      val currentClubs = user.clubs.toMutableList()
      if (currentClubs.contains(clubId)) {
        currentClubs.remove(clubId)
      } else {
        currentClubs.add(clubId)
      }
      user.copy(clubs = currentClubs)
    }
  }

  fun updateProfile(name: String, department: String, year: String, bio: String) {
    _currentUser.update { user ->
      user?.copy(
        name = name,
        department = department,
        yearOfStudy = year,
        bio = bio
      )
    }
  }

  fun signIn(email: String, pass: String): Boolean {
    _currentUser.value = User(
      id = "stu_001",
      name = if (email.contains(".")) email.substringBefore(".").replaceFirstChar { it.uppercase() } else "Student",
      email = email,
      university = "Addis Ababa University",
      department = "Software Engineering",
      yearOfStudy = "4th Year",
      bio = "Campus student explorer.",
      clubs = listOf("club_1"),
      rsvpEvents = listOf("evt_1"),
      bookmarkedEvents = listOf("evt_1")
    )
    return true
  }

  fun signUp(name: String, email: String, university: String, department: String, year: String): Boolean {
    _currentUser.value = User(
      id = "stu_" + System.currentTimeMillis().toString().takeLast(4),
      name = name,
      email = email,
      university = university,
      department = department,
      yearOfStudy = year,
      bio = "New student member of UniConnect.",
      clubs = emptyList(),
      rsvpEvents = emptyList(),
      bookmarkedEvents = emptyList()
    )
    return true
  }

  fun signOut() {
    _currentUser.value = null
  }

  companion object {
    val instance: CampusRepository by lazy { CampusRepository() }
  }
}
