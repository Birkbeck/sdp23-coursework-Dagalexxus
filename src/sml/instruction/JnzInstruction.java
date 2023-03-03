package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Represents the instruction to perform a jump if zero operation.
 * @author Niklas Hassforther
 * @version 1.0
 * @since 1.0
 */

public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String jumpToLabel;

    /**
     * The operation code of the instruction.
     */
    public final static String OP_CODE = "jnz";

    /**
     * Constructor method to instantiate a new instruction.
     * @param label - the label of the instruction. Can be null.
     * @param source - the register to check whether it is 0 or not.
     * @param jumpToLabel - the label of the instruction to jump to if a jump is required.
     */
    public JnzInstruction(String label, RegisterName source, String jumpToLabel){
        super(label, OP_CODE);
        this.source = source;
        this.jumpToLabel = jumpToLabel;
    }

    public RegisterName getSource() {
        return source;
    }

    public String getJumpToLabel() {
        return jumpToLabel;
    }

    /**
     * Executes the instruction.
     * @param m - The instance of the machine class on which the instruction is executed.
     * @return If the operation does not need a jump the NORMAL_PROGRAM_COUNTER_UPDATE will be returned. Otherwise, the address of the new instruction is returned.
     */
    @Override
    public int execute(Machine m){
        return (m.getRegisters().get(this.source) == 0)
                ? NORMAL_PROGRAM_COUNTER_UPDATE
                : m.getLabels().getAddress(this.jumpToLabel);

    }

    @Override
    public String toString(){
        return this.getLabelString() + this.getOpcode() + " " + this.source + " " + this.jumpToLabel;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else if (o instanceof JnzInstruction other){
            return Objects.equals(this.label, other.getLabel())
                    && Objects.equals(this.opcode, other.getOpcode())
                    && Objects.equals(this.source, other.getSource())
                    && Objects.equals(this.jumpToLabel, other.getJumpToLabel());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.label, this.opcode, this.source, this.jumpToLabel);
    }
}
