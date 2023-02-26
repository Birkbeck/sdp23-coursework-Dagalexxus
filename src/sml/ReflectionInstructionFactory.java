package sml;

import java.lang.reflect.Constructor;
import java.util.List;

public class ReflectionInstructionFactory {

    private static ReflectionInstructionFactory factory;

    private ReflectionInstructionFactory(){}

    public static ReflectionInstructionFactory getFactory(){
        if (factory == null){
            factory = new ReflectionInstructionFactory();
        }

        return factory;
    }

    public Instruction createInstruction(String className, List<String> parameters){
        try {
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
        catch (ClassNotFoundException e){
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

}
