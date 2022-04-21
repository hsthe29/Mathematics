package integral

import kotlin.math.ceil

typealias UnivariateFunction<T> = (T) -> T

enum class Integral {
    TRAPEZOID,
    COMPOSITE_MIDPOINT,
    PARABOLIC
}

private fun integral1(start: Double, end: Double, function: (Double) -> Double, eta: Double): Double{
    val n_ranges = ceil((end - start) / eta).toLong()
    val delta_h = (end - start) / n_ranges
    var sum = (function(start) + function(end)) / 2
    for(i in 1 until n_ranges) {
        val tempX = start + i * delta_h
        sum += function(tempX)
    }
    return sum * delta_h
}

private fun integral2(start: Double, end: Double, function: (Double) -> Double, eta: Double): Double{
    val n_ranges = ceil((end - start) / eta).toLong()
    val dn = n_ranges * 2
    val delta_h = (end - start) / dn
    var sum = function(start) + function(end)
    for(i in 1 until dn) {
        val tempX = start + i * delta_h

        sum += if((i and 1).toInt() == 1) 4 * function(tempX)
                else 2 * function(tempX)
    }
    return sum * delta_h / 3
}

private fun integral3(start: Double, end: Double, function: (Double) -> Double, eta: Double): Double{
    val n_ranges = ceil((end - start) / eta).toLong()
    val delta_h = (end - start) / n_ranges
    var sum = 0.0
    for(i in 0 until  n_ranges) {
        val tempX = start + i * delta_h + delta_h / 2
        sum += function(tempX)
    }
    return sum * delta_h
}

fun integrate(range: ClosedRange<Double>,
              function: (Double) -> Double,
              features: Integral = Integral.PARABOLIC,
              eta: Double = 0.0008): Double =
    when (features) {
        Integral.TRAPEZOID -> integral1(range.start, range.endInclusive, function = function, eta)
        Integral.PARABOLIC -> integral2(range.start, range.endInclusive, function = function, eta)
        Integral.COMPOSITE_MIDPOINT -> integral3(range.start, range.endInclusive, function = function, eta)
    }

