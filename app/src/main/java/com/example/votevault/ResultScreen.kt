package com.example.votevault // Update with your actual package name

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
fun ResultsScreen() {
    // --- COLORS ---
    val primaryColor = Color(0xFF001C40)
    val primaryContainer = Color(0xFF003067)
    val surfaceColor = Color(0xFFF8F9FA)
    val surfaceContainer = Color(0xFFEDEEEF)
    val surfaceContainerLow = Color(0xFFF3F4F5)
    val surfaceLowest = Color(0xFFFFFFFF)
    val textVariant = Color(0xFF56657B)
    val outlineColor = Color(0xFF747781)

    val winnerGradient = Brush.horizontalGradient(
        colors = listOf(primaryColor, primaryContainer)
    )

    // --- ANIMATION FOR LIVE DOT ---
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val dotAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "alpha"
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
                    Text("VoteVault", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = primaryColor)
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
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Analytics, contentDescription = "Results") },
                    label = { Text("Results") },
                    selected = true, // Results is ACTIVE here
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = primaryColor,
                        indicatorColor = primaryColor
                    )
                )
                NavigationBarItem(icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") }, label = { Text("Profile") }, selected = false, onClick = { })
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
            Column(modifier = Modifier.padding(top = 8.dp)) {
                // Live Pill
                Row(
                    modifier = Modifier
                        .background(primaryColor.copy(alpha = 0.1f), RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(Color.Red)
                            .alpha(dotAlpha) // Animated Alpha!
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("LIVE ELECTION STATUS", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = primaryColor, letterSpacing = 1.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "2024 General Council",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    lineHeight = 40.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Real-time cryptographic verification active. Results are updating as precincts report in.",
                    color = textVariant,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Total Votes Card
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = surfaceLowest,
                    border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.15f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text("TOTAL VOTES CAST", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = textVariant, letterSpacing = 1.sp)
                        Text("3,597", fontSize = 36.sp, fontWeight = FontWeight.Black, color = primaryColor)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("HASH: 7F2A...99CE", fontSize = 10.sp, fontFamily = FontFamily.Monospace, color = textVariant)
                    }
                }
            }

            // --- CANDIDATE STANDINGS ---
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = surfaceLowest,
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Candidate Standings", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Icon(Icons.Default.Info, contentDescription = "Info", tint = outlineColor)
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Candidate 1 (Winner)
                    CandidateResultItem(
                        name = "Dr. Elena Vance",
                        party = "PROGRESSIVE COALITION",
                        percentage = "42.8%",
                        votes = "1,539 VOTES",
                        progress = 0.428f,
                        progressBarBrush = winnerGradient,
                        percentageColor = primaryColor
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Candidate 2
                    CandidateResultItem(
                        name = "Marcus Thorne",
                        party = "SECURITY & STABILITY PARTY",
                        percentage = "36.1%",
                        votes = "1,298 VOTES",
                        progress = 0.361f,
                        progressBarColor = Color(0xFF434750).copy(alpha = 0.4f),
                        percentageColor = Color(0xFF434750)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Candidate 3
                    CandidateResultItem(
                        name = "Sarah Jenkins",
                        party = "INDEPENDENT TECH ALLIANCE",
                        percentage = "21.1%",
                        votes = "760 VOTES",
                        progress = 0.211f,
                        progressBarColor = outlineColor,
                        percentageColor = textVariant
                    )
                }
            }

            // --- CRYPTOGRAPHIC SEAL ---
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = primaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Verified, contentDescription = null, tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text("Cryptographically Sealed", fontWeight = FontWeight.Bold, color = Color.White)
                            Text("Audit trail public and immutable.", fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f))
                        }
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("Verify Audit", color = primaryColor, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }

            // --- REGIONAL PARTICIPATION ---
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = surfaceContainerLow,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text("Regional Participation", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Spacer(modifier = Modifier.height(24.dp))

                    // Map Placeholder
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(16.dp))
                            .background(surfaceContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Map, contentDescription = "Map", modifier = Modifier.size(64.dp), tint = Color.Gray.copy(alpha = 0.5f))
                        // Simulated Heatmap blobs
                        Box(modifier = Modifier.size(100.dp).background(primaryColor.copy(alpha = 0.2f), CircleShape).align(Alignment.Center))
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    RegionItem("North District", "84% Participation", "1,104")
                    Spacer(modifier = Modifier.height(12.dp))
                    RegionItem("West Harbor", "71% Participation", "892")
                    Spacer(modifier = Modifier.height(12.dp))
                    RegionItem("South Heights", "92% Participation", "1,601")
                }
            }

            Spacer(modifier = Modifier.height(48.dp)) // Nav bar padding
        }
    }
}

@Composable
fun CandidateResultItem(
    name: String,
    party: String,
    percentage: String,
    votes: String,
    progress: Float,
    progressBarBrush: Brush? = null,
    progressBarColor: Color = Color.Gray,
    percentageColor: Color
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFEDEEEF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = Color.Gray)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
                    Text(party, fontSize = 10.sp, fontWeight = FontWeight.Medium, color = Color(0xFF56657B), letterSpacing = 0.5.sp)
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(percentage, fontWeight = FontWeight.Black, fontSize = 24.sp, color = percentageColor)
                Text(votes, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF747781))
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Progress Bar Custom Build
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFEDEEEF))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50))
                    .then(
                        if (progressBarBrush != null) Modifier.background(progressBarBrush)
                        else Modifier.background(progressBarColor)
                    )
            )
        }
    }
}

@Composable
fun RegionItem(regionName: String, participation: String, votes: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(regionName, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.Black)
            Text(participation, fontSize = 10.sp, color = Color(0xFF56657B))
        }
        Text(votes, fontWeight = FontWeight.Black, fontSize = 14.sp, color = Color(0xFF001C40))
    }
}