package sml;

import java.util.List;

/**
 * The interface for an InstructionFactory to be used in the Translator class.
 *
 * @author Niklas Hassforther
 * @version 1.0
 * @since 1.0
 */
public interface InstructionFactory {
    Instruction createInstruction(String opcode, List<String> parameters);
}
