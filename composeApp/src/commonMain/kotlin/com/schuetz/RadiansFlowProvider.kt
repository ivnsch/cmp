package com.schuetz

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.PI
import kotlin.random.Random

object RadiansFlowProvider {
    val radiansFlow: Flow<Double> = flow {
        while (true) {
            emit(Random.nextDouble(0.0, 2 * PI))
            kotlinx.coroutines.delay(2000)
        }
    }
}
