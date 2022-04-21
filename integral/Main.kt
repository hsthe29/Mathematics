import integral.Integral
import integral.UnivariateFunction
import integral.integrate
import kotlin.math.exp

fun main() {
    val eta = 0.0008
    val f:UnivariateFunction<Double> = {x -> exp(- x * x) }
    val result1 = integrate(-20.0..20.0, function = f, features = Integral.TRAPEZOID, eta)
    val result2 = integrate(-20.0..20.0, function = f, features = Integral.PARABOLIC, eta)
    val result3 = integrate(-20.0..20.0, function = f, features = Integral.COMPOSITE_MIDPOINT, eta)
    println(result1)
    println(result2)
    println(result3)
    println("1.7724538509055160272981674")
}