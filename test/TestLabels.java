import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Labels;

public class TestLabels {

    private Labels labels;

    @BeforeEach
    void setUp() {
        this.labels = new Labels();
    }

    @AfterEach
    void tearDown() {
        this.labels = null;
    }

    @Test
    void toStringTest(){
        this.labels.addLabel("f4:", 2);
        this.labels.addLabel("f5:", 5);
        String output = this.labels.toString();
        Assertions.assertEquals("[f4: -> 2, f5: -> 5]", output);
    }

    @Test
    void toStringTestEmpty(){
        String output = this.labels.toString();
        Assertions.assertEquals("[]", output);
    }

    @Test
    void equalsTest(){
        this.labels.addLabel("f4:", 2);
        this.labels.addLabel("f5:", 5);
        Labels other = new Labels();
        other.addLabel("f4:", 2);
        other.addLabel("f5:", 5);
        Assertions.assertEquals(other, this.labels);
    }

    @Test
    void hashCodeTest(){
        this.labels.addLabel("f4:", 2);
        this.labels.addLabel("f5:", 5);
        Labels other = new Labels();
        other.addLabel("f4:", 2);
        other.addLabel("f5:", 5);
        Assertions.assertEquals(other.hashCode(), this.labels.hashCode());
    }

    @Test
    void exceptionTest(){
        Assertions.assertThrows(NullPointerException.class, () -> labels.getAddress("f5:"));
    }
}
