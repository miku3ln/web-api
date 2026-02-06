package com.example.mobile.core.config

import com.example.mobile.core.device.DeviceDetect

object ApiConfig {

    // Puertos (según tus profiles .NET)
    private const val PORT_HTTP = 5035
    private const val PORT_HTTPS = 7041

    // Hosts especiales
    private const val EMULATOR_HOST = "192.168.0.100"     // Android Emulator -> PC
    private const val GENYMOTION_HOST = "192.168.0.100"   // Genymotion -> PC

    /**
     * ⚠️ Para CELULAR FÍSICO: pon aquí la IP de tu PC en la red (WiFi)
     * Ej: 192.168.1.20
     */
    @Volatile var deviceHostIp: String = "192.168.0.100"

    /**
     * Ambiente: por defecto detecta emulador vs celular
     */
    @Volatile var env: ApiEnv = if (DeviceDetect.isEmulator()) ApiEnv.EMULATOR else ApiEnv.DEVICE

    /**
     * Recomendado en dev: HTTP (evita líos de certificado https)
     */
    @Volatile var useHttps: Boolean = false

    fun baseUrl(): String {
        val host = when (env) {
            ApiEnv.EMULATOR -> EMULATOR_HOST
            ApiEnv.GENYMOTION -> GENYMOTION_HOST
            ApiEnv.DEVICE -> deviceHostIp
        }

        val port = if (useHttps) PORT_HTTPS else PORT_HTTP
        val scheme = if (useHttps) "https" else "http"

        return "$scheme://$host:$port/"
    }
}
