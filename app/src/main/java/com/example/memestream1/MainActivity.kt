package com.example.memestream1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        if (Prefs.isBiometricsEnabled(this) && BiometricUtils.canUseBiometrics(this)) {
            BiometricUtils.showPrompt(
                activity = this,
                onSuccess = {
                    showMainLayout()
                },
                onErrorOrCancel = {
                    finish() // Go back to LoginActivity if biometric fails
                }
            )
        } else {
            showMainLayout()
        }
    }

    private fun showMainLayout() {
        setContentView(R.layout.activity_main)

        val btnSignOut = findViewById<Button>(R.id.btnSignOut)
        btnSignOut.setOnClickListener {
            auth.signOut()
            googleSignInClient.signOut().addOnCompleteListener {
                // After sign-out, go back to login screen
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
    }
}
 