package x.vladgba.scanner

import android.app.Activity
import android.app.Instrumentation
import android.graphics.Bitmap
import android.os.Bundle
import x.vladgba.scanner.engine.CodeRenderer
import x.vladgba.scanner.engine.LumaFrame
import x.vladgba.scanner.engine.ZXingCppEngine

/** Exercises the real native decoder, including bitmap replacement and ARGB fallback reuse. */
class MemoryInstrumentation : Instrumentation() {
    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        start()
    }

    override fun onStart() {
        val result = Bundle()
        try {
            val engine = ZXingCppEngine()
            val text = "pScanner memory regression"
            for (target in listOf(320, 480, 320)) {
                val bitmap = CodeRenderer.render("QR_CODE", text, "M", target)
                try {
                    check(engine.decodeBitmap(bitmap)?.text == text)
                    val rgb565 = bitmap.copy(Bitmap.Config.RGB_565, false)
                    try {
                        check(engine.decodeBitmap(rgb565)?.text == text)
                        check(!rgb565.isRecycled) { "Decoder recycled the caller's bitmap" }
                    } finally { rgb565.recycle() }
                    val pixels = IntArray(bitmap.width * bitmap.height)
                    bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
                    val frame = LumaFrame(ByteArray(pixels.size) { pixels[it].toByte() },
                        bitmap.width, bitmap.height, 0)
                    // A build without ALPHA_8 support switches to ARGB on the first read.
                    engine.decode(frame)
                    // Exercise fallback even when this device supports ALPHA_8 directly.
                    ZXingCppEngine::class.java.getDeclaredField("argbFallback").apply {
                        isAccessible = true
                        setBoolean(engine, true)
                    }
                    repeat(12) { check(engine.decode(frame)?.text == text) }
                } finally { bitmap.recycle() }
            }
            result.putString("stream", "PASS: still images, caller ownership, repeated frames and size changes\n")
            finish(Activity.RESULT_OK, result)
        } catch (error: Throwable) {
            result.putString("stream", "FAIL: ${error.stackTraceToString()}\n")
            finish(Activity.RESULT_CANCELED, result)
        }
    }
}
