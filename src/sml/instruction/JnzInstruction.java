package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String jumpToLabel;

    public final static String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName source, String jumpToLabel){
        super(label, OP_CODE);
        this.source = source;
        this.jumpToLabel = jumpToLabel;
    }

    public JnzInstruction(String label, String source, String jumpToLabel){
        super(label, OP_CODE);
        this.source = Registers.Register.valueOf(source);
        this.jumpToLabel = jumpToLabel;
    }

    public RegisterName getSource() {
        return source;
    }

    public String getJumpToLabel() {
        return jumpToLabel;
    }

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
