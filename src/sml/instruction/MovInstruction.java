package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Represents the instruction to load a register with a value.
 * @author Niklas Hassforther
 * @version 1.0
 * @since 1.0
 */

public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int value;

    /**
     * The operation code of the instruction.
     */
    public static final String OP_CODE = "mov";

    /**
     * The constructor to instantiate a new instruction.
     * @param label - the label of the instruction. Can be null.
     * @param result - the target register in which to load the value
     * @param value - the value to load into the register.
     */
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

    /**
     * Executes the instruction.
     * @param m - The instance of the machine class on which the instruction is executed.
     * @return If the operation was successful the NORMAL_PROGRAM_COUNTER_UPDATE will be returned.
     */
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
