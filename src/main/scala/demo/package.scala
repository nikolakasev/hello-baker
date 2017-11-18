import com.ing.baker.recipe.common.FiresOneOfEvents
import com.ing.baker.recipe.scaladsl.{Event, Ingredient, Interaction, Recipe}

package object demo {

  val milk = Ingredient[String]("milk")
  val eggs = Ingredient[String]("eggs")
  val flour = Ingredient[String]("flour")
  val batter = Ingredient[String]("batter")
  val butter = Ingredient[String]("butter")
  val crepe = Ingredient[String]("crepe")
  val creme = Ingredient[String]("creme")

  val crepeServed = Event("CrepeServed")

  val groceriesDone = new Event("GroceriesDone", Seq(milk, eggs, flour, butter, creme), Some(1))
  val kidsHungry = Event("KidsAreHungry")

  val batterMixed = Event("BatterMixed", batter)
  val crepeCooked = Event("CrepeCooked", crepe)

  val mixFirstThree = Interaction(
    name = "MixFirstThree",
    inputIngredients = Seq(milk, eggs, flour),
    output = FiresOneOfEvents(batterMixed)
  )

  val mixFirstThreeImpl = mixFirstThree implement {
    (milk: String, eggs: String, flour: String) =>
      println(s"mixing $milk, $eggs, and $flour")
      batterMixed.instance("batter")
  }

  val cookCrepe = Interaction(
    name = "CookCrepe",
    inputIngredients = Seq(batter, butter),
    output = FiresOneOfEvents(crepeCooked)
  )

  val cookCrepeImpl = cookCrepe implement {
    (_: String, _: String) =>
      println(s"cooking...")
      crepeCooked.instance("crepe")
  }

  val serveCrepe = Interaction(
    name = "ServeCrepe",
    inputIngredients = Seq(crepe, creme),
    output = FiresOneOfEvents(crepeServed)
  )

  val serveCrepeImpl = serveCrepe implement {
    (crepe: String) =>
      println(s"serving the $crepe")
      crepeServed.instance()
  }

  val crepeRecipe = Recipe("ScaleByTheBayLovesCrepeWithCreme")
    .withInteractions(
      serveCrepe,
      mixFirstThree.withRequiredEvent(kidsHungry),
      cookCrepe
    )
    .withSensoryEvents(groceriesDone, kidsHungry)
}
