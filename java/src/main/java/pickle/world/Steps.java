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
            final String keyString, final String valueString)
            throws ClassNotFoundException {
        final Object keyObject = StringUtil.evaluateString(keyString);
        final Object valueObject = StringUtil.evaluateString(valueString);
        final Class<?> forClass =
                valueObject != null ? valueObject.getClass() : null;
        final String forKey =
                keyObject != null ? keyObject.toString() : null;
        World.Key key = World.Key.forString(forKey);
        if (forClass != null)
            if (key.forClass() != null) {
                if (!key.forClass().isAssignableFrom(forClass))
                    key = new World.Key(forClass, key.forName());
            } else
                key = new World.Key(forClass, key.forName());
        world.putObject(key, valueObject);
    }

    @Then("I should get the World object {string} as {string}")
    public void i_should_get_the_world_object_as(
            final String keyString, final String expectedString)
            throws ClassNotFoundException {
        final Object keyObject = StringUtil.evaluateString(keyString);
        final String forKey =
                keyObject != null ? keyObject.toString() : null;
        final World.Key key = World.Key.forString(forKey);
        final Object expected = StringUtil.evaluateString(expectedString);
        final Object actual = world.getObject(key);
        assertEquals(expected, actual);
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
