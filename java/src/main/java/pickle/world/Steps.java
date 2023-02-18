package pickle.world;

import pickle.World;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pickle.utils.StringUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Steps {
    private final World world;

    public Steps(final World world) {
        this.world = world;
    }

    @When("I set the World object {string} to {string}")
    public void i_set_the_world_object_to(
            final String name, final String value) {
        world.putObject(name, value);
    }

    @Then("I should get the World object {string} as {string}")
    public void i_should_get_the_world_object_as(
            final String keyString, final String expected)
            throws ClassNotFoundException {
        final World.Key key = World.Key.forString(keyString);
        assertEquals(expected, world.getObject(key));
    }

    @Then("I should get the class name {string} and the object name {string} for key {string}")
    public void i_should_get_the_class_name_and_the_object_name_for_key(
            final String classNameString,
            final String objectNameString,
            final String keyString)
            throws ClassNotFoundException {
        final World.Key key = World.Key.forString(keyString);
        assertEquals(
                StringUtil.substituteStringLiterals(classNameString),
                key.forClass() != null ? key.forClass().getName() : null);
        assertEquals(
                StringUtil.substituteStringLiterals(objectNameString),
                key.forName());
    }
}
