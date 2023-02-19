package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int value;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, int value){
        super(label, OP_CODE);
        this.result = result;
        this.value = value;
    }

    public RegisterName getResult() {
        return result;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int execute(Machine m){
        m.getRegisters().set(this.result, this.value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString(){
        return this.getLabelString() + this.getOpcode() + " " + this.result + " " + this.value;
    }

    @Override
    public boolean equals(Object o){
        if ( o == this ){
            return true;
        }
        else if ( o instanceof MovInstruction other){
            return Objects.equals(this.label, other.getLabel())
                    && Objects.equals(this.opcode, other.getOpcode())
                    && Objects.equals(this.value, other.getValue())
                    && Objects.equals(this.result, other.getResult());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.label, this.opcode, this.value, this.result);
    }
}
