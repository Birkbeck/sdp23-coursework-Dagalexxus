import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.InstructionFactory;
import sml.ReflectionInstructionFactory;
import sml.Registers;
import sml.exceptions.OpcodeNotFoundException;
import sml.instruction.AddInstruction;

import java.util.ArrayList;
import java.util.List;


public class TestReflectionInstructionFactory {
    InstructionFactory factory = ReflectionInstructionFactory.getFactory();

    @Test
    void ValidTest(){
        Instruction add1 = new AddInstruction(null, Registers.Register.EAX, Registers.Register.EBX);
        List<String> parameters = new ArrayList<>();
        parameters.add(null);
        parameters.add("EAX");
        parameters.add("EBX");

        try {
            Assertions.assertEquals(factory.createInstruction("add", parameters), add1);
        }
        catch (Exception e){
            Assertions.assertEquals(1,0);
        }
    }

    @Test
    void CatchClassNotFoundTest() {
        List<String> parameters = new ArrayList<>();
        parameters.add(null);
        parameters.add("EAX");
        parameters.add("EBX");

        Assertions.assertThrows(OpcodeNotFoundException.class, () -> factory.createInstruction("abc", parameters));
    }
}
