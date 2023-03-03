package sml;

/**
 * A class that holds the actual InstructionFactory implementation. Can hold any implementation of the InstructionFactory interface.
 *
 * @author Niklas Hassforther
 * @version 1.0
 * @since 1.0
 */
public class AbstractInstructionFactory {

    private InstructionFactory factory = null;

    public void setFactory(InstructionFactory factory) {
        this.factory = factory;
    }

    public InstructionFactory getFactory(){
        return this.factory;
    }

}
