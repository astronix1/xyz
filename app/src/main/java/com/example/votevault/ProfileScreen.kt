package com.example.votevault // Update with your actual package name

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    // --- COLORS ---
    val primaryColor = Color(0xFF001C40)
    val primaryFixed = Color(0xFFD7E3FF)
    val surfaceColor = Color(0xFFF8F9FA)
    val surfaceContainer = Color(0xFFEDEEEF)
    val surfaceLowest = Color(0xFFFFFFFF)
    val textVariant = Color(0xFF56657B)
    val errorColor = Color(0xFFBA1A1A)
    val errorContainer = Color(0xFFFFDAD6)

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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Notifications, contentDescription = "Alerts", tint = textVariant, modifier = Modifier.size(28.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.align(Alignment.Center), tint = Color.Gray)
                    }
                }
            }
        },
        bottomBar = {
            // --- BOTTOM NAVIGATION ---
            NavigationBar(
                containerColor = surfaceColor.copy(alpha = 0.9f),
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(icon = { Icon(Icons.Default.HowToVote, contentDescription = "Ballot") }, label = { Text("Ballot") }, selected = false, onClick = { })
                NavigationBarItem(icon = { Icon(Icons.Default.VerifiedUser, contentDescription = "Security") }, label = { Text("Security") }, selected = false, onClick = { })
                NavigationBarItem(icon = { Icon(Icons.Default.Analytics, contentDescription = "Results") }, label = { Text("Results") }, selected = false, onClick = { })
                NavigationBarItem(
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = true, // Profile is ACTIVE here
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = primaryColor,
                        indicatorColor = primaryColor
                    )
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- PROFILE HERO ---
            Spacer(modifier = Modifier.height(24.dp))
            Box(contentAlignment = Alignment.BottomEnd) {
                Box(
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .border(4.dp, surfaceLowest, CircleShape)
                        .background(Color.LightGray)
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.align(Alignment.Center).size(64.dp), tint = Color.Gray)
                }
                Box(
                    modifier = Modifier
                        .offset(x = (-4).dp, y = (-4).dp)
                        .clip(CircleShape)
                        .background(vaultGradient)
                        .border(4.dp, surfaceColor, CircleShape)
                        .padding(6.dp)
                ) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Vishal Kumar", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .background(primaryFixed, RoundedCornerShape(16.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.HowToReg, contentDescription = null, tint = primaryColor, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("VERIFIED VOTER", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = primaryColor, letterSpacing = 1.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- STATUS & TRUST SCORE GRID ---
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Surface(
                    shape = RoundedCornerShape(16.dp), color = surfaceLowest, modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("ACCOUNT STATUS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = textVariant, letterSpacing = 1.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Active", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.History, contentDescription = null, tint = textVariant, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Member since 2022", fontSize = 12.sp, color = textVariant)
                        }
                    }
                }
                Surface(
                    shape = RoundedCornerShape(16.dp), color = surfaceLowest, modifier = Modifier.weight(1f)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("TRUST SCORE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = textVariant, letterSpacing = 1.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text("99.9%", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Secured", fontSize = 10.sp, color = textVariant, modifier = Modifier.padding(bottom = 4.dp))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(50)).background(surfaceContainer)) {
                            Box(modifier = Modifier.fillMaxWidth(0.999f).fillMaxHeight().background(vaultGradient))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- VOTING HISTORY ---
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Voting History", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(start = 8.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Surface(shape = RoundedCornerShape(16.dp), color = surfaceLowest, modifier = Modifier.fillMaxWidth()) {
                    Column {
                        // History Item 1
                        Row(modifier = Modifier.padding(20.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(surfaceContainer), contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Ballot, contentDescription = null, tint = primaryColor)
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text("2024 General Election", fontWeight = FontWeight.Bold, color = Color.Black)
                                    Text("Voted: Nov 05, 2024", fontSize = 12.sp, color = textVariant)
                                }
                            }
                            Row(modifier = Modifier.background(Color(0xFFE6F4EA), RoundedCornerShape(16.dp)).padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF137333), modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("CONFIRMED", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF137333))
                            }
                        }
                        Divider(color = surfaceContainer, modifier = Modifier.padding(horizontal = 20.dp))
                        // History Item 2
                        Row(modifier = Modifier.padding(20.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)).background(surfaceContainer), contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Ballot, contentDescription = null, tint = textVariant)
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text("2022 Midterm Election", fontWeight = FontWeight.Bold, color = Color.DarkGray)
                                    Text("Voted: Nov 08, 2022", fontSize = 12.sp, color = textVariant)
                                }
                            }
                            Row(modifier = Modifier.background(surfaceContainer, RoundedCornerShape(16.dp)).padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Archive, contentDescription = null, tint = textVariant, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("ARCHIVED", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = textVariant)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- SECURITY PREFERENCES ---
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Security Preferences", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black, modifier = Modifier.padding(start = 8.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Surface(shape = RoundedCornerShape(16.dp), color = surfaceLowest, modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Row(modifier = Modifier.clickable { }.padding(20.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Fingerprint, contentDescription = null, tint = primaryColor)
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text("Manage Biometrics", fontWeight = FontWeight.Bold, color = Color.Black)
                                    Text("FaceID & TouchID configured", fontSize = 12.sp, color = textVariant)
                                }
                            }
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
                        }
                        Divider(color = surfaceContainer, modifier = Modifier.padding(horizontal = 20.dp))
                        Row(modifier = Modifier.clickable { }.padding(20.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Pin, contentDescription = null, tint = primaryColor)
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text("Security PIN", fontWeight = FontWeight.Bold, color = Color.Black)
                                    Text("Update secondary access code", fontSize = 12.sp, color = textVariant)
                                }
                            }
                            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                // Encrypted Hash Seal
                Row(
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(surfaceLowest).border(1.dp, primaryColor.copy(alpha = 0.1f), RoundedCornerShape(16.dp)).padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.VpnKey, contentDescription = null, tint = primaryColor)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("ENCRYPTED HASH SEAL", fontSize = 10.sp, fontWeight = FontWeight.Black, color = primaryColor.copy(alpha = 0.4f))
                        Text("8f2b...d9a1c4e0f7...21b4", fontSize = 12.sp, fontFamily = FontFamily.Monospace, color = primaryColor, fontWeight = FontWeight.Medium)
                    }
                    Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = textVariant, modifier = Modifier.size(20.dp).clickable { })
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- LOGOUT BUTTON ---
            Button(
                onClick = { /* Sign Out */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = errorContainer),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Sign Out Securely", color = errorColor, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(48.dp)) // Extra padding for bottom nav
        }
    }
}