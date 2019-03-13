package unit_tests.mocks.scalaMock

import org.scalamock.scalatest.MockFactory
import unit_tests.UnitSpec

class FunctionScalaMockSpec extends UnitSpec with MockFactory {

  "Checks" should "be correct" in {
    val m = mockFunction[Int, String]

    m expects 42 returning "Forty two" once

    val result: String = m(42)

    result shouldBe "Forty two"
  }

  "Checks with Predicate matching" should "be correct" in {
    val m = mockFunction[Double, Double, Unit]

    m expects where { _ < _ }

    m(42.0, 45.0)
    // correct

    m(42.0, 40.0)
    // MockFunction2-2<function1> once (never called - UNSATISFIED)
  }

  "Checks with exception" should "be correct" in {
    val m = mockFunction[Int, Int, String]

    m expects (42, 40) throws new RuntimeException("What did Tesss..?")

    an [RuntimeException] should be thrownBy m(42, 40)
  }
}
