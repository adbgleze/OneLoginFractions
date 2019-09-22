package com.onelogin.adbgleze.fractions;

public class FractionOperations {

    /**
     * Reduces the Fraction to the simplest form possible
     * @param f Fraction to work with
     */
    public static void reduce(Fraction f) {
        // Verification that we really have a fraction
        if (f.denominator == 1) {
            f.wholePart += f.numerator;
            f.numerator = f.denominator = 0;
            return;
        } else if (f.numerator == 0) {
            f.denominator = 0;
            return;
        }

        int gcd = greaterCommonDivisorByEuclid(Math.abs(f.numerator), Math.abs(f.denominator));
        f.numerator /= gcd;
        f.denominator/= gcd;
    }

    /**
     * Identifies if the current Fraction > 1 and proceeds to separate the whole part of the fraction part,
     * making it a "mixed fraction".
     * @param f Fraction to work with
     */
    private static void separateWholePart(Fraction f) {
        if (f.numerator < f.denominator) return; // Nothing to do here
        else if (f.denominator == 1) { // It's not really a fraction, its already an integer
            f.wholePart += f.numerator;
            f.denominator = f.numerator = 0;
            return;
        }

        f.wholePart += f.numerator/ f.denominator;
        f.numerator = f.numerator % f.denominator;

        if (f.numerator == 0) f.denominator = 0;

    }

    /**
     * Takes the whole part of a fraction to represent it as part of the numerator. Contrary to separateWholePart()
     * @param f Fraction to work with
     */
    private static void joinWholePart(Fraction f) {
        if (f.wholePart != 0) {
            if (!f.isWholeOnly()) f.numerator = f.wholePart * f.denominator + f.numerator;
            else {
                f.numerator = f.wholePart;
                f.denominator = 1;
            }
        }
    }

    /**
     * Sums two Fractions
     * @param a First Fraction
     * @param b Second Fraction
     * @return Sum result as a Fraction
     */
    public static Fraction sum(Fraction a, Fraction b) {
        prepareForOperation(a);
        prepareForOperation(b);

        if (b.isZero()) {
            separateWholePart(a);
            reduce(a);
            return a;
        } else if (a.isZero()) {
            separateWholePart(b);
            reduce(b);
            return b;
        }

        Fraction f = new Fraction(0, leastCommonMultiple(a.denominator, b.denominator));

        int aMultiplier = f.denominator / a.denominator;
        int bMultiplier = f.denominator / b.denominator;

        f.numerator = a.numerator * aMultiplier + b.numerator * bMultiplier;
        separateWholePart(f);
        reduce(f);
        return f;
    }

    /**
     * Calculates the difference from the two fractions
     * @param a First Fraction
     * @param b Second Fraction
     * @return Difference as a Fraction
     */
    public static Fraction difference(Fraction a, Fraction b) throws ArithmeticException {

        if (b.wholePart != 0) b.wholePart *= -1;
        else b.numerator *= -1;
        return sum(a, b);
    }

    /**
     * Multiplies two Fractions
     * @param a First Fraction
     * @param b Second Fraction
     * @return Result as a Fraction
     */
    public static Fraction multiply(Fraction a, Fraction b) {
        prepareForOperation(a);
        prepareForOperation(b);

        if (b.isZero()) {
            return b;
        } else if (a.isZero()) {
            return a;
        }

        Fraction f = new Fraction(a.numerator * b.numerator,
                a.denominator * b.denominator);

        separateWholePart(f);
        reduce(f);
        return f;
    }

    /**
     * Calculates the division of two fractions (a / b)
     * @param a First Fraction
     * @param b Second Fraction
     * @return Result as a Fraction
     */
    public static Fraction divide(Fraction a, Fraction b) {

        if (b.isZero()) throw new ArithmeticException();

        joinWholePart(b);
        Fraction f = new Fraction(b.denominator, b.numerator);
        f = multiply(a, f);

        return f;
    }

    /**
     * Prepares the Fraction to ongoing operations to avoid problems when the fraction is just an integer or is a "mixed
     * fraction" (integer + fraction)
     * @param f Fraction to work with
     */
    private static void prepareForOperation(Fraction f) {
        if (f.isWholeOnly()) {
            f.numerator = f.wholePart;
            f.wholePart = 0;
            f.denominator = 1;
            return;
        }
        joinWholePart(f);
    }

    /**
     * My implementation of the Euclid algorithm to get the GCD of two numbers
     * @param a First number
     * @param b Seconds number
     * @return the GCD of both numbers provided
     */
    private static int greaterCommonDivisorByEuclid(int a, int b) {
        int dividend = Math.max(a, b);
        int divisor = Math.min(a, b);

        if (dividend % divisor == 0)
            return divisor;

        int currentRemainder = dividend % divisor;
        int lastRemainder;

        do {
            lastRemainder = currentRemainder;
            currentRemainder = dividend % divisor;

            dividend = divisor;
            divisor = currentRemainder;
        } while (currentRemainder != 0);

        return lastRemainder;
    }

    /**
     * Calculates the Least Common Multiple according to the formula:
     * LCM(a, b) = |a * b| / GCD(a,b)
     * @param a First number
     * @param b Second number
     * @return Least common multiple of both numbers provided
     */
    private static int leastCommonMultiple(int a, int b) {
        return Math.abs(a * b) / greaterCommonDivisorByEuclid(a, b);
    }
}
