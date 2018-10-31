package com.mowitnow.components;

import com.mowitnow.exceptions.OuterLimitException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Mower {

    private static final Logger LOG = LoggerFactory.getLogger(Mower.class);
    private static final String INVALID_COMMAND = "This command is not valid : %s. Ignoring it.";
    private static final String LAWN_LIMIT_REACHED = "Action not permitted du to lawn limits. Doing nothing...";
    private static final String MOWER_FULL_OUTPUT = "Position : %d %d - Orientation : %s - Instructions : %s";
    private static final String MOWER_POSITION = "%d %d %s";

    @Getter
    public Position position;
    @Setter
    public Lawn lawn;
    @Setter
    public List<String> instructions;


    public Mower(Position position){
        this.position = position;
    }

    @Override
    public String toString(){
        return String.format(MOWER_POSITION,this.position.easting, this.position.northing, this.position.orientation);
    }

    public String toStringFull(){
        return String.format(MOWER_FULL_OUTPUT,this.position.easting, this.position.northing, this.position.orientation, this.instructions.toString());
    }

    public void goForward() throws OuterLimitException{
        Position newPosition = position.goForward();
        if (newPosition.northing <= lawn.height && newPosition.northing >= 0 && newPosition.easting >= 0 && newPosition.easting <= lawn.width){
            this.position = newPosition;
        }
        else {
            throw new OuterLimitException();
        }
    }

    private void turnLeft(){ this.position.turnLeft(); }
    private void turnRight(){ this.position.turnRight(); }

    public void start(){
        for (String instr : instructions){
            try {
                Instruction i = Instruction.getInstructionFromString(instr);
                switch(i){
                    case left:
                        turnLeft();
                        break;
                    case right:
                        turnRight();
                        break;
                    case forward:
                        goForward();
                        break;
                }
            } catch(IllegalArgumentException e){
                LOG.error(INVALID_COMMAND, instr);
            } catch (OuterLimitException e){
                LOG.error(LAWN_LIMIT_REACHED, instr);
            }
        }
    }


}
