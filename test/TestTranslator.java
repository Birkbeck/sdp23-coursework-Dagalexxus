import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Translator;
import sml.Machine;
import sml.Registers;

import java.io.IOException;
import java.util.List;

public class TestTranslator {
    private Translator translator;
    private Machine machine;

    @BeforeEach
    void setUp(){
        this.machine = new Machine(new Registers());
    }

    @AfterEach
    void tearDown(){
        this.translator = null;
        this.machine = null;
    }

    @Test
    void executeValidTestfile(){
        this.translator = new Translator("test1.sml");
        try {
            this.translator.readAndTranslate(this.machine.getLabels(), this.machine.getProgram());
        }
        // make the test fail when an exception is thrown
        catch (IOException e){
            Assertions.assertEquals(1,0);
        }
        machine.execute();
        Assertions.assertEquals(720, this.machine.getRegisters().get(Registers.Register.valueOf("EBX")));
    }

    @Test
    void executeInvalidTestfile(){
        this.translator = new Translator("test2.sml");
        try {
            this.translator.readAndTranslate(this.machine.getLabels(), this.machine.getProgram());
        }
        // make the test fail when an exception is thrown
        catch (IOException e){
            Assertions.assertEquals(1,0);
        }
        List<Instruction> program = machine.getProgram();
        Assertions.assertEquals(0, program.size());
    }
}
