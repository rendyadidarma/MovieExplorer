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

## Preview
<img width="447" height="985" alt="image" src="https://github.com/user-attachments/assets/e08c4d50-a5f4-4b0d-855a-f7099c87aed5" />
<img width="442" height="1019" alt="image" src="https://github.com/user-attachments/assets/c502f7c0-7b2e-4a77-9ffa-af578a00719f" />
<img width="454" height="1003" alt="image" src="https://github.com/user-attachments/assets/48b7c99b-2e2a-496b-8abd-fc4a4b46db8f" />
<img width="465" height="1028" alt="image" src="https://github.com/user-attachments/assets/b7b8e020-7484-4d3d-883a-61e4e79acc3e" />

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
