package com.example.votevault // Update with your actual package name

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier= Modifier,
    onContinueClicked: (String) -> Unit,
    onBiometricClicked: () -> Unit // This is where our CryptoManager will hook in!
) {
    var sovereignId by remember { mutableStateOf("") }

    // Using a deep blue/grey color palette to match the "Vault" aesthetic
    val primaryColor = Color(0xFF001C40)
    val surfaceColor = Color(0xFFF8F9FA)
    val surfaceVariant = Color(0xFFE1E3E4)




    Surface(
        modifier = Modifier.fillMaxSize(),
        color = surfaceColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .systemBarsPadding(), // Ensures it doesn't overlap the status bar
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- TOP NAVIGATION ---
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = "Shield",
                    tint = primaryColor,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "VoteVault",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = primaryColor
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // --- HERO SECTION ---
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Welcome to the Sovereign Vault",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = primaryColor,
                    lineHeight = 40.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Your digital signature is your authority. Enter to cast your will.",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // --- INPUT FIELD ---
            OutlinedTextField(
                value = sovereignId,
                onValueChange = { sovereignId = it },
                label = { Text("SOVEREIGN ID OR EMAIL", fontWeight = FontWeight.Bold) },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = surfaceVariant,
                    unfocusedContainerColor = surfaceVariant,
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = Color.Transparent
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Fingerprint,
                        contentDescription = null,
                        tint = Color.Gray.copy(alpha = 0.5f)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- PRIMARY ACTION BUTTON ---
            Button(
                onClick = { onContinueClicked(sovereignId) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text("Continue", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- DIVIDER ---
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(modifier = Modifier.weight(1f), color = surfaceVariant)
                Text(
                    text = "SECURE OPTIONS",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    letterSpacing = 1.sp
                )
                Divider(modifier = Modifier.weight(1f), color = surfaceVariant)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- BIOMETRICS BUTTON ---
            Surface(
                onClick = onBiometricClicked,
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                tonalElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD4E3FE)), // Light blue background
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Fingerprint,
                            contentDescription = "Biometrics",
                            tint = primaryColor,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Log in with Biometrics", fontWeight = FontWeight.Bold, color = primaryColor)
                        Text("Touch ID or Face Recognition", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // --- SECURITY SEAL (FOOTER) ---
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 32.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFE7E8E9))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock",
                        tint = primaryColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AES-256 ENCRYPTED & SECURE",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Proprietary of VoteVault Governance Systems",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}