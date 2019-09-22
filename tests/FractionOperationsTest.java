import com.onelogin.adbgleze.fractions.Fraction;
import com.onelogin.adbgleze.fractions.FractionOperations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FractionOperationsTest {

    /**
     * Addition
     */
    @Test
    void fractionPlusFraction() {
        Fraction a = new Fraction(1,2);
        Fraction b = new Fraction(1,2);
        Fraction expected = new Fraction(1);
        Assertions.assertEquals(expected, FractionOperations.sum(a, b));
    }

    @Test
    void fractionPlusInt() {
        Fraction a = new Fraction(1,2);
        Fraction b = new Fraction(1);
        Fraction expected = new Fraction(1, 1, 2);
        Assertions.assertEquals(expected, FractionOperations.sum(a, b));
    }

    @Test
    void intPlusFraction() {
        Fraction a = new Fraction(1);
        Fraction b = new Fraction(1,2);
        Fraction expected = new Fraction(1, 1, 2);
        Assertions.assertEquals(expected, FractionOperations.sum(a, b));
    }

    @Test
    void intPlusInt() {
        Fraction a = new Fraction(1);
        Fraction b = new Fraction(2);
        Fraction expected = new Fraction(3);
        Assertions.assertEquals(expected, FractionOperations.sum(a, b));
    }

    @Test
    void intPlusZero() {
        Fraction a = new Fraction(1);
        Fraction b = new Fraction(0);
        Fraction expected = new Fraction(1);
        Assertions.assertEquals(expected, FractionOperations.sum(a, b));
    }

    /**
     *  Difference
     */
    @Test
    void fractionSubtractFraction() {
        Fraction a = new Fraction(1,2);
        Fraction b = new Fraction(1,2);
        Fraction expected = new Fraction(0);
        Assertions.assertEquals(expected, FractionOperations.difference(a, b));
    }

    @Test
    void fractionSubtractInt() {
        Fraction a = new Fraction(1,2);
        Fraction b = new Fraction(1);
        Fraction expected = new Fraction(-1,2);
        Assertions.assertEquals(expected, FractionOperations.difference(a, b));
    }

    @Test
    void intSubtractInt() {
        Fraction a = new Fraction(1);
        Fraction b = new Fraction(2);
        Fraction expected = new Fraction(-1);
        Assertions.assertEquals(expected, FractionOperations.difference(a, b));
    }

    @Test
    void subtractZero() {
        Fraction a = new Fraction(1);
        Fraction b = new Fraction(0);
        Fraction expected = new Fraction(1);
        Assertions.assertEquals(expected, FractionOperations.difference(a, b));
    }

    /**
     *  Multiplication
     */
    @Test
    void fractionTimesFraction() {
        Fraction a = new Fraction(1,2);
        Fraction b = new Fraction(1,2);
        Fraction expected = new Fraction(1,4);
        Assertions.assertEquals(expected, FractionOperations.multiply(a, b));
    }

    @Test
    void fractionTimesInt() {
        Fraction a = new Fraction(1,2);
        Fraction b = new Fraction(1);
        Fraction expected = new Fraction(1,2);
        Assertions.assertEquals(expected, FractionOperations.multiply(a, b));
    }

    @Test
    void intTimesInt() {
        Fraction a = new Fraction(1);
        Fraction b = new Fraction(2);
        Fraction expected = new Fraction(2);
        Assertions.assertEquals(expected, FractionOperations.multiply(a, b));
    }

    @Test
    void timesZero() {
        Fraction a = new Fraction(1);
        Fraction b = new Fraction(0);
        Fraction expected = new Fraction(0);
        Assertions.assertEquals(expected, FractionOperations.multiply(a, b));
    }

    /**
     *  Division
     */
    @Test
    void fractionDividedByFraction() {
        Fraction a = new Fraction(1,2);
        Fraction b = new Fraction(1,2);
        Fraction expected = new Fraction(1);
        Assertions.assertEquals(expected, FractionOperations.divide(a, b));
    }

    @Test
    void fractionDividedByInt() {
        Fraction a = new Fraction(1,2);
        Fraction b = new Fraction(2);
        Fraction expected = new Fraction(1,4);
        Assertions.assertEquals(expected, FractionOperations.divide(a, b));
    }

    @Test
    void intDividedByInt() {
        Fraction a = new Fraction(1);
        Fraction b = new Fraction(2);
        Fraction expected = new Fraction(1,2);
        Assertions.assertEquals(expected, FractionOperations.divide(a, b));
    }

    @Test
    void dividedByZero() {
        Fraction a = new Fraction(1);
        Fraction b = new Fraction(0);
        Assertions.assertThrows(ArithmeticException.class, () -> FractionOperations.divide(a, b));
    }
}
