Flip Rush 7 Android v1.2.0 - Microphone Wrapper Update

Live game: https://flip-rush-7-60k8.onrender.com/
Package ID: com.fliprush7.app
Version code: 3
Version name: 1.2.0

This update keeps the existing app shell and game unchanged, while explicitly enabling microphone access for WebRTC voice chat.

Required Android permissions:
- INTERNET
- RECORD_AUDIO
- MODIFY_AUDIO_SETTINGS

Use the SAME four GitHub signing secrets as v1.0/v1.1. Do not generate a new signing key.
Replace the flat source files in the Android GitHub repository, replace the workflow contents with FINAL-RELEASE-WORKFLOW.yml, then run the release workflow.
The artifact will be named Flip-Rush-7-v1.2-Microphone-Release and will contain Flip-Rush-7-v1.2.apk.
