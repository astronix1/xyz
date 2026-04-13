package com.example.votevault // Update with your actual package name

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityScreen() {
    // --- COLORS ---
    val primaryColor = Color(0xFF001C40)
    val surfaceColor = Color(0xFFF8F9FA)
    val surfaceContainer = Color(0xFFEDEEEF)
    val surfaceLowest = Color(0xFFFFFFFF)
    val textVariant = Color(0xFF56657B)
    val errorColor = Color(0xFFBA1A1A)

    val vaultGradient = Brush.linearGradient(
        colors = listOf(Color(0xFF001C40), Color(0xFF003067))
    )

    Scaffold(
        containerColor = surfaceColor,
        topBar = {
            // --- TOP APP BAR ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(surfaceColor.copy(alpha = 0.9f))
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .systemBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Security, contentDescription = "Shield", tint = primaryColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("VoteVault", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = primaryColor)
                }
                IconButton(onClick = { /* Notifications */ }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Alerts", tint = primaryColor)
                }
            }
        },
        bottomBar = {
            // --- BOTTOM NAVIGATION ---
            NavigationBar(
                containerColor = surfaceColor.copy(alpha = 0.9f),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.HowToVote, contentDescription = "Ballot") },
                    label = { Text("Ballot") },
                    selected = false,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.VerifiedUser, contentDescription = "Security") },
                    label = { Text("Security") },
                    selected = true, // Security is ACTIVE here
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = primaryColor,
                        indicatorColor = primaryColor
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Analytics, contentDescription = "Results") },
                    label = { Text("Results") },
                    selected = false,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { }
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // --- HERO SECTION ---
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = "Security & Identity",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = primaryColor,
                    lineHeight = 40.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your digital sovereignty is protected by multi-layered biometric encryption and hardware-level isolation.",
                    color = textVariant,
                    fontSize = 14.sp
                )
            }

            // --- BENTO: SOVEREIGN ID CARD ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(vaultGradient)
                    .padding(24.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column {
                            Text("Sovereign ID", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            Text("Vishal Kumar", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                        }
                        Row(
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                                .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Verified, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("VERIFIED", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        // Dummy QR Code Block
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.QrCode2, contentDescription = "QR", modifier = Modifier.size(64.dp), tint = primaryColor)
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("IDENTITY HASH", color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                            Text(
                                "8f92..3c1a..d4b0",
                                color = Color.White,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }

            // --- BENTO: STATUS INDICATOR ---
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = surfaceLowest,
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFD7E3FF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Fingerprint, contentDescription = "Biometrics", tint = primaryColor, modifier = Modifier.size(36.dp))
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Biometric Identity Verified", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = primaryColor)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Your identity was re-authenticated via hardware-level facial scanning today at 09:41 AM.",
                        fontSize = 12.sp,
                        color = textVariant,
                        textAlign = TextAlign.Center // GETTING ERRROR
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    TextButton(onClick = { /* Run Check */ }) {
                        Text("Run New Check", color = primaryColor, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }

            // --- BENTO: RECENT SESSIONS ---
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = surfaceContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Recent Secure Sessions", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = primaryColor)
                    Spacer(modifier = Modifier.height(16.dp))

                    // Session 1
                    SessionItem(
                        icon = Icons.Default.Smartphone,
                        deviceName = "Pixel 7a - VoteVault Mobile",
                        locationInfo = "Prayagraj, UP • IP: 192.168.1.45",
                        statusText = "Active Now",
                        isCurrent = true,
                        primaryColor = primaryColor,
                        errorColor = errorColor
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Session 2
                    SessionItem(
                        icon = Icons.Default.LaptopMac,
                        deviceName = "MacBook Air M2 - Web Vault",
                        locationInfo = "Gonda, UP • IP: 84.22.190.12",
                        statusText = "Yesterday, 14:22",
                        isCurrent = false,
                        primaryColor = primaryColor,
                        errorColor = errorColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp)) // Bottom padding
        }
    }
}

@Composable
fun SessionItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    deviceName: String,
    locationInfo: String,
    statusText: String,
    isCurrent: Boolean,
    primaryColor: Color,
    errorColor: Color
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEDEEEF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = null, tint = Color(0xFF56657B))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(deviceName, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = primaryColor)
                    Text(locationInfo, fontSize = 11.sp, color = Color(0xFF56657B))
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(statusText, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = if (isCurrent) primaryColor else Color.DarkGray)
                Text(
                    if (isCurrent) "CURRENT SESSION" else "EXPIRED",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF56657B),
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Icon(
                    imageVector = if (isCurrent) Icons.Default.Logout else Icons.Default.Delete,
                    contentDescription = "Action",
                    tint = errorColor,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}