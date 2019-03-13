package unit_tests.mocks.scalaMock

import org.scalamock.scalatest.MockFactory
import unit_tests.{TurtleImpl, UnitSpec}

class GeneratedMockSpec extends UnitSpec with MockFactory {

  "Checks" should "be correct" in {
    val m = mock[TurtleImpl]

    (m.setPosition _).expects(10.0, 10.0).once()

    // Wildcards
    (m.setPosition _).expects(*, *).returning(20.0, 25.0).noMoreThanTwice()

    (m.forward _).expects(5.0).twice()

    // returning
    (m.getPosition _).expects().returning(15.0, 10.0).repeat(1)

    (m.turn _).expects(*).repeat(1 to 3)

    // another way to write
    m.getAngle _ expects() returns 10 once

    m.setPosition(10.0, 10.0)
    m.forward(5.0)
    m.forward(5.0)
    m.turn(10.0)
    m.turn(11.0)

    val angle: Double = m.getAngle
    val (x, y): (Double, Double) = m.getPosition
    val (l, u): (Double, Double) = m.setPosition(1.0, 1.0)

    x should be (15.0)
    y should be (10.0)
    l should be (20.0)
    u should be (25.0)
    angle should be (10)
  }

  "Checks with Predicate matching" should "be correct" in {
    val m = mock[TurtleImpl]

    (m.setPosition _).expects(where {
      (x: Double, y: Double) => (x < y)
    }).once()

    m.setPosition(10.0, 11.0)

    m.setPosition(11.0, 10.0)
    // org.scalatest.exceptions.TestFailedException: Unexpected call: <mock-2> TurtleImpl.setPosition(11.0, 10.0)
  }

  "Checks exception" should "be correct" in {
    val m = mock[TurtleImpl]

    (m.setPosition _).expects(10.0, 10.0).throws(new RuntimeException("What did Polak do...?"))

    an [RuntimeException] should be thrownBy m.setPosition(10.0, 10.0)
  }

  "Checks Ordering" should "be correct" in {
    val m = mock[TurtleImpl]

    inSequence {
        m.getAngle _ expects() returns 10 once()
        m.turn _ expects 12.0 once()
    }

    inAnyOrder {
      m.setPosition _ expects (10.0, 10.0) once()
      m.setPosition _ expects (12.0, 12.0) once()
    }

    val angle: Double = m.getAngle
    m.turn(12.0)

    m.setPosition(12.0, 12.0)
    m.setPosition(10.0, 10.0)

    angle should be (10)
  }
}
