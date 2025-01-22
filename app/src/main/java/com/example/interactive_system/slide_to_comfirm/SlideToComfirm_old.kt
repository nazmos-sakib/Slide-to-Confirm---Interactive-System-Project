package com.example.interactive_system.slide_to_comfirm

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import kotlin.math.roundToInt

@SuppressLint("UnrememberedMutableState")
@Composable
private fun DraggableControl_old(
    modifier: Modifier,
    progress: Float
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
        val isConfirmed = derivedStateOf { progress >= 0.99f }

        Crossfade(targetState = isConfirmed.value, label = "") {
            if (it) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Confirm Icon",
                    tint = GreenColor86
                )
            } else {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Forward Icon",
                    tint = GreenColor86
                )
            }
        }
    }
}

@Preview
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalWearMaterialApi::class)
 @Composable
fun SlideToConfirm_Old(
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
                .align(Alignment.Center)
                .alpha(1f - progress.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Swipe to Confirm",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        DraggableControl_old(
            modifier = Modifier
                .offset {
                    IntOffset(swipeAbleState.offset.value.roundToInt(), 0)
                }
                .size(dragSize),
            progress = progress.value
        )
    }
}

