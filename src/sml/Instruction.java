package sml;

// TODO: write a JavaDoc for the class

/**
 * Represents an abstract instruction.
 * Represents the basis for an executable instruction. Any instruction to be used in SML will need to extend this (or a subclass of this) class.
 *
 * @author Niklas Hassforther
 * @version 1.0
 * @since 1.0
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// TODO: What does abstract in the declaration below mean?
	//       (Write a short explanation.)
	// Abstract methods will have to be implemented by the classes that inherit from this class unless the class that
	// is inheriting from the abstract class is also declared abstract.
	@Override
	public abstract String toString();

	// TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine).

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object o);
}
