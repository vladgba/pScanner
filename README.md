# pScanner

A fast barcode and QR scanner and generator for Android. It reads almost every 1D and 2D format straight from the camera or from an image, and everything is processed on the device.

## Features

- **Live scanning.** Continuous camera scanning with torch. Each new code slides up a card with **Open / Copy / Share**; a code that stays in view doesn't trigger again.
- **Scan from an image** file.
- **Many formats.** QR, Micro QR, Aztec, Data Matrix, PDF417, MaxiCode, EAN/UPC, Code 128/39/93, Codabar, ITF, GS1 DataBar and more, even blurred, rotated or inverted.
- **History.** Scanned and saved codes with their type; open one to show it full-screen with boosted brightness, so another scanner can read it.
- **Generator.** Pick a format, type the content, see a live preview, then share it as a PNG or save it.
- **Honeywell scanners.** On Honeywell mobile computers the hardware trigger works too.
- **No ads, no network, no data collection.**

## Permissions

| Permission | Why | Required |
|---|---|---|
| Camera | Live scanning | Yes, to scan with the camera |
| Honeywell decode | Uses the built-in scan engine on Honeywell devices; ignored elsewhere | No |

pScanner is offline by design: it has no internet permission, and the build removes any network permission a library might add.

### Notes

- History is stored in a local database and excluded from cloud backup.
- The supported formats per direction and the release sizes per ABI are listed in the [guide](docs/GUIDE.md).

## Build

Requires Android 7.0+ (API 24). Built with Kotlin, [zxing-cpp](https://github.com/zxing-cpp/zxing-cpp) for scanning and [ZXing](https://github.com/zxing/zxing) core for generating.

```bash
./gradlew assembleRelease
```

Per-ABI and universal APKs are written to `app/build/outputs/apk/release/`.

## License

See [LICENSE](LICENSE).
