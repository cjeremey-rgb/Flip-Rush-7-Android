FLIP RUSH 7 ANDROID APP - v1.1 VOICE CHAT WRAPPER
=================================================

Live game URL:
https://flip-rush-7-60k8.onrender.com/

Android package ID:
com.fliprush7.app

Version:
1.1.0 (versionCode 2)

This is intentionally a FLAT GitHub project. All files can live directly in the repository root.
Gradle reconstructs the Android source and launcher-icon resource folders during the build.

WHAT CHANGED FROM v1.0:
- Added Android RECORD_AUDIO permission.
- The WebView now securely grants web microphone access only to the live Flip Rush 7 Render origin.
- This lets the multiplayer website use its new WebRTC microphone/voice-chat button.

SIGNING:
Use the SAME four GitHub Actions signing secrets already configured for v1.0.
Do not create a new signing key. Using the same key lets v1.1 install over v1.0 as a normal update.

Build with FINAL-RELEASE-WORKFLOW.yml. The artifact will be named:
Flip-Rush-7-v1.1-Voice-Release

Inside will be:
Flip-Rush-7-v1.1.apk
Flip-Rush-7-v1.1.sha256.txt

Normal game changes still happen on the live website and do not require another APK.
A new APK is only required when native Android permissions/wrapper behavior changes.
