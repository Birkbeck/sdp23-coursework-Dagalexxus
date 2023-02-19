package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.*;

public class OutInstructionTest {
    private Machine machine;
    private Registers registers;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputCapture = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        System.setOut(new PrintStream(outputCapture));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
        System.setOut(standardOut);
    }

    @Test
    void executeValid(){
        registers.set(EAX, 3);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        Assertions.assertEquals("3\n", outputCapture.toString());
    }
}
