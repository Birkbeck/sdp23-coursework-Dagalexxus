package sml;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * The interface for an InstructionFactory to be used in the Translator class.
 *
 * @author Niklas Hassforther
 */
public interface InstructionFactory {
    Instruction createInstruction(String opcode, List<String> parameters);
}
