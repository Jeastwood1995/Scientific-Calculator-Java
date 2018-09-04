/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientific.calculator.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author Jack
 */

public class ScientificCalculatorJava {
    // global variables that will be used for evaluations

    static float aggregate = 0, highest = -Float.MAX_VALUE, lowest = Float.MAX_VALUE, average, result;

    static int valid, invalid; // will hold all the valid and invalid expressions 

    static void statistics(float result) { // sum from the operation is carried in 
        aggregate += result; // adds the result to the aggregate (to give the total of all the sums)

        highest = (result > highest) ? result : highest; // result is stored in highest first, then the next result is compared to 
        // the current highest sum, and if the current result is higher then it gets
        // stored in the highest variable

        lowest = (result < lowest) ? result : lowest;

        average = aggregate / valid; // divided the total sums in the aggregate variable 
    } //statistics
    
    static void extraOperators(String[] text, String input) {
        String operation;
        Stack<Float> stack = new Stack<Float>(); // a stack is created to deal with expressions
        float num1;
        float num2;

        for (int i = 0; i < text.length; i++) { // goes through each element in the text array
            try {
                operation = text[i]; // stores each element in the array to operation
                if (!"+".equals(operation) && !"*".equals(operation) && !"-".equals(operation) && !"/".equals(operation)
                        && !"<<".equals(operation) && !">>".equals(operation) && !"%".equals(operation)
                        && !"&".equals(operation) && !"|".equals(operation)) {
                    stack.push(Float.parseFloat(operation));
                } else {
                    num2 = (float) stack.pop();
                    num1 = (float) stack.pop();

                    if (operation.equals("+")) {
                        System.out.println("\nYour equation is: " + num1 + " + " + num2 + " = " + (num1 + num2));
                        result = num1 + num2;
                        valid++;

                    } else if (operation.equals("-")) {
                        System.out.println("\nYour equation is: " + num1 + " - " + num2 + " = " + (num1 - num2));
                        result = num1 - num2;
                        valid++;

                    } else if (operation.equals("/")) {
                        System.out.println("\nYour equation is: " + num1 + " / " + num2 + " = " + (num1 / num2));
                        result = num1 / num2;
                        valid++;

                    } else if (operation.equals("*")) {
                        System.out.println("\nYour equation is: " + num1 + " * " + num2 + " = " + (num1 * num2));
                        result = num1 * num2;
                        valid++;

                    } else {
                        System.out.print("\nInvalid operator. Please input a valid operator.\n ");
                        invalid++; // invalid is increased by one for every invalid expression

                    }
                    stack.push(result);
                    statistics(result); // the results then get sent to be calculated for evaluations

                } //else 

            } catch (NumberFormatException e) {
                System.out.print("Invalid operands entered: " + input + ". Please enter valid operands.\n\n");
                invalid++;

            } catch (EmptyStackException s) {
                System.out.print("Invalid operands entered: " + input + ". Please enter valid operands.\n\n");
                invalid++;

            } //catch
        } //for

    } //extraOperators()
    
    static void evaluations(float highest, float lowest, float aggregate, float average, int invalid, int valid) {

        if (valid == 0) { // if there's no valid expressions, then "n/a" is displayed
            System.out.println("\nEvaluations complete\n-------------------------------------------");
            System.out.println("Highest value: n/a");
            System.out.println("Lowest value: n/a");
            System.out.println("Aggregate result: " + aggregate);
            System.out.println("Average result: n/a");
            System.out.println("Invalid expressions: " + invalid);
            System.out.println("Valid expresssions: " + valid + "\n");

        } else {
            System.out.println("\nEvaluations complete\n-------------------------------------------");
            System.out.println("Highest value: " + highest);
            System.out.println("Lowest value: " + lowest);
            System.out.println("Aggregate result: " + aggregate);
            System.out.println("Average result: " + average);
            System.out.println("Invalid expressions: " + invalid);
            System.out.println("Valid expresssions: " + valid + "\n");
        } //else

    } //evaluations()
    
