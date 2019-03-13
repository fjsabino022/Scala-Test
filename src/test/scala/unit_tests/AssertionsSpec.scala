package unit_tests

class AssertionsSpec extends UnitSpec {

  "Right" should "be equal to left" in {
    val left = 2
    val right = 1
    assert(left == right)
  }
  // Result: org.scalatest.exceptions.TestFailedException: 2 did not equal 1

  "First letters of words" should "be corrects" in {
    assert("hello".startsWith("h") && "goodbye".endsWith("y"))
  }
  // Result: org.scalatest.exceptions.TestFailedException: "hello" started with "h", but "goodbye" did not end with "y"

  "Attempted" should "be 1" in {
    val attempted = 2

    // assert with error message
    assert(attempted == 1, "Execution was " + attempted + " instead of 1")
  }
  // Result: org.scalatest.exceptions.TestFailedException: 2 did not equal 1 Execution was 2 instead of 1

  "The result of operation" should "be correct" in {
    val a = 5
    val b = 2
    assertResult(2) {
      a - b
    }
    // Result: org.scalatest.exceptions.TestFailedException: Expected 2, but got 3
  }

  "1" should "be 1" in {
    fail()
    fail("I don't know why it happened")
    // Result: org.scalatest.exceptions.TestFailedException: I don't know why it happened
  }

  "CharAt" must "be failure" in {
    val s = "hi"
    assertThrows[IndexOutOfBoundsException] {
      s.charAt(-1)
    }
    // If charAt throws an instance of IndexOutOfBoundsException, assertThrows will return Succeeded
  }
  "CharAt" must "use interceptor" in {
    val s = "hi"
    val caught =
      intercept[IndexOutOfBoundsException] {
        s.charAt(-1)
      }
    assert(caught.getMessage.indexOf("-1") != -1)
    // The intercept method behaves the same as assertThrows, except that instead of returning Succeeded,
    // intercept returns the caught exception so that you can inspect it further if you wish.
  }

}
