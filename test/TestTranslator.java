import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.*;

import java.io.IOException;
import java.lang.reflect.Field;

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
    void TestDependencyInjection(){
        this.translator = new Translator("test1.sml");
        try {
            this.translator.readAndTranslate(this.machine.getLabels(), this.machine.getProgram());
        }
        // make the test fail when an exception is thrown
        catch (IOException e){
            Assertions.assertEquals(1,0);
        }
        machine.execute();
        try {
            Field privatefield = Translator.class.getDeclaredField("factory");
            privatefield.setAccessible(true);
            AbstractInstructionFactory factory = (AbstractInstructionFactory) privatefield.get(this.translator);
            privatefield = AbstractInstructionFactory.class.getDeclaredField("factory");
            privatefield.setAccessible(true);
            InstructionFactory factory1 = (InstructionFactory) privatefield.get(factory);
            Assertions.assertTrue(factory1 instanceof ReflectionInstructionFactory);
        }
        catch (Exception e){
            Assertions.assertEquals(1,2);
        }
    }
}