    static void fileReader(String choice) {

        String filename = "";
        boolean file = true;
        String fileline = "";
        String operandone1 = "";

        String operandtwo2 = "";
        String operator = "";

        while (file == true) {
            try {

                System.out.print("Please enter a valid filename: ");
                Scanner scan = new Scanner(System.in);
                filename = scan.nextLine();
                if (filename.length() == 0) {
                    System.out.println("Exiting program...");
                    file = false;
                    System.exit(0);
                    break;
                }
                Scanner filescan = new Scanner(new File(filename));
                System.out.println(filename + " has been selected. \n-------------------------");
                System.out.println("Processing data...");

                while (filescan.hasNext()) { // loops through the file until it reads the entire file
                    try {
                        fileline = filescan.nextLine(); // reads the first line in the file
                        System.out.println("\nPost fixed expression is: " + fileline);

                        String[] filevar = fileline.split(" "); // splits the tokens in the selected
                        // line in an array called filevar and puts a space between them
                        if (filevar.length > 3) { // if there's more than 2 operands/1 operator in the array, then the extra operator method
                            // is called
                            extraOperators(filevar, fileline);
                        } else {

                            operandone1 = filevar[0]; // first token in the array is
                            // the first operand
                            operandtwo2 = filevar[1];
                            operator = filevar[2];

                            if (operator.equals("+")) {
                                Double operand1 = Double.parseDouble(operandone1); // operands are converted to double types
                                Double operand2 = Double.parseDouble(operandtwo2);
                                System.out.println("Your equation is: " + operand1 + " + " + operand2 + " = " + (operand1 + operand2));
                                result = (float) (operand1 + operand2); // the sum of the 2 operands are converted to float's then added to result
                                valid++; // addds one to the valid global variable every time a valid expression is calculated

                            } else if (operator.equals("-")) {
                                Double operand1 = Double.parseDouble(operandone1);
                                Double operand2 = Double.parseDouble(operandtwo2);
                                System.out.println("Your equation is: " + operand1 + " - " + operand2 + " = " + (operand1 - operand2));
                                result = (float) (operand1 - operand2);
                                valid++;

                            } else if (operator.equals("/")) {
                                Double operand1 = Double.parseDouble(operandone1);
                                Double operand2 = Double.parseDouble(operandtwo2);
                                System.out.println("Your equation is: " + operand1 + " / " + operand2 + " = " + (operand1 / operand2));
                                result = (float) (operand1 / operand2);
                                valid++;

                            } else if (operator.equals("*")) {
                                Double operand1 = Double.parseDouble(operandone1);
                                Double operand2 = Double.parseDouble(operandtwo2);
                                System.out.println("Your equation is: " + operand1 + " * " + operand2 + " = " + (operand1 * operand2));
                                result = (float) (operand1 * operand2);
                                valid++;

                            } else if (operator.equals("<<")) { // rest of the else statements are for the extra operators
                                int conversion1 = Integer.parseInt(operandone1); // the operands are converted to integers
                                int conversion2 = Integer.parseInt(operandtwo2);
                                System.out.println("Your equation is: " + conversion1 + " << " + conversion2 + " = " + (conversion1 << conversion2));
                                result = conversion1 << conversion2;
                                valid++;

                            } else if (operator.equals(">>")) {
                                int conversion1 = Integer.parseInt(operandone1);
                                int conversion2 = Integer.parseInt(operandtwo2);
                                System.out.println("Your equation is: " + conversion1 + " >> " + conversion2 + " = " + (conversion1 >> conversion2));
                                result = conversion1 >> conversion2;
                                valid++;

                            } else if (operator.equals("%")) {
                                int conversion1 = Integer.parseInt(operandone1);
                                int conversion2 = Integer.parseInt(operandtwo2);
                                System.out.println("Your equation is: " + conversion1 + " % " + conversion2 + " = " + (conversion1 % conversion2));
                                result = conversion1 % conversion2;
                                valid++;

                            } else if (operator.equals("&")) {
                                int conversion1 = Integer.parseInt(operandone1);
                                int conversion2 = Integer.parseInt(operandtwo2);
                                System.out.println("Your equation is: " + conversion1 + " & " + conversion2 + " = " + (conversion1 & conversion2));
                                result = conversion1 & conversion2;
                                valid++;

                            } else if (operator.equals("|")) {
                                int conversion1 = Integer.parseInt(operandone1);
                                int conversion2 = Integer.parseInt(operandtwo2);
                                System.out.println("Your equation is: " + conversion1 + " | " + conversion2 + " = " + (conversion1 | conversion2));
                                result = conversion1 | conversion2;
                                valid++;

                            } else {
                                System.out.print("Invalid operator. Please input a valid operator.\n ");
                                invalid++; // invalid is increased by one for every invalid expression

                            } //else
                            ScientificCalculatorJava.statistics(result);  // the results then get sent to be calculated for evaluations 
                            // every expression has been read
                        }
                    } catch (InputMismatchException e) { // if characters are entered instead of numbers or more than 3 operands + operator are i the file then these will be printed
                        System.out.print("Invalid operands entered: " + fileline + "\n");
                        invalid++;

                    } catch (NumberFormatException e) {
                        System.out.print("Invalid operands entered: " + fileline + "\n");
                        invalid++;

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Incorrect operands/operator entered. Please enter in format (o1-o2-op).");
                        invalid++;

                    }
                }
                filescan.close();
                System.out.println("---------------------------\n\tEnd of file\n---------------------------");
                evaluations(highest, lowest, aggregate, average, invalid, valid); // evaluations are shown as soon as the file is ended
                main(null); // goes back to start of program after evaluations have been shown
            } catch (FileNotFoundException e) {
                System.out.println("Invalid file. Please enter a valid filename: \n");
            } //catch()
        } //while
    } //filereader()
    
