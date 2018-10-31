package com.mowitnow.parsers;

import com.mowitnow.components.Lawn;
import com.mowitnow.components.Mower;
import com.mowitnow.components.parsers.LawnParser;
import com.mowitnow.components.parsers.MowerInstructionsParser;
import com.mowitnow.exceptions.ParserException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParsersTest {
    private String goodDimensions = "5 5";
    private String badDimensions = "5";


    @Test
    public void lawnParser_should_return_Lawn_from_well_formatted_input() throws ParserException {
        Lawn l = LawnParser.parse(goodDimensions);
        assertEquals(5, l.width);
    }

    @Test(expected = ParserException.class)
    public void lawnParser_should_throw_ParserException_from_bad_formatted_input() throws ParserException {
        Lawn l = LawnParser.parse(badDimensions);
    }

    @Test
    public void lawnParser_should_return_Lawn_from_well_formatted_conf_input() throws ParserException {
        String params = getClass().getClassLoader().getResource("confs/valid_args").getFile();
        Lawn l = LawnParser.parse(params);
        assertEquals(5, l.width);
    }


    @Test
    public void mowerInstructionParser_should_return_Mowers_from_well_formatted_input() throws ParserException {
        String params = getClass().getClassLoader().getResource("confs/valid_args").getFile();
        List<Mower> list = MowerInstructionsParser.parse(params);
        assertEquals(2, list.size());
    }


}
