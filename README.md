# Flip Rush 7 Android Wrapper

This is a lightweight Android WebView shell for the live Flip Rush 7 website. The website remains the source of truth, so gameplay/UI updates are deployed to the website rather than rebuilt into the app.

## Point the app at the live website

Edit:

`app/src/main/assets/live-url.txt`

The live website is already configured as:

`https://flip-rush-7-60k8.onrender.com/`

Normal users will launch directly into the Flip Rush 7 startup screen. The native setup screen is retained only as an emergency fallback if the bundled URL is ever removed or invalid.

## Build

Open this folder as a project in Android Studio. Let Android Studio install/sync the required Android SDK/Gradle components, then use **Build → Build APK(s)** or **Build → Generate Signed Bundle / APK**.

## Update behavior

The wrapper loads the live HTTPS website. Normal website changes therefore do not require a new APK. A new APK is needed only if you change native wrapper behavior, the Android package identity, permissions, or launcher icon.

The wrapper does not alter the game UI. The game still renders from the current website files.

## Optional: build the APK automatically with GitHub Actions

A workflow is included at `.github/workflows/build-apk.yml`. Put this Android wrapper project in its own GitHub repository and push to `main`, or run **Build Flip Rush 7 APK** manually from the repository's Actions tab. The resulting debug APK is uploaded as a workflow artifact.
