package sml;

import java.util.List;

public interface InstructionFactory {
    public Instruction createInstruction(String opcode, List<String> parameters);
}
