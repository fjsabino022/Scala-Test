package unit_tests

import unit_tests.tags.{DbTest, FileTest}

class TagExampleSpec extends UnitSpec {
  "The Scala language" must "add correctly" taggedAs(FileTest) in {
    val sum = 1 + 1
    assert(sum === 2)
  }

  it must "subtract correctly" taggedAs(DbTest, FileTest) in {
    val diff = 4 - 1
    assert(diff === 3)
  }
}
