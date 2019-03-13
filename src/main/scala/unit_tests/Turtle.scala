package unit_tests

trait Turtle {
  def penUp(): Unit
  def penDown(): Unit
  def forward(distance: Double): Unit
  def turn(angle: Double): Unit
  def getAngle: Double
  def getPosition: (Double, Double)
  def setPosition(x: Double, y: Double): (Double, Double)
}
