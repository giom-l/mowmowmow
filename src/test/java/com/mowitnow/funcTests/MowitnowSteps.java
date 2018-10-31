package com.mowitnow.funcTests;

import com.mowitnow.components.Lawn;
import com.mowitnow.components.Mower;
import com.mowitnow.components.Orientation;
import com.mowitnow.components.Position;
import com.mowitnow.exceptions.ParserException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MowitnowSteps {

    private MowitnowTest mowitnow;
    private Lawn lawn;
    private Mower mower;

    private static final String MOWER_POSITION = "%d %d %s";

//    Given a lawn of <width> and <height>
//    And a mower initially starting at (<mowerStartingEasting>,<mowerStartingNorthing>) and oriented <mowerStartingOrientation>
//    When I run the mower with <instructions>
//    Then the mower should finish its job at (<mowerEndingEasting>, <mowerEndingNorthing>) and oriented <mowerEndingOrientation>

    @Given("a lawn of (\\d+) width and (\\d+) height")
    public void initialize_lawn(int width, int height){
        this.lawn = new Lawn(width, height);
    }
    @And("a mower initially starting at (\\d+),(\\d+) and oriented ([NEWS])")
    public void initialize_mower(int mowerStartingEasting, int mowerStartingNorthing, String mowerStartingOrientation) throws ParserException {
        Position p = new Position(mowerStartingEasting, mowerStartingNorthing, Orientation.getOrientationFromString(mowerStartingOrientation));
        mower = new Mower(p);
    }

    @When("I run the mower with ([ADG]*)")
    public void mow(String instructions){
        mower.instructions = Arrays.asList(instructions.split(""));
        mower.lawn = lawn;
        mower.start();
    }

    @Then("the mower should finish its job at (\\d+),(\\d+) and oriented ([NEWS])")
    public void display_ending_position(int mowerEndingEasting, int mowerEndingNorthing, String mowerEndingOrientation) throws ParserException {
        String actualState = mower.toString();
        String expectedState = String.format(MOWER_POSITION, mowerEndingEasting, mowerEndingNorthing, Orientation.getOrientationFromString(mowerEndingOrientation));
        assertEquals(expectedState,actualState);
    }





}
