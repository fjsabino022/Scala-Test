package unit_tests

import unit_tests.customMatchers.CustomMatchers.{odd, even}

class MatchersSpec extends UnitSpec {

  // Checking equality with matchers
  "Right" should "be equal to right" in {
    val right: Int = 3

    right should equal(3) // can customize equality
    right should ===(3) // can customize equality and enforce type constraints
    right should be(3) // cannot customize equality, so fastest to compile
    right shouldEqual 3 // can customize equality, no parentheses required
    right shouldBe 3 // cannot customize equality, so fastest to compile, no parentheses required
  }

  // Checking size and length
  "The length or size of array" should "be not empty" in {

    val clients: List[Int] = List(10, 11, 12)
    clients should have length 3
    // LENGTH: can be used with String, Array, any java.util.List,
    // and any type T for which an implicit Length[T] type class is available in scope.


    val customers: Map[String, Int] = Map("Federico" -> 1, "Hector" -> 2)
    customers should have size 2
    // SIZE: can be used with Array, any java.util.Collection,
    // any java.util.Map, and any type T for which an implicit Size[T] type class is available in scopE
  }

  // Checking strings
  "All string checks" should "be correct" in {
    val string: String = "Hello seven world"

    string should startWith("Hello")
    string should endWith("world")
    string should include("seven")

    string should startWith regex "Hel*o"
    string should endWith regex "wo.ld"
    string should include regex "wo.ld"
  }

  // Greater and less than
  "All integer checks" should "be correct" in {
    5 should be < 7
    1 should be > 0
    7 should be <= 7
    0 should be >= 0
  }

  // Checking for emptiness
  "All list checks" should "be correct" in {
    List.empty should be(empty)
    List.empty shouldBe empty
    new java.util.HashMap[Int, Int] shouldBe empty
    Array(1, 2, 3) should not be empty
  }

  // Working with "containers"
  "All container checks" should "be correct" in {
    List(1, 2, 3) should contain(2)
    Map('a' -> 1, 'b' -> 2, 'c' -> 3) should contain('b' -> 2)
    Set(1, 2, 3) should not contain 5
    "123" should contain('2')

    List(1, 2, 3, 4, 5) should contain oneOf (5, 7, 9)
  }

  // Working with "aggregations"
  "All aggregations checks" should "be correct" in {
    List(1, 2, 3) should contain atLeastOneOf (2, 3, 4)

    Array(1, 2, 3) should contain atLeastOneOf (3, 4, 5)

    "abc" should contain atLeastOneOf ('c', 'a', 't')

    List(1, 2, 3, 4, 5) should contain atMostOneOf (5, 6, 7)

    List(1, 2, 3, 4, 5) should contain allOf (2, 3, 5)

    List(1, 2, 3, 2, 1) should contain only (1, 2, 3)
  }

  // Working with "sortables"
  "Collection" should "be sorted" in {
    List(1, 2, 3) shouldBe sorted
    List(5, 1, 2, 3) should not be sorted
  }

  // Working with iterators
  "All iterator checks" should "be correct" in {
    var xs: List[Int] = List(1, 2, 3, 4, 5)

    all (xs) should be > 0

    atMost(2, xs) should be >= 4

    atLeast(3, xs) should be < 5

    between(2, 3, xs) should (be > 1 and be < 5)

    exactly (2, xs) should be <= 2

    every (xs) should be < 10

    exactly (2, xs) shouldEqual 2
    // org.scalatest.exceptions.TestFailedException: 'exactly(2)' inspection failed, because only 1 element
  }

  "All customMatcher" should "be correct" in {
    2 should be (even)
    2 shouldBe even
    3 should not be (even)
    3 should be (odd)
  }
}
