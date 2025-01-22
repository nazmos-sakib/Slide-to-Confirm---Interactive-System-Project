package com.example.interactive_system.slide_to_comfirm

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.interactive_system.slide_to_comfirm.model.ConfirmationState
import com.example.interactive_system.ui.theme.GreenColor86
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("UnrememberedMutableState")
@Composable
private fun DraggableControl(
    modifier: Modifier,
    conformationState:  ConfirmationState
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 2.dp,
                CircleShape,
                clip = false
            )
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {


        Crossfade(targetState = conformationState, label = "") {isConfirmedState ->

            when(isConfirmedState){
                ConfirmationState.DEFAULT -> {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Forward Icon",
                        tint = GreenColor86
                    )
                }
                ConfirmationState.LOADING -> {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
                ConfirmationState.CONFIRMED -> {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Confirm Icon",
                        tint = GreenColor86
                    )
                }
            }
        }
    }
}

@Preview
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalWearMaterialApi::class)
 @Composable
fun SlideToConfirm(
    modifier: Modifier = Modifier
) {
    val width = 350.dp
    val dragSize = 50.dp
    val swipeAbleState = rememberSwipeableState(initialValue = ConfirmationState.DEFAULT)
    val sizePx = with(LocalDensity.current) {
        (width - dragSize).toPx()
    }
    val anchors = mapOf(0f to ConfirmationState.DEFAULT, sizePx to ConfirmationState.CONFIRMED)
    val progress = derivedStateOf {
        if (swipeAbleState.offset.value == 0f) 0f else swipeAbleState.offset.value / sizePx
    }

    val conformationState = remember {
        mutableStateOf(ConfirmationState.DEFAULT)
    }


    // Detect release by observing when animation stops
    LaunchedEffect(swipeAbleState.isAnimationRunning) {
        //Log.d("TAG->", "SlideToConfirm: LaunchedEffect")
        if (!swipeAbleState.isAnimationRunning && progress.value>.95f) {
            Log.d("TAG->", "SlideToConfirm: LaunchedEffect")
            conformationState.value = ConfirmationState.LOADING
        }
        else conformationState.value = ConfirmationState.DEFAULT
    }


    var dynamicText by remember {
        mutableStateOf("")
    }

    var job: Job? by remember { mutableStateOf(null) }
    val scope = rememberCoroutineScope() // Create a coroutine scope
    LaunchedEffect(conformationState.value) {
        Log.d("TAG", "SlideToConfirm: ${conformationState.value.toString()}")

        when(conformationState.value){
            ConfirmationState.DEFAULT -> {
                swipeAbleState.animateTo(ConfirmationState.DEFAULT)
                dynamicText = "Release"
                job?.cancel()
            }
            ConfirmationState.LOADING ->{
                dynamicText = "Cancel"
                job = scope.launch {
                    delay(4000)
                    conformationState.value = ConfirmationState.CONFIRMED
                }
            }
            ConfirmationState.CONFIRMED -> {
                dynamicText = "Confirmed"
                //swipeAbleState.animateTo(ConfirmationState.CONFIRMED)
            }
        }
    }


    Box(
        modifier = modifier
            .width(width)
            .swipeable(
                state = swipeAbleState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.99f) },
                orientation = Orientation.Horizontal,
                reverseDirection = false
            )
            .background(GreenColor86, RoundedCornerShape(dragSize))
    ) {
        Column(
            Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment= Alignment.Center) {
                Text(
                    text = "Swipe to Confirm",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .alpha(1f - progress.value)
                )
                if (progress.value>0.99f){
                    Text(
                        text = dynamicText,
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.clickable {
                            conformationState.value = ConfirmationState.DEFAULT
                        }
                    )
                }
            }

        }

        DraggableControl(
            modifier = Modifier
                .offset {
                    IntOffset(swipeAbleState.offset.value.roundToInt(), 0)
                }
                .size(dragSize),
            conformationState = conformationState.value
        )
    }
}

