package com.example.votevault

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.votevault.ui.theme.VoteVaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VoteVaultTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Call your new UI here and apply the padding!
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        onContinueClicked = { sovereignId ->
                            // This runs when they type an ID and hit Continue
                            Log.d("VoteVault", "ID Entered: $sovereignId")
                        },
                        onBiometricClicked = {
                            // This runs when they tap the Fingerprint button
                            Log.d("VoteVault", "Biometric button tapped!")

                        }
                    )

                }
            }
        }
    }
}