package unit_tests.customMatchers

import org.scalatest.matchers.{BeMatcher, MatchResult}

trait CustomMatchers {

  class OddMatcher extends BeMatcher[Int] {
    def apply(left: Int) =
      MatchResult(
        left % 2 == 1,
        left.toString + " was even",
        left.toString + " was odd"
      )
  }
  val odd = new OddMatcher

  class EvenMatcher extends BeMatcher[Int] {
    def apply(left: Int) =
      MatchResult(
        left % 2 == 0,
        left.toString + " was odd",
        left.toString + " was even"
      )
  }
  val even = new EvenMatcher
}

// Make them easy to import with:
// import CustomMatchers._
object CustomMatchers extends CustomMatchers