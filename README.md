# ScalaTest
_(http://www.scalatest.org/)_

El concepto central de scala test es la suite, una colleccion de 0 a muchos test.

La unidad central de composicion en Scala Test is el objeto Suite, que representa la suite de los test.

El objeto Suite declara el metodo "run" y otros metodos del ciclo de vida que definen el camino para escribir y ejecutar los test. Estos metodos del ciclo de vida pueden ser sobreescritos.

#### Running your test

ScalaTest -> IntelliJ Scala plugin

	File -> Settings... -> Plugins -> Scala

Agregamos la dependencia de Scalatest

	- sbt project
	    
	    libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
	
	- maven project
        
        <dependency>
          <groupId>org.scalatest</groupId>
          <artifactId>scalatest_2.11</artifactId>
          <version>3.0.5</version>
          <scope>test</scope>
        </dependency>

	- gradle project
		
		testCompile group: 'org.scalatest', name: 'scalatest_2.12', version: '3.0.5'


#### Selecting testing styles for your project

Se recomienda elegir un set de estilos de test para cada proyecto, un estilo de test para testing de unidad y otro estilo distinto  para test de aceptacion.

EL estilo que tu elijas solo cambia en la forma que se visualiza la estructura del test, pero todo lo demas como matchers, assertions, mixin trains, etc funcionan de la misma manera independientemente del estilo.

Si no queremos elegir un estilo de test, ScalaTest recomienda utilizar el estilo FlatSpec para test de unidad y FeatureSpec para test de aceptacion. Se recomienda por defecto utilizar FlatSpec porque es parecido a la familia de XUnit test en los cuales la mayoria de los desarrolladores estan acostumbrados a utilizar.

**FlatSpec**

~~~	
import org.scalatest.FlatSpec

class SetSpec extends FlatSpec {

  "An empty Set" should "have size 0" in {
    assert(Set.empty.size == 0)
  }

  it should "produce NoSuchElementException when head is invoked" in {
    assertThrows[NoSuchElementException] {
      Set.empty.head
    }
  }
}
~~~

**FeatureSpec**

~~~
class TVSetSpec extends FeatureSpec with GivenWhenThen {

  info("As a TV set owner")
  info("I want to be able to turn the TV on and off")
  info("So I can watch TV when I want")
  info("And save energy when I'm not watching TV")

  feature("TV power button") {
    scenario("User presses power button when TV is off") {

      Given("a TV set that is switched off")
      val tv = new TVSet
      assert(!tv.isOn)

      When("the power button is pressed")
      tv.pressPowerButton()

      Then("the TV should switch on")
      assert(tv.isOn)
    }

    scenario("User presses power button when TV is on") {

      Given("a TV set that is switched on")
      val tv = new TVSet
      tv.pressPowerButton()
      assert(tv.isOn)

      When("the power button is pressed")
      tv.pressPowerButton()

      Then("the TV should switch off")
      assert(!tv.isOn)
    }
  }
}
~~~


#### Writing your first test

Debemos definir la clase de test siempre extendiendo de uno de los estilos posibles de ScalaTest, por ejemplo FlatSpec

EL estilo FlatSpec esta compuesto de una sentencia que especifica el comportamiento requerido y el bloque que verifica ese comportamiento.

_"A Stack" should "pop values in last-in-first-out order"_ -> pordemos usar **should**, **must** or **can**.

Si tenemos muchos test dentro del mismo "titulo" podemos isar **"it"** para referenciar al titulo anterior.
	
_it should "throw NoSuchElementException if an empty stack is popped"_



#### Using assertions

- **assert()**
- **assertResult() {}**: comparar valor esperado con valor actual, alternativa de assert.
- **assertThrows**: para asegurar una porcion de codigo que espera lanzar una excepcion.
- **fail("I've got a bad feeling about this")** 
- **cancel()**: Es identico al fail pero cancel arroja TestCanceledException  mientras que fail arroja una exepcion de tipo 	TestFailedException
- **assume(database.isAvailable)**: por ejemplo si queremos cancelar un test porque un recurso requerido no esta 
    disponible. El metodo assume arrojara TestCanceledException.

#### Tagging your tests

	ScalaTest soporta un tag por defecto: "ignore". Podemos taggear el test con "ignore" para no ejecutar el test temporalmente.

	Podemos crear nuestros propios tags. Cada estilo de test provee un camino distinto para taggear tests por ejemplo en el estilo FlatSpec debemos crear un objeto extendiendo de la clase "Tag" de ScalaTest. Luego de crear este objeto, debemos agregar nuestro tag al test que nosotros queremos taggear antes de la palabra clave "IN". 
	Cuando vamos a ejecutar el test, nos permitira seleccionar los tests asociados a ciertos tags para ejecutar

#### Matchers

	Podemos importar directamente los matchers mediante la palabra clave "with":
		
		class ExampleSpec extends FlatSpec with Matchers { ... }

	O importar la clase Matchers en nuestra seccion de imports.

		import Matchers._

	Un ejmplo de Matcher seria:

		result should equal (3)

		Result es una variable que puede ser de cualquier tipo, si el objeto es un entero de valor 3, la ejecucion continuara, en caso contrario se arrojara una exepcion del tipo TestFailedException causando la falla del test.
		
		Matchers -> usa la palabra clave "should", lenguaje informal haciendo que el codigo sea parecido a una conversacion entre el escritor y el lector.
		MustMatchers -> usa la palabra clave "must", lenguaje mas formal parecido a una escritura de especificaciones.


#### Testing with mock objects

	ScalaMock es el framework nativo de mocking de Scala, es muy facil de integrar a las suites de ScalaTest.
	ScalaTest permite la utilizacion de tres de los mas populares frameworks de mocking de Java: 
		- JMock
		- EasyMock
		- Mockito

	ScalaMock

		Es un framework nativo y open source de mocking que permite mockear objetos y funciones. Soporta tres diferentes estilos:
			1- Function mocks
			2- Proxy (dynamic) mocks
			3- Generated (type-safe) mocks

