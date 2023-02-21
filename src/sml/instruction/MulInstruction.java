package sml.instruction;

import sml.ArithmeticInstruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class MulInstruction extends ArithmeticInstruction {
    public static final String OP_CODE = "mul";

    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE, result, source);
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public final boolean equals(Object o){
        if (o == this){
            return true;
        }
        else if (o instanceof MulInstruction other){
            return (Objects.equals(this.getResult(), other.getResult())
                    && Objects.equals(this.getSource(), other.getSource())
                    && Objects.equals(this.getLabel(), other.getLabel())
                    && Objects.equals(this.getOpcode(), other.getOpcode()));
        }
        return false;
    }

}

