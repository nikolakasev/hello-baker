package demo

import java.util.UUID

import akka.actor.ActorSystem
import akka.testkit.TestKit
import com.ing.baker.compiler.RecipeCompiler
import com.ing.baker.runtime.core.{Baker, RuntimeEvent}
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration._

class BakerSpec extends FlatSpec with Matchers {

  implicit val actorSystem = ActorSystem("demoActorSystem")
  implicit val timeout: FiniteDuration = 2.seconds

  "A crepe with cream recipe" should "compile and validate" in {
    val compiledRecipe = RecipeCompiler.compileRecipe(crepeRecipe)

    compiledRecipe.validationErrors should be ('empty)
  }

  "Baker" should "bake the crepe recipe" in {
    val served = RuntimeEvent("CrepeServed", Seq.empty)

    val compiledRecipe = RecipeCompiler.compileRecipe(crepeRecipe)
    val baker = new Baker(compiledRecipe, Seq(mixFirstThreeImpl, cookCrepeImpl, serveCrepeImpl))

    val processId = UUID.randomUUID().toString
    baker.bake(processId)

    baker.handleEvent(processId, kidsHungry.instance())
    baker.handleEvent(processId, groceriesDone.instance("milk", "eggs", "flour", "butter", "creme"))

    TestKit.awaitCond(baker.events(processId) contains served, 2.seconds)
  }
}