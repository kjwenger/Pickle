package pickle.world;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pickle.World;

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
    public void i_set_the_object_named_to(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("I should get the object named {string} as {string}")
    public void i_should_get_the_object_named_as(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
