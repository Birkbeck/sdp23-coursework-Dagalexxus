package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "add";

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	protected RegisterName getResult() {
		return this.result;
	}

	protected RegisterName getSource() {
		return source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result + " " + source;
	}


	@Override
	public final boolean equals(Object o){
		if (o == this){
			return true;
		}
		else if (o instanceof AddInstruction other){

			if (other.getLabel().equals(this.getLabel())
				&& other.getOpcode().equals(this.getOpcode())
				&& other.getResult().equals((this.getResult()))
				&& other.getSource().equals((this.getResult()))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public final int hashCode(){
		return 1;
	}
}
