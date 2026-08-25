FLIP RUSH 7 ANDROID v1.3.0 - FINAL RELEASE WRAPPER
==================================================

This package intentionally builds a SIGNED RELEASE APK only.
The GitHub Actions workflow does NOT run assembleDebug.

Live game URL:
https://flip-rush-7-60k8.onrender.com/

Android identity:
Package: com.fliprush7.app
Version name: 1.3.0
Version code: 4

Microphone support:
- android.permission.RECORD_AUDIO is declared.
- Android WebView microphone permission is granted only to the trusted Flip Rush 7 Render origin.

Required existing GitHub Actions repository secrets:
ANDROID_KEYSTORE_BASE64
ANDROID_KEYSTORE_PASSWORD
ANDROID_KEY_ALIAS
ANDROID_KEY_PASSWORD

IMPORTANT:
Use the SAME signing secrets/key already created for Flip Rush 7 v1.0.
Do not create a new signing key. A new key would prevent normal app updates.

GITHUB WORKFLOW:
Paste FINAL-RELEASE-WORKFLOW.yml into the workflow file under .github/workflows/ in GitHub.
Delete old duplicate Upload APK / Find APK steps. The final workflow contains exactly one artifact upload.

FINAL OUTPUT:
Artifact name: Flip-Rush-7-v1.3-FINAL
APK inside: Flip-Rush-7-v1.3.apk

This is a release APK, not a debug APK.
