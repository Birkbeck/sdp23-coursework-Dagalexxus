package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

public class OutInstruction extends Instruction {
    private final RegisterName source;

    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    @Override
    public int execute(Machine m){
        System.out.println(m.getRegisters().get(source));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    public RegisterName getSource(){
        return this.source;
    }

    @Override
    public String toString(){
        return getLabelString() + getOpcode() + " " + this.source;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else if (o instanceof OutInstruction other){
            return Objects.equals(this.label, other.getLabel())
                    && Objects.equals(this.opcode, other.getOpcode())
                    && Objects.equals(this.source, other.getSource());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.label, this.opcode, this.source);
    }
}
