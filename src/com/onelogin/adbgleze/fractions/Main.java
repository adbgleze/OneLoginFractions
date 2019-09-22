package com.onelogin.adbgleze.fractions;

public class Main {

    /**
     * Represents an invalid input provided at program execution
     */
    private static class InvalidInputException extends Exception {}

    private static final String ASK_COMMAND = "?";
    private static final String HELP_COMMAND = "-h";
    private static final String RESULT_INDICATOR = "= ";

    /**
     * Currently supported operators
     */
    private static final String PLUS_SIGN = "+";
    private static final String DIFFERENCE_SIGN = "-";
    private static final String MULTIPLICATION_SIGN = "*";
    private static final String DIVISION_SIGN = "/";


    /**
     * Help constants
     */
    private static final String HELP_GENERAL = "List of Commands:" +
            "\n\t?\tUsed to ask a fraction operation in the following format:" +
            "\n\t\t? fraction operator fraction" +
            "\n\t-h\t Used to show this help." +
            "\n\nFraction format must be W_N/D or N/D or W where W represents the integer or \"whole\" part, " +
            "N represents the numerator, and D the denominator. E.g. 3_1/2, 1/4, 5." +
            "\n\nSupported operations:" +
            "\n\t+\tAddition" +
            "\n\t-\tDifference" +
            "\n\t*\tMultiplication" +
            "\n\t/\tDivision";
    private static final String INVALID_COMMAND = "Invalid command, please check -h for help";
    private static final String INVALID_INPUT = "Invalid Input, please check -h command for more help";
    private static final String INVALID_FRACTION = "Wrong fraction format or invalid fraction found," +
            "please check -h for mot help";
    private static final String DIVISION_BY_ZERO_DETECTED = "Cannot divide by zero, please try again with a" +
            "non zero divisor";

    /**
     * Makes the actual main operation of the program
     * @param input input from running program
     * @throws InvalidInputException if the input is invalid
     * @throws MalformedFractionException if an invalid fraction or format is found
     */
    private static void makeOperation(String[] input) throws InvalidInputException, MalformedFractionException {

        // Validate input length
        if (input.length > 4) throw new InvalidInputException();

        // Currently only ASK_COMMAND supported (?)
        if (!input[0].equals(ASK_COMMAND)) {
            throw new InvalidInputException();
        }

        // Deserialize from input
        Fraction a = Fraction.fromString(input[1]);
        Fraction b = Fraction.fromString(input[3]);

        // Extract the operator to work with
        String operator = input[2];
        Fraction result;

        switch (operator) {
            case PLUS_SIGN:
                result = FractionOperations.sum(a, b);
                break;
            case DIFFERENCE_SIGN:
                result = FractionOperations.difference(a, b);
                break;
            case MULTIPLICATION_SIGN:
                result = FractionOperations.multiply(a, b);
                break;
            case DIVISION_SIGN:
                result = FractionOperations.divide(a, b);
                break;
            default:
                throw new InvalidInputException();
        }

        // Print result
        System.out.println(RESULT_INDICATOR + result.toString());

    }

    /**
     * Executes the main operation of the program and checks if some error occurs on the process
     * @param args command received hopefully in the ? fraction operator fraction, look at HELP_GENERAL for more info.
     */
    public static void main(String[] args) {

        // HELP_COMMAND is considered
        if (args.length == 1) {
            if (args[0].equals(HELP_COMMAND)) {
                System.out.println(HELP_GENERAL);
                return;
            } else {
                System.out.println(INVALID_COMMAND);
            }
        }

        // Otherwise make the operation requested
        try {
            makeOperation(args);
        } catch (InvalidInputException i) {
            System.out.println(INVALID_INPUT);
        } catch (MalformedFractionException m) {
            System.out.println(INVALID_FRACTION);
        } catch (ArithmeticException a) {
            System.out.println(DIVISION_BY_ZERO_DETECTED);
        }
    }
}
