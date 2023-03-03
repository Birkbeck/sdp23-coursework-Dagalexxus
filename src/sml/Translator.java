package sml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * This class
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Niklas Hassforther
 */
public final class Translator {

    private final String fileName; // source file of SML code

    private AbstractInstructionFactory factory;

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    /**
     * Constructor to instantiate a new Translator object
     *
     * @param fileName - The name of the file in which the program to translate is stored
     */
    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    /**
     * Setter method to add the factory that provides the instructions.
     *
     * @param factory - The abstract factory that will provide the required instructions.
     */
    public void setFactory(AbstractInstructionFactory factory) {
        this.factory = factory;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    /**
     * This method reads the file for new instructions and translates them into an executable program
     *
     * @param labels - the collection of the labels defined in the
     * @param program - A list of instructions in executable form.
     * @throws IOException - thrown if the file provided is not readable.
     */
    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) throws NumberFormatException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (line.isEmpty())
            return null;

        String opcode = scan();

//        switch (opcode) {
//            case AddInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }

            // TODO: add code for all other types of instructions
//            case SubInstruction.OP_CODE ->  {
//                String r = scan();
//                String s = scan();
//                return new SubInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//
//            case MulInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new MulInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//
//            case DivInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new DivInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//
//            case MovInstruction.OP_CODE -> {
//                String r = scan();
//                int val;
//                try {
//                    val = Integer.parseInt(scan());
//                    return new MovInstruction(label, Register.valueOf(r), val);
//                } catch (NumberFormatException e) {
//                    throw new NumberFormatException("MovInstruction requires an integer value as second argument.");
//                }
//            }
//
//            case OutInstruction.OP_CODE ->{
//                    String s = scan();
//                    return new OutInstruction(label, Register.valueOf(s));
//            }
//
//            case JnzInstruction.OP_CODE -> {
//                String s = scan();
//                String jumpTo = scan();
//                return new JnzInstruction(label, Register.valueOf(s), jumpTo);
//            }

            // TODO: Then, replace the switch by using the Reflection API
            /*String className = "sml.instruction." + opcode.substring(0,1).toUpperCase() +opcode.substring(1) + "Instruction";

            try {
                Constructor<?>[] constructors = Class.forName(className).getDeclaredConstructors();
                List<String> parameters = new ArrayList<>();
                parameters.add(label);
                for (int n = 1; n < constructors[0].getParameterCount(); n++){
                    String argument = scan();
                    parameters.add(argument);
                }
                ReflectionInstructionFactory factory = ReflectionInstructionFactory.getFactory();
                return factory.createInstruction(className, parameters);
            }
            catch (ClassNotFoundException e){
                System.out.println(e);
            }
            catch (Exception e){
                System.out.println(e);
            }*/



        // TODO: Next, use dependency injection to allow this machine class
        //       to work with different sets of opcodes (different CPUs)
        // get BeanFactory
        if (this.factory == null){
            ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");
            this.setFactory((AbstractInstructionFactory) context.getBean("abstractInstructionFactory"));
        }
        List<String> parameters = new ArrayList<>();
        parameters.add(label);
        while (!line.isEmpty()) {
            parameters.add(scan());
            if (!Character.isWhitespace(line.charAt(0))) {
                line = "";
            }
        }

        return this.factory.getFactory().createInstruction(opcode, parameters);


//            default -> {
//                System.out.println("Unknown instruction: " + opcode);
//            }
//        }
    }

    /**
     * Scans the input line to check whether the instruction has a label or not.
     *
     * @return - if there is a label the label is returned otherwise it is null
     */
    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}