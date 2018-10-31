package com.mowitnow.components.parsers;

import com.google.common.base.Splitter;
import com.mowitnow.components.Mower;
import com.mowitnow.components.Orientation;
import com.mowitnow.components.Position;
import com.mowitnow.exceptions.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MowerInstructionsParser {

    private static final Logger LOG = LoggerFactory.getLogger(MowerInstructionsParser.class);

    private static final String MOWER_POSITION_REGEX = "^\\s*(\\d+)\\s+(\\d+)\\s+([NEWS])\\s*$";
    private static final Pattern mowerPositionPattern = Pattern.compile(MOWER_POSITION_REGEX);
    private static Matcher mowerPositionMatcher;

    private static final String MOWER_POSITION_PARSING_ERROR = "Error during parsing Mowers Positions ";
    private static final String SEPARATOR = " ";
    private static final String LINE_SEPARATOR = "\\n";

    private static List<Mower> mowers = new ArrayList<>();

    public static List<Mower> parse(Stream<String> lines) throws ParserException {
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()){
                String current = iterator.next();
                mowerPositionMatcher = mowerPositionPattern.matcher(current);
                if (mowerPositionMatcher.find()){
                    Mower m = getMowerFromParams(current);
                    List<String> instr = Arrays.asList(iterator.next().split(""));
                    m.instructions = instr;
                    mowers.add(m);
                    LOG.debug(String.format("Adding a new mower #%d :  %s", mowers.indexOf(m)+1, m.toStringFull()));
                }
            }
            if (mowers.size() != 0) {
                return mowers;
            }else throw new ParserException(MOWER_POSITION_PARSING_ERROR);
    }

    public static List<Mower> parse(String input) throws ParserException {
        List<Mower> tmpList = null;
        File f = new File(input);
        if (f.exists()){
            try (Stream<String> stream = Files.lines(Paths.get(input))) {
                tmpList = parse(stream);
            } catch (IOException e) {
                throw new ParserException(MOWER_POSITION_PARSING_ERROR);
            }
        }else {
            List<String> split = Splitter.on(LINE_SEPARATOR).omitEmptyStrings().splitToList(input);
            tmpList = parse(split.stream());
        }
        return tmpList;
    }

    private static Mower getMowerFromParams(String data) throws ParserException{
        String[] params = data.split(SEPARATOR);
        int easting = Integer.parseInt(params[0]);
        int northing = Integer.parseInt(params[1]);
        Orientation orientation = Orientation.getOrientationFromString(params[2]);
        Position p = new Position(easting, northing, orientation);
        return new Mower(p);
    }
}
