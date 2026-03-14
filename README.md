# HabitNote 

A clean and minimal note-taking Android app built with Jetpack Compose and Material 3.

## Features

- **Create notes** with a title and body text
- **Edit existing notes**
- **Delete notes** via long-press context menu
- **Color-coded notes** — pick a background color for each note
- **Grid / List view toggle** — switch between staggered grid and list layout
- **Filter by color** — filter notes by their assigned color
- **Empty state** — friendly illustration shown when no notes exist

## Tech Stack

| Layer | Technology |
|---|---|
| UI | Jetpack Compose + Material 3 |
| Navigation | Navigation Compose |
| State management | ViewModel + `mutableStateOf` |
| Architecture | MVVM |
| Language | Kotlin |

## Project Structure

```
com.example.habitnote
├── model
│   └── Habit.kt              # Data class for a note
├── view
│   ├── HomeScreen.kt         # Note list + grid/filter UI
│   ├── AddScreen.kt          # Create new note
│   ├── EditScreen.kt         # Edit existing note
│   └── ViewScreen.kt         # Read a saved note
├── viewmodel
│   └── MainViewModel.kt      # State + business logic
└── MainActivity.kt           # NavHost + entry point
```

## Navigation Routes

| Route | Screen |
|---|---|
| `home` | Home screen with note list |
| `add` | Add new note |
| `view/{id}` | View a saved note |
| `edit/{id}` | Edit a saved note |

## Data Model

```kotlin
data class Habit(
    val id: Int,
    val title: String,
    val subtitle: String,
    val color: Color
)
```

## Getting Started

1. Clone the repository
2. Open in Android Studio
3. Run on an emulator or physical device (Android 8.0+)

## Screenshots



## License

MIT
