
# Mobile Assignments

This repository contains various mobile assignments, including a Tic-Tac-Toe (XO) game and a Calculator application developed for Android.

## Repository URL

[https://github.com/aqaPayam/mobile-assignments-aqaPayam](https://github.com/aqaPayam/mobile-assignments-aqaPayam)

## Projects

### XO Game

This project is a Tic-Tac-Toe (XO) game developed for Android.

#### Project Structure

- `.gitignore`: Specifies files and directories that should be ignored by Git.
- `.idea`: IDE-specific settings and configuration.
- `app`: Contains the main application code and resources.
  - `build.gradle.kts`: Gradle build script for the app module.
  - `proguard-rules.pro`: ProGuard configuration file.
  - `src`: Source code and resources for the application.
    - `androidTest`: Instrumented tests for Android.
    - `main`: Main application code and resources.
      - `AndroidManifest.xml`: Application manifest file.
      - `java/com/example/xo`: Java source files.
        - `ForgetPasswordActivity.java`: Activity for handling password recovery.
        - `GAME.java`, `GAME2.java`, `GAMECPU.java`, `GAMECPU2.java`: Game logic and activities.
    - `test`: Unit tests for the application.

#### Getting Started

##### Prerequisites

- Android Studio
- Java Development Kit (JDK)
- Android SDK

##### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/aqaPayam/mobile-assignments-aqaPayam
    ```
2. Open the `XO` project in Android Studio.
3. Build the project:
    ```sh
    ./gradlew build
    ```
4. Run the project on an Android emulator or device.

#### Usage

- The application provides multiple game modes including single-player and two-player modes.
- Navigate through different activities to play the game and manage user accounts.

#### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Calculator App

This project is a simple calculator application developed for Android.

#### Project Structure

- `.gitignore`: Specifies files and directories that should be ignored by Git.
- `.idea`: IDE-specific settings and configuration.
- `app`: Contains the main application code and resources.
  - `build.gradle.kts`: Gradle build script for the app module.
  - `proguard-rules.pro`: ProGuard configuration file.
  - `src`: Source code and resources for the application.
    - `androidTest`: Instrumented tests for Android.
    - `main`: Main application code and resources.
      - `AndroidManifest.xml`: Application manifest file.
      - `java/com/example/login_sign`: Java source files.
        - `Calc.java`: Main activity for the calculator functionality.
      - `res`: Application resources such as layouts and drawable assets.
        - `drawable`: Drawable resources.
        - `layout`: Layout XML files.
          - `calc.xml`: Layout for the calculator interface.
    - `test`: Unit tests for the application.

#### Getting Started

##### Prerequisites

- Android Studio
- Java Development Kit (JDK)
- Android SDK

##### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/aqaPayam/mobile-assignments-aqaPayam
    ```
2. Open the `CALCULATOR` project in Android Studio.
3. Build the project:
    ```sh
    ./gradlew build
    ```
4. Run the project on an Android emulator or device.

#### Usage

- The application provides basic calculator functionality with a user-friendly interface.

#### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
