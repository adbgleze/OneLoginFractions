package com.onelogin.adbgleze.fractions;

public class Fraction {

    /**
     * Pattern representing the fraction for Serialization and Deserialization.
     * Fraction format is W_N/D or N/D or just W; where W represents the whole part,
     * N the numerator and D the denominator.
     */
    private static final String PATTERN = "(d_*d+/d+)|(d+)";
    private static final String WHOLE_DIVISOR = "_";
    private static final String FRACTION_SYMBOL = "/";

    int wholePart = 0;
    int numerator;
    int denominator = 1;

    /**
     * Normal construction of a fraction with value of 0
     */
    public Fraction() {}

    /**
     * Instantiates a Fraction from another Fraction object (it clones it)
     * @param f Fraction to clone
     */
    public Fraction (Fraction f) {
        wholePart = f.wholePart;
        numerator = f.numerator;
        denominator = f.denominator;
    }

    /**
     * Instantiates a Fraction that represents just an Integer.
     * @param whole Integer that will be represented
     */
    public Fraction(int whole) {
        wholePart = whole;
        denominator = 0;
    }

    /**
     * Instantiates a Fraction by its numerator and denominator
     * @param numerator Integer representing the numerator of the Fraction
     * @param denominator Integer representing the denominator of the Fraction
     */
    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Instantiates a Fraction by its whole part, numerator, and denominator
     * @param whole Integer representing the whole portion of the Fraction
     * @param numerator Integer representing the numerator of the Fraction
     * @param denominator Integer representing the denominator of the Fraction
     */
    public Fraction(int whole, int numerator, int denominator) {
        this(numerator, denominator);
        wholePart = whole;
    }

    /**
     * Deserializes the Fraction from a String in the W_N/D where W is the whole part, N is the numerator,
     * and D the denominator
     * @param val String value of the Fraction
     * @return fraction as a Fraction object
     * @throws MalformedFractionException if the deserialization fails by format issues
     */
    public static Fraction fromString(String val) throws MalformedFractionException {
        if (isValidFraction(val)) throw new MalformedFractionException();

        int wholePart = 0;

        int wholeDivisorPos = val.lastIndexOf(WHOLE_DIVISOR);
        if (wholeDivisorPos != -1)
            wholePart = Integer.parseInt(val.substring(0, wholeDivisorPos));

        int fractionSymbolPos = val.lastIndexOf(FRACTION_SYMBOL);
        int numerator = Integer.parseInt(val.substring(wholeDivisorPos + 1, fractionSymbolPos));
        int denominator = Integer.parseInt(val.substring(fractionSymbolPos + 1));

        return new Fraction(wholePart, numerator, denominator);
    }

    /**
     * Checks if the String value is correctly representing a fraction in W_N/D format
     * @param val String representation of the Fraction
     * @return true if fraction representation is valid, false otherwise
     */
    public static boolean isValidFraction(String val) {
        return val.matches(PATTERN);
    }

    /**
     * Identifies if a Fraction is representing an exact integer
     * @return true if the Fraction represents only an integer (no fraction part available), false otherwise
     */
    public boolean isWholeOnly() {
        return ((numerator == 0 || denominator == 0) && wholePart != 0);
    }

    /**
     * Idenftifies if the actual fraction is zero
     * @return true if the fraction is zero, false otherwise
     */
    public boolean isZero() {
        return wholePart == 0 && numerator == 0 && denominator == 0;
    }

    /**
     * Evaluates if two fractions are equal
     * @param obj Fraction to compare with
     * @return true if Fractions are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Fraction)) return false;
        Fraction f = (Fraction) obj;
        return wholePart == f.wholePart && numerator == f.numerator && denominator == f.denominator;
    }

    /**
     * Serializes the Fraction in the W_N/D format
     * @return String representation of the Fraction
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();

        if (wholePart != 0) {
            b.append(wholePart);
            if (numerator != 0) b.append(WHOLE_DIVISOR);
        }

        if (numerator != 0) {
            b.append(numerator);
            b.append(FRACTION_SYMBOL);
            b.append(denominator);
        }

        return b.toString();
    }
}
