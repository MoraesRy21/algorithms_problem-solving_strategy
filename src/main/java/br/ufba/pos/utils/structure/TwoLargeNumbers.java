package br.ufba.pos.utils.structure;

public class TwoLargeNumbers {
    public String num1;
    public String num2;

    public TwoLargeNumbers(String num1, String num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "x = " + num1 + "; y = " + num2;
    }
}
