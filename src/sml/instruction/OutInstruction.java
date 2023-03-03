package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Represents the instruction to print out the value of a register.
 * @author Niklas Hassforther
 * @version 1.0
 * @since 1.0
 */
public class OutInstruction extends Instruction {
    private final RegisterName source;

    /**
     * The operation code of the instruction.
     */
    public static final String OP_CODE = "out";

    /**
     * The constructor method to instantiate a new instruction.
     * @param label - the label of the instruction. Can be null.
     * @param source - the register to print out.
     */
    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    /**
     * Executes the instruction.
     * @param m - The instance of the machine class on which the instruction is executed.
     * @return If the operation was successful the NORMAL_PROGRAM_COUNTER_UPDATE will be returned.
     */
    @Override
    public int execute(Machine m){
        System.out.println(m.getRegisters().get(source));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    public RegisterName getSource(){
        return this.source;
    }

    @Override
    public String toString(){
        return getLabelString() + getOpcode() + " " + this.source;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else if (o instanceof OutInstruction other){
            return Objects.equals(this.label, other.getLabel())
                    && Objects.equals(this.opcode, other.getOpcode())
                    && Objects.equals(this.source, other.getSource());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.label, this.opcode, this.source);
    }
}
