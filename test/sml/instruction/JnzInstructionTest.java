package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class JnzInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid(){
        var program = machine.getProgram();
        var labels = machine.getLabels();

        program.add(new MovInstruction(null, EAX, 6));
        program.add(new MovInstruction(null, EBX, 1));
        program.add(new MovInstruction(null, ECX, 1));
        program.add(new MulInstruction("f3", EBX, EAX));
        labels.addLabel("f3", 3);
        program.add(new SubInstruction(null, EAX, ECX));
        program.add(new JnzInstruction(null, EAX, "f3"));

        machine.execute();
        Assertions.assertEquals(720, registers.get(EBX));
    }
}
