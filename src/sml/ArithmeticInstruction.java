package sml;

import java.util.Objects;

/**
 * Abstract class for arithmetic operations requiring the contents of two different registers
 *
 * @author Niklas Hassforther
 */
public abstract class ArithmeticInstruction extends Instruction {
    protected final RegisterName source;
    protected final RegisterName result;

    public ArithmeticInstruction(String label, String opcode, RegisterName result, RegisterName source){
        super(label, opcode);
        this.source = source;
        this.result = result;
    }

    protected RegisterName getResult() {
        return this.result;
    }

    protected RegisterName getSource() {
        return this.source;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public final int hashCode(){
        return Objects.hash(this.result, this.source, this.label, this.opcode);
    }
}
