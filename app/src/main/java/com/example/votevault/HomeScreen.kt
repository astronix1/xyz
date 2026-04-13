package com.example.votevault

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

// --- DATA MODEL ---
data class Candidate(
    val id: Int,
    val name: String,
    val party: String,
    val votes: String,
    val manifesto: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    // --- STATE ---
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showBiometricSheet by remember { mutableStateOf(false) }
    var selectedCandidate by remember { mutableStateOf<Candidate?>(null) }

    // --- COLORS (Matching your Tailwind config) ---
    val primaryColor = Color(0xFF001C40)
    val surfaceColor = Color(0xFFF8F9FA)
    val cardColor = Color(0xFFFFFFFF)
    val textVariant = Color(0xFF56657B)
    val emeraldGreen = Color(0xFF10B981)

    // --- MOCK DATA ---
    val candidates = listOf(
        Candidate(1, "Dr. Elena Vance", "Green Digital Alliance", "1,402 votes", "\"Vision for a sustainable digital infrastructure.\""),
        Candidate(2, "Marcus Thorne", "Citizens for Transparency", "985 votes", "\"Empowering communities through transparent governance.\""),
        Candidate(3, "Sarah Jenkins", "Future Ed Party", "1,210 votes", "\"Education-first policy for the next generation.\"")
    )

    Scaffold(
        containerColor = surfaceColor,
        snackbarHost = { SnackbarHost(snackbarHostState) },
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

                // Secure Enclave Pill
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFEDEEEF))
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(emeraldGreen)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "SECURE ENCLAVE ACTIVE",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = textVariant
                    )
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
                    selected = true,
                    onClick = { },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = primaryColor,
                        indicatorColor = primaryColor
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.VerifiedUser, contentDescription = "Security") },
                    label = { Text("Security") },
                    selected = false,
                    onClick = { }
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
        // --- MAIN CONTENT ---
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "2024 General Election",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = primaryColor,
                    lineHeight = 36.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Select your preferred candidate for the Digital Infrastructure Council. Your vote is encrypted and sovereign.",
                    color = textVariant,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            items(candidates) { candidate ->
                CandidateCard(
                    candidate = candidate,
                    onVoteClicked = {
                        selectedCandidate = candidate
                        showBiometricSheet = true
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                // --- VERIFICATION SEAL ---
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFFEDEEEF))
                        .border(1.dp, Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(24.dp))
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Lock, contentDescription = "Encrypted", tint = primaryColor)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Verified Identity Token", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = primaryColor)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "VAULT-HASH-7291-XX-0019-V3-ECC-SECP256K1",
                        fontSize = 12.sp,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                        color = textVariant,
                        modifier = Modifier
                            .background(Color(0xFFE1E3E4), RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Your cryptographic session is active and tethered to your biometric signature.",
                        fontSize = 11.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    // --- BIOMETRIC BOTTOM SHEET ---
    if (showBiometricSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBiometricSheet = false },
            containerColor = cardColor,
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp)
                    .padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFD7E3FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Fingerprint,
                        contentDescription = "Fingerprint",
                        tint = primaryColor,
                        modifier = Modifier.size(48.dp)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text("Biometric Verification", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = primaryColor)
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Scan Fingerprint to Cryptographically Sign Vote. This process ensures your vote is immutable and unique.",
                    color = textVariant,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        showBiometricSheet = false
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Vote recorded on blockchain!",
                                actionLabel = "View TX"
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Mock Scan Success", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Cancel Operation",
                    color = textVariant,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { showBiometricSheet = false }
                )
            }
        }
    }
}

@Composable
fun CandidateCard(candidate: Candidate, onVoteClicked: () -> Unit) {
    val primaryColor = Color(0xFF001C40)
    val cardColor = Color(0xFFFFFFFF)

    Surface(
        shape = RoundedCornerShape(24.dp),
        color = cardColor,
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(24.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Placeholder for Profile Image
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFEDEEEF)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.Gray)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(candidate.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = primaryColor)
                        Text(candidate.party, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF56657B))
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFFF3F4F5))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(candidate.votes, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = primaryColor)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = candidate.manifesto,
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Italic,
                    color = Color.DarkGray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onVoteClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                ) {
                    Text("Cast Vote", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}