import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.ReflectionInstructionFactory;
import sml.Registers;
import sml.exceptions.OpcodeNotFoundException;
import sml.instruction.AddInstruction;
import sml.instruction.MovInstruction;

import java.util.ArrayList;
import java.util.List;


public class TestReflectionInstructionFactory {
    ReflectionInstructionFactory factory = ReflectionInstructionFactory.getFactory();

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
    void ValidTest2(){
        Instruction mov = new MovInstruction(null, Registers.Register.EAX, 3);
        List<String> parameters = new ArrayList<>();
        parameters.add(null);
        parameters.add("EAX");
        parameters.add("3");

        try {
            Assertions.assertEquals(factory.createInstruction("mov", parameters), mov);
        }
        catch (Exception e){
            Assertions.assertEquals(1,2);
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
