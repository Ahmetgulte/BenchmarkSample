package com.example.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScrollBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun defaultCompilation() = scroll(CompilationMode.DEFAULT)

    private fun scroll(compilationMode: CompilationMode) {
        var firstStart = true
        benchmarkRule.measureRepeated(
            packageName = "com.example.benchmarksample",
            metrics = listOf(
                StartupTimingMetric(),
                FrameTimingMetric()
            ),
            compilationMode = compilationMode,
            startupMode = null,
            iterations = 4,
            setupBlock = {
                if (firstStart) {
                    startActivityAndWait()
                    firstStart = false
                }
            }
        ) {
            val scrollableObject = device.findObject(By.res("container"))
            if (scrollableObject == null) {
                TestCase.fail("No scrollable view found in hierarchy")
            }
            scrollableObject.setGestureMargin(device.displayWidth / 5)
            scrollableObject?.apply {
                repeat(2) {
                    scroll(Direction.DOWN, 1.0F)
                }
                repeat(2) {
                    scroll(Direction.UP, 1.0F)
                }
            }
        }
    }
}