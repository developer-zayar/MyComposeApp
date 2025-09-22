package com.zayar.racetracker.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlin.coroutines.cancellation.CancellationException

class RaceParticipant(
    val name: String,
    val maxProgress: Int = 100,
    val progressDelayMillis: Long = 500L,
    private val progressIncrement: Int = 1,
    private val initialProgress: Int = 0,
) {
    init {
        require(maxProgress > 0) { "maxProgress=$maxProgress; must be a positive number" }
        require(progressIncrement > 0) { "progressIncrement=$progressIncrement; must be a positive number" }
    }

    var currentProgress by mutableStateOf(initialProgress)
        private set

    suspend fun run() {
        while (currentProgress < maxProgress) {
            delay(progressDelayMillis)
            currentProgress += progressIncrement
        }

        // Checking the cancellation of the coroutine
//        try {
//            while (currentProgress < maxProgress) {
//                delay(progressDelayMillis)
//                currentProgress += progressIncrement
//            }
//        } catch (e: CancellationException) {
//            Log.e("RaceParticipant", "$name: ${e.message}")
//            throw e // Always re-throw CancellationException.
//        }
    }

    fun reset() {
        currentProgress = 0
    }
}

val RaceParticipant.progressFactor: Float
    get() = currentProgress / maxProgress.toFloat()