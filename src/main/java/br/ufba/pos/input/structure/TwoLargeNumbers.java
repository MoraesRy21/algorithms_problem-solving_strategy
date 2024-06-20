package br.ufba.pos.input.structure;

import java.math.BigInteger;

/**
 * Simple structure to hold two large Numbers
 */
public class TwoLargeNumbers {

    private final BigInteger bigNumber1;
    private final BigInteger bigNumber2;

    private final String num1;
    private final String num2;

    public TwoLargeNumbers(BigInteger bigNumber1, BigInteger bigNumber2) {
        this.bigNumber1 = bigNumber1;
        this.bigNumber2 = bigNumber2;
        num1 = bigNumber1.toString();
        num2 = bigNumber2.toString();
    }

    public BigInteger multiplication() {
        return bigNumber1.multiply(bigNumber2);
    }

    public BigInteger getBigNumber1() {
        return bigNumber1;
    }

    public BigInteger getBigNumber2() {
        return bigNumber2;
    }

    public String getNum1() {
        return num1;
    }

    public String getNum2() {
        return num2;
    }

    @Override
    public String toString() {
        return "x = " + num1 + "; y = " + num2;
    }
}
