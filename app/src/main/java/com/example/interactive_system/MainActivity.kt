package com.example.interactive_system

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.interactive_system.ui.theme.BlueColorFF
import com.example.interactive_system.ui.theme.GreenColor
import com.example.interactive_system.ui.theme.GreenColorCF
import com.example.interactive_system.ui.theme.InteractivesystemTheme
import com.example.interactive_system.variant_a.VariantAActivity
import com.example.interactive_system.variant_b.VariantBActivity
import kotlin.math.roundToInt
import kotlin.reflect.KClass

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InteractivesystemTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    LandingScreen{ activityClass ->
                        val intent = Intent(baseContext, activityClass)
                        startActivity(intent)
                    }

                }
            }
        }
    }
}



@Composable
fun LandingScreen(
    onClick:(Class<*>)->Unit={}
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = GreenColorCF)
                .clickable {
                    onClick(VariantAActivity::class.java)
                },
            contentAlignment  = Alignment.Center
        ) {
            Text(text = "Variant A", fontSize = 40.sp, color = Color.DarkGray)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = BlueColorFF)
                .clickable {
                    onClick(VariantBActivity::class.java)
                },
            contentAlignment  = Alignment.Center
        ) {
            Text(text = "Variant B",fontSize = 40.sp,color = Color.DarkGray)
        }

    }
}



@Preview
@Composable
fun LandingScreenPreview(){
    LandingScreen()
}











/*

Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Swipe to Button",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ConfirmationButton()
                        }
                    }
 */







