# MovieExplorer (Android)

MovieExplorer is an Android app built in **Kotlin** that allows users to explore movies, genres, trailers, and reviews using the TMDB API. The app follows **MVVM architecture** with clean separation of concerns and modern Android development practices.

---

## Features

- Browse movie genres
- View movie details (title, overview, rating, runtime)
- Watch movie trailers (YouTube integration)
- Read movie reviews
- Smooth animations with Jetpack Compose
- Network error handling (e.g., missing API key, connectivity issues)

---

## Requirements

- Android Studio Flamingo or newer  
- Minimum SDK: 21  
- Kotlin 1.8+  
- Gradle 8+  

---

## Setup

1. **Clone the repository**

```bash
git clone https://github.com/<username>/MovieExplorer-Android.git
cd MovieExplorer-Android
```

2.	Open in Android Studio

	•	Open the project by selecting the build.gradle at the root.
	•	Let Gradle sync and resolve all dependencies automatically.

3.	Add TMDB API key

	•	Open local.properties (depending on implementation).
	•	Example if using local.properties:
```bash
  TMDB_API_KEY=YOUR_API_KEY_HERE
  ```

4.	Build and Run

	•	Select an emulator or physical device
	•	Press Run

## License

This project is for educational and portfolio purposes.
