package sml;

public class AbstractInstructionFactory { ;

    private InstructionFactory factory = null;

    public void setFactory(InstructionFactory factory) {
        this.factory = factory;
    }

    public InstructionFactory getFactory(){
        return this.factory;
    }

}
