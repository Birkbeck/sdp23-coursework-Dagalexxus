package sml;

import java.util.List;

/**
 * The interface for an InstructionFactory to be used in the Translator class.
 */
public interface InstructionFactory {
    public Instruction createInstruction(String opcode, List<String> parameters);
}
