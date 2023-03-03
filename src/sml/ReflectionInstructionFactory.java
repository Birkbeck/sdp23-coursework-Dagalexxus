package sml;

import sml.exceptions.OpcodeNotFoundException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Implements the InstructionFactory interface. Uses reflection in order to build the correct instruction as required.
 *
 * @author Niklas Hassforther
 */
public class ReflectionInstructionFactory implements InstructionFactory {

    private static ReflectionInstructionFactory factory;

    private ReflectionInstructionFactory(){}

    public static ReflectionInstructionFactory getFactory(){
        if (factory == null){
            factory = new ReflectionInstructionFactory();
        }

        return factory;
    }

     /**
      * Creates and returns the instruction
      * @param opcode - The operation code of the instruction
      * @param parameters - The required parameters to instantiate a new instruction of the type provided in the opcode.
      * @throws OpcodeNotFoundException - in case the class does not exist.
      * @throws InstantiationException - in case the class can't be instantiated
      * @throws IllegalAccessException - in case the class cannot be accessed through reflection
      * @throws InvocationTargetException - in case there is an issue with the constructor.
      */
    @Override
    public Instruction createInstruction(String opcode, List<String> parameters) throws OpcodeNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException{
        try {
            String className = "sml.instruction." + opcode.substring(0,1).toUpperCase() +opcode.substring(1) + "Instruction";
            Constructor<?>[] constructors = Class.forName(className).getDeclaredConstructors();
            Class<?>[] parameterTypes = constructors[0].getParameterTypes();
            Object[] params = new Object[constructors[0].getParameterCount()];

            for (int n = 0; n < constructors[0].getParameterCount(); n++){
                String parameter = parameters.get(n);
                if (parameter == null){
                    params[n] = null;
                    continue;
                }

                boolean isNotNumber = parameter
                        .chars()
                        .mapToObj(i -> (char) i)
                        .anyMatch(i -> !Character.isDigit(i));

                if (parameterTypes[n].getName().equals("sml.RegisterName")){
                    params[n] = Registers.Register.valueOf(parameter);
                }
                else if (!isNotNumber){
                    params[n] = Integer.parseInt(parameter);
                }
                else{
                    params[n] = parameter;
                }
            }

            return (Instruction) constructors[0].newInstance(params);
        }
        catch (ClassNotFoundException e) {
            throw new OpcodeNotFoundException("Operation" + opcode + " does not exist.\nTerminating.");
        }
    }
}
