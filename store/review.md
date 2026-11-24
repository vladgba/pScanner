# pScanner publication review

Reviewed 2026-11-16. Source commit: unavailable (no readable Git metadata).

- Owner must confirm developer identity/contact, production support and privacy URLs, countries, pricing, ads, target age and content rating.
- Audit runtime traffic, dependencies and final merged release manifest before signing off Data safety. Source manifest alone is not a complete data audit.
- Create a signed release AAB, test the release on a device and complete the Play Console testing/review requirements applicable to this account. No release binary was built by this documentation task.

## Source evidence

Manifest: `app/src/main/AndroidManifest.xml`. Build: `app/build.gradle.kts`.

- network: `app/src/main/java/x/vladgba/scanner/pui/PAbout.kt`, `app/src/main/java/x/vladgba/scanner/ui/Actions.kt`
- storage: `app/src/main/java/x/vladgba/scanner/MainActivity.kt`, `app/src/main/java/x/vladgba/scanner/data/History.kt`, `app/src/main/java/x/vladgba/scanner/ui/GeneratePage.kt`
- sharing: `app/src/main/java/x/vladgba/scanner/ui/Actions.kt`
- learning_clipboard: No matches in scanned module sources; absence is not proof.
- speech_translation: No matches in scanned module sources; absence is not proof.
