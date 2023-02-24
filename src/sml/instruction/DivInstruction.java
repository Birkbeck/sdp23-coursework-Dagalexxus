package sml.instruction;

import sml.ArithmeticInstruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class DivInstruction extends ArithmeticInstruction {
    public static final String OP_CODE = "div";

    public DivInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE, result, source);
    }

    public DivInstruction(String label, String result, String source){
        super(label, OP_CODE, result, source);
    }

    @Override
    public int execute(Machine m) throws ArithmeticException {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        if (value2 == 0){
            throw new ArithmeticException("Division by zero!");
        }
        m.getRegisters().set(result, value1 / value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public final boolean equals(Object o){
        if (o == this){
            return true;
        }
        else if (o instanceof DivInstruction other){
            return (Objects.equals(this.getResult(), other.getResult())
                    && Objects.equals(this.getSource(), other.getSource())
                    && Objects.equals(this.getLabel(), other.getLabel())
                    && Objects.equals(this.getOpcode(), other.getOpcode()));
        }
        return false;
    }
}
