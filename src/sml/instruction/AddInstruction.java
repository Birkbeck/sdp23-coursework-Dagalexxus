package sml.instruction;

import sml.ArithmeticInstruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * Represents the instruction to perform an arithmetic addition operation.
 * @author Niklas Hassforther
 * @version 1.0
 * @since 1.0
 */

public class AddInstruction extends ArithmeticInstruction {
	/**
	 * The operation code of the instruction.
	 */
	public static final String OP_CODE = "add";

	/**
	 * Creates a new addition instruction.
	 *
	 * @param label the label of the instruction. Can be null.
	 * @param result the register in which the result of the computation will be stored. Also provides the first operand.
	 * @param source the register of the computation from which one of the two operands will be taken.
	 */
	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE, result, source);
	}

	/**
	 * Executes the instruction.
	 * @param m - The instance of the machine class on which the instruction is executed.
	 * @return If the operation was successful the NORMAL_PROGRAM_COUNTER_UPDATE will be returned.
	 */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(this.result);
		int value2 = m.getRegisters().get(this.source);
		m.getRegisters().set(this.getResult(), value1 + value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public final boolean equals(Object o){
		if (o == this){
			return true;
		}
		else if (o instanceof AddInstruction other){
			return (Objects.equals(this.getResult(), other.getResult())
			&& Objects.equals(this.getSource(), other.getSource())
			&& Objects.equals(this.getLabel(), other.getLabel())
			&& Objects.equals(this.getOpcode(), other.getOpcode()));
		}
		return false;
	}

}
