package com.example.mobile.core.device

object DeviceDetect {

    fun isEmulator(): Boolean {
        val fingerprint = android.os.Build.FINGERPRINT
        val model = android.os.Build.MODEL
        val brand = android.os.Build.BRAND
        val device = android.os.Build.DEVICE
        val product = android.os.Build.PRODUCT
        val manufacturer = android.os.Build.MANUFACTURER

        return fingerprint.startsWith("generic")
                || fingerprint.startsWith("unknown")
                || model.contains("google_sdk", ignoreCase = true)
                || model.contains("Emulator", ignoreCase = true)
                || model.contains("Android SDK built for x86", ignoreCase = true)
                || manufacturer.contains("Genymotion", ignoreCase = true)
                || (brand.startsWith("generic") && device.startsWith("generic"))
                || product.contains("sdk_gphone", ignoreCase = true)
    }
}