    static void userInput(String choice) throws FileNotFoundException {

        String input = null;
        boolean reset = true;
        String operandone1 = "";
        String operandtwo2 = "";
        String operator = "";
        Scanner scan = new Scanner(System.in);

        OUTER:
        while (reset == true) {
            try {
                System.out.print("\nPlease enter a post-fixed expression: ");
                input = scan.nextLine();
                if (input.length() == 0) {
                    System.out.println("\nExiting program...");
                    reset = false;
                    System.exit(0);
                    break;
                }
                String[] text = input.split(" ");
                if (text.length > 3) {
                    extraOperators(text, input);
                } else {
                    operandone1 = text[0];
                    operandtwo2 = text[1];
                    operator = text[2];
                    // get answers
                    switch (operator) {
                        case "+": {
                            Double operand1 = Double.parseDouble(operandone1); // operands 1 + 2 are converted to double for the calculations
                            Double operand2 = Double.parseDouble(operandtwo2);
                            System.out.println("Your equation is: " + operand1 + " + " + operand2 + " = " + (operand1 + operand2) + "\n");
                            result = (float) (operand1 + operand2);
                            valid++;
                            break;
                        }
                        case "-": {
                            Double operand1 = Double.parseDouble(operandone1); // operands 1 + 2 are converted to double for the calculations
                            Double operand2 = Double.parseDouble(operandtwo2);
                            System.out.println("Your equation is: " + operand1 + " - " + operand2 + " = " + (operand1 - operand2) + "\n");
                            result = (float) (operand1 - operand2);
                            valid++;
                            break;
                        }
                        case "/": {
                            Double operand1 = Double.parseDouble(operandone1); // operands 1 + 2 are converted to double for the calculations
                            Double operand2 = Double.parseDouble(operandtwo2);
                            System.out.println("Your equation is: " + operand1 + " / " + operand2 + " = " + (operand1 / operand2) + "\n");
                            result = (float) (operand1 / operand2);
                            valid++;
                            break;
                        }
                        case "*": {
                            Double operand1 = Double.parseDouble(operandone1); // operands 1 + 2 are converted to double for the calculations
                            Double operand2 = Double.parseDouble(operandtwo2);
                            System.out.println("Your equation is: " + operand1 + " * " + operand2 + " = " + (operand1 * operand2) + "\n");
                            result = (float) (operand1 * operand2);
                            valid++;
                            break;
                        }
                        case "<<": {
                            // rest of the else statements are for the extra operators
                            int conversion1 = Integer.parseInt(operandone1); // the operands are converted to integers
                            int conversion2 = Integer.parseInt(operandtwo2);
                            System.out.println("Your equation is: " + conversion1 + " << " + conversion2 + " = " + (conversion1 << conversion2));
                            result = conversion1 << conversion2;
                            valid++;
                            break;
                        }
                        case ">>": {
                            int conversion1 = Integer.parseInt(operandone1);
                            int conversion2 = Integer.parseInt(operandtwo2);
                            System.out.println("Your equation is: " + conversion1 + " >> " + conversion2 + " = " + (conversion1 >> conversion2));
                            result = conversion1 >> conversion2;
                            valid++;
                            break OUTER;
                        }
                        case "%": {
                            int conversion1 = Integer.parseInt(operandone1);
                            int conversion2 = Integer.parseInt(operandtwo2);
                            System.out.println("Your equation is: " + conversion1 + " % " + conversion2 + " = " + (conversion1 % conversion2));
                            result = conversion1 % conversion2;
                            valid++;
                            break;
                        }
                        case "&": {
                            int conversion1 = Integer.parseInt(operandone1);
                            int conversion2 = Integer.parseInt(operandtwo2);
                            System.out.println("Your equation is: " + conversion1 + " & " + conversion2 + " = " + (conversion1 & conversion2));
                            result = conversion1 & conversion2;
                            valid++;
                            break;
                        }
                        case "|": {
                            int conversion1 = Integer.parseInt(operandone1);
                            int conversion2 = Integer.parseInt(operandtwo2);
                            System.out.println("Your equation is: " + conversion1 + " | " + conversion2 + " = " + (conversion1 | conversion2));
                            result = conversion1 | conversion2;
                            valid++;
                            break;
                        }
                        default:
                            System.out.print("Invalid operator: " + operator + ". Please input a valid operator: \n\n");
                            invalid++;
                            break;
                    } //switch
                } //else
            } catch (InputMismatchException e) {
                System.out.print("Invalid operands entered: " + input + ". Please enter valid operands.\n\n");
                invalid++;

            } catch (NumberFormatException e) {
                System.out.print("Invalid operands entered: " + input + ". Please enter valid operands.\n\n");
                invalid++;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Incorrect operands/operator entered. Please enter in format (o1-o2-op).\n");
                invalid++;

            } //catch
            System.out.print("Would you like to input more numbers?: ");
            input = scan.nextLine().toUpperCase();
            while (!input.equals("YES") || !input.equals("NO")) {
                if (input.equals("YES")) {
                    statistics(result); // the result gets sent for calculations
                    userInput(input); // goes back to user input method
                } else if (input.equals("NO")) {
                    statistics(result); // the result gets sent for calculations
                    evaluations(highest, lowest, aggregate, average, invalid, valid); // evaluations are displayed
                    main(null); // goes back to start of program
                } else if (input.length() == 0) {
                    System.out.println("\nExiting program. Goodbye!");
                    System.exit(0);
                    break;
                } else {
                    System.out.print("Invalid choice. Please enter either Yes or No. \n\n");
                }
            } //while
            scan.close();
        } //while

    } //userInput

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        String choice;

        Scanner scan = new Scanner(System.in);

        System.out.print("Press ‘F’ to read expressions from a file, or ‘K’ to enter expressions from the keyboard : ");
        choice = scan.nextLine().toUpperCase();

        while (!choice.equals("F") || !choice.equals("K")) {
            if (choice.equals("F")) {
                fileReader(choice);
            } else if (choice.equals("K")) {
                userInput(choice);
            } else if (choice.length() == 0) {
                System.out.println("\nExiting program. Goodbye!");
                System.exit(0);
                break;
            } else {
                System.out.print("Invalid choice. Please enter a valid choice. \n\n");
                ScientificCalculatorJava.main(args);
            } //else
        } //while
        scan.close();

    } //main()
    
} //ScientificCalculatorJava
