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
    private final RegisterName source;
    private final RegisterName result;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName source, RegisterName result){
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    public RegisterName getResult() {
        return result;
    }

    public RegisterName getSource() {
        return source;
    }

    @Override
    public int execute(Machine m){
        int value = m.getRegisters().get(this.source);
        m.getRegisters().set(this.result, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString(){
        return this.getLabelString() + this.getOpcode() + " " + this.source + " " + this.result;
    }

    @Override
    public boolean equals(Object o){
        if ( o == this ){
            return true;
        }
        else if ( o instanceof MovInstruction other){
            return Objects.equals(this.label, other.getLabel())
                    && Objects.equals(this.opcode, other.getOpcode())
                    && Objects.equals(this.source, other.getSource())
                    && Objects.equals(this.result, other.getResult());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.label, this.opcode, this.source, this.result);
    }
}
