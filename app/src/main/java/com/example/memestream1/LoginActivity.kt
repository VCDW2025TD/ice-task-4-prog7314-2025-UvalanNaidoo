// app/src/main/java/com/example/memestream1/LoginActivity.kt
package com.example.memestream1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            if (idToken != null) {
                firebaseAuthWithGoogle(idToken)
            } else {
                Log.e("Login", "ID token is null")
            }
        } catch (e: ApiException) {
            Log.e("Login", "Google sign-in failed", e)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1) FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // 2) Google Sign-In options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // 3) Client
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // 4) Button -> launch
        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnGoogle)
            .setOnClickListener {
                googleSignInLauncher.launch(googleSignInClient.signInIntent)
            }
    }

    override fun onStart() {
        super.onStart()
        // If already signed in, head to Main; it will handle biometric gate
        auth.currentUser?.let { goToMain() }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Ask once to enable biometrics, then proceed.
                    promptAfterSignIn()
                } else {
                    Log.e("Login", "Firebase auth failed", task.exception)
                }
            }
    }

    private fun promptAfterSignIn() {
        if (BiometricUtils.canUseBiometrics(this) && !Prefs.isBiometricsEnabled(this)) {
            BiometricUtils.showPrompt(
                activity = this,
                title = "Enable biometric unlock?",
                subtitle = "Use fingerprint/face next time you open the app",
                onSuccess = {
                    Prefs.setBiometricsEnabled(this, true)
                    goToMain()
                },
                onErrorOrCancel = {
                    Prefs.setBiometricsEnabled(this, false)
                    goToMain()
                }
            )
        } else {
            goToMain()
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    // Optional
    private fun signOutAll() {
        auth.signOut()
        googleSignInClient.signOut()
    }
}
