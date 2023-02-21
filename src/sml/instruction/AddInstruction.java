package sml.instruction;

import sml.ArithmeticInstruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class AddInstruction extends ArithmeticInstruction {
	public static final String OP_CODE = "add";

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE, result, source);
	}


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
