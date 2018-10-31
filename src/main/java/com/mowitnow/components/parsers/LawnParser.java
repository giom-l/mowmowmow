package com.mowitnow.components.parsers;


import com.google.common.base.Splitter;
import com.mowitnow.components.Lawn;
import com.mowitnow.exceptions.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LawnParser {

    private static final Logger LOG = LoggerFactory.getLogger(LawnParser.class);

    private static final String LAWN_REGEX = "^\\s*(\\d+)\\s+(\\d+)\\s*$";
    private static final Pattern lawnPattern = Pattern.compile(LAWN_REGEX);
    private static final Predicate<String> lawnPredicate = lawnPattern.asPredicate();

    private static final String LAWN_PARSING_ERROR = "Error during parsing Lawn";
    private static final String LAWN_NUMBER_ERRORS = "There should be at least and only one Lawn to mow";
    private static final String SEPARATOR = " ";
    private static final String LINE_SEPARATOR = "\\n";
    private static final String LAWN_OUTPUT = "Setting a new Lawn of width : %d and height : %d";

    private static List<String> lawns = new ArrayList<>();

    public static Lawn parse(String input) throws ParserException {
        Lawn tmpLawn = null;
        File f = new File(input);
        if (f.exists()){
            try (Stream<String> stream = Files.lines(Paths.get(input))) {
                tmpLawn = parse(stream);
            } catch (IOException e) {
                throw new ParserException(LAWN_PARSING_ERROR);
            }
        } else {
            List<String> split = Splitter.on(LINE_SEPARATOR).omitEmptyStrings().splitToList(input);
            tmpLawn = parse(split.stream());
        }
        return tmpLawn;
    }

    public static Lawn parse(Stream<String> lines) throws ParserException{
        lawns = lines
                .filter(lawnPredicate)
                .collect(Collectors.toList());

        if (lawns.size() < 1 || lawns.size() > 1) {
            throw new ParserException(LAWN_NUMBER_ERRORS);
        }

        String[] dimensions = lawns.get(0).split(SEPARATOR);
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);

        LOG.debug(String.format(LAWN_OUTPUT, width, height));
        return new Lawn(width, height);

    }
}
