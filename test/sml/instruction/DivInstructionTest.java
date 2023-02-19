package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class DivInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 6);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(1, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -6);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidThree() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction1 = new DivInstruction(null, EAX, EBX);
        Instruction instruction2 = new DivInstruction(null, EAX, EBX);
        Assertions.assertEquals(instruction1, instruction2);
    }

    @Test
    void executeValidFour(){
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction1 = new DivInstruction(null, EAX, EBX);
        Instruction instruction2 = new DivInstruction(null, EAX, EBX);
        Assertions.assertEquals(instruction1.hashCode(), instruction2.hashCode());
    }

    @Test
    void executeValidFive(){
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        registers.set(ECX, 4);
        Instruction instruction1 = new DivInstruction(null, EAX, EBX);
        Instruction instruction2 = new DivInstruction(null, EAX, ECX);
        Assertions.assertNotEquals(instruction1.hashCode(), instruction2.hashCode());
    }

    @Test
    void executeValidSix(){
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        registers.set(ECX, 4);
        Instruction instruction1 = new DivInstruction(null, EAX, EBX);
        Instruction instruction2 = new DivInstruction(null, EAX, ECX);
        Assertions.assertNotEquals(instruction1, instruction2);
    }

    @Test
    void executeValidSeven(){
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new DivInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));

    }
}
