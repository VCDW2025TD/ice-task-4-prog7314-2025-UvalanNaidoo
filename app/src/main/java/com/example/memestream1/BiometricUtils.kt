// app/src/main/java/com/example/memestream1/BiometricUtils.kt
package com.example.memestream1

import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

object BiometricUtils {

    fun canUseBiometrics(activity: AppCompatActivity): Boolean {
        val bm = BiometricManager.from(activity)
        val flags = BiometricManager.Authenticators.BIOMETRIC_STRONG or
                BiometricManager.Authenticators.DEVICE_CREDENTIAL
        return bm.canAuthenticate(flags) == BiometricManager.BIOMETRIC_SUCCESS
    }

    fun showPrompt(
        activity: AppCompatActivity,
        title: String = "Verify it’s you",
        subtitle: String = "Use fingerprint/face to continue",
        onSuccess: () -> Unit,
        onErrorOrCancel: (String) -> Unit = {}
    ) {
        val executor = ContextCompat.getMainExecutor(activity)

        val prompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    onSuccess()
                }
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    onErrorOrCancel(errString.toString())
                }
                override fun onAuthenticationFailed() {
                    // Not fatal; the system allows retry
                }
            }
        )

        val info = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setAllowedAuthenticators(
                BiometricManager.Authenticators.BIOMETRIC_STRONG or
                        BiometricManager.Authenticators.DEVICE_CREDENTIAL
            )
            .build()

        prompt.authenticate(info)
    }
}
