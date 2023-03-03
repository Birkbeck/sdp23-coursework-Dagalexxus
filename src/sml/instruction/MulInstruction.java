package sml.instruction;

import sml.ArithmeticInstruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Represents the instruction to perform an arithmetic multiplication operation.
 * @author Niklas Hassforther
 * @version 1.0
 * @since 1.0
 */

public class MulInstruction extends ArithmeticInstruction {
    /**
     * The operation code of the instruction.
     */
    public static final String OP_CODE = "mul";

    /**
     * Creates a new multiplication instruction.
     *
     * @param label the label of the instruction. Can be null.
     * @param result the register in which the result of the computation will be stored. Also provides the first operand.
     * @param source the register of the computation from which one of the two operands will be taken.
     */
    public MulInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE, result, source);
    }

    /**
     * Executes the instruction.
     * @param m - The instance of the machine class on which the instruction is executed.
     * @return If the operation was successful the NORMAL_PROGRAM_COUNTER_UPDATE will be returned.
     */
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

