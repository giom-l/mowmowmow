package com.mowitnow.core;

import com.mowitnow.components.Lawn;
import com.mowitnow.components.Mower;
import com.mowitnow.components.parsers.LawnParser;
import com.mowitnow.components.parsers.MowerInstructionsParser;
import com.mowitnow.exceptions.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Gardener {

    private static final Logger LOG = LoggerFactory.getLogger(Gardener.class);
    private static final String ARGS_ERROR = "1 argument should be provided";

    private static Lawn lawn;
    private static List<Mower> mowers = new ArrayList<Mower>();


    public static void main(String[] args)  throws IllegalArgumentException{
        try {
            checkParamNumber(args);
            acquireDatum(args[0]);
            for (Mower m: mowers){
                m.lawn = lawn;
                m.start();
                LOG.info(String.format("End position of mower #%d : %s", mowers.indexOf(m)+1, m.toString()));
            }


        } catch (IllegalArgumentException iae) {
            LOG.error(ARGS_ERROR);
            throw iae;

        }
    }

    private static void acquireDatum(String input) {
        try {
            lawn = LawnParser.parse(input);
            mowers = MowerInstructionsParser.parse(input);
        } catch (ParserException | IllegalArgumentException e) {
            LOG.error(e.toString());
        }
    }

    private static void checkParamNumber(String[] args) throws IllegalArgumentException {
        if (args.length != 1) throw new IllegalArgumentException();
    }
}

