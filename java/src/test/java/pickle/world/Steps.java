package pickle.world;

import pickle.World;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Steps {
    private final World world;

    public Steps(final World world) {
        this.world = world;
    }

    @Given("I have a World object")
    public void i_have_a_world_object() {
        assertNotNull(world);
    }

    @When("I set the object named {string} to {string}")
    public void i_set_the_object_named_to(
            final String name, final String value) {
        world.putObject(name, value);
    }

    @Then("I should get the object named {string} as {string}")
    public void i_should_get_the_object_named_as(
            final String name, final String expected) {
        assertEquals(expected, world.getObject(name));
    }
}
