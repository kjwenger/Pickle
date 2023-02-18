package pickle.scenario;

import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pickle.World;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Steps {
    private final World world;

    public Steps(final World world) {
        this.world = world;
    }

    @BeforeStep
    public void beforeStep(final Scenario scenario) {
        world.putObject(Scenario.class, scenario.getName(), scenario);
    }

    @AfterStep
    public void afterStep(final Scenario scenario) {
        world.removeObject(scenario);
    }

    @When("I set the scenario name as World object {string}")
    public void i_set_the_scenario_name_as_world_object(
            final String keyString) throws ClassNotFoundException {
        final Scenario scenario = world.getObject(Scenario.class);
        assertNotNull(scenario);
        World.Key key = World.Key.forString(keyString);
        key = new World.Key(String.class, key.forName());
        world.putObject(key, scenario.getName());
    }

    @Then("The scenario should have the URI {string}")
    public void the_scenario_should_have_the_uri(final String uriString)
            throws URISyntaxException {
        final Scenario scenario = world.getObject(Scenario.class);
        assertNotNull(scenario);
        assertEquals(scenario.getUri(), new URI(uriString));
    }

}
