package br.ufba.pos.questions;

import br.ufba.pos.utils.Charts;
import br.ufba.pos.utils.structure.TwoLargeNumbers;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.TreeMap;

/**
 * Q02: Multiplicação de Números Inteiros Grandes
 * Sejam X e Y dois números inteiros de n dígitos.
 * Desenvolva um algoritmo que encontre a multiplicação de X e Y em menos de O(n²) passos.
 * Encontre a complexidade do algoritmo desenvolvido.
 */
public class Question2 extends Question<TwoLargeNumbers> {

    private TwoLargeNumbers twoLargeNumbers;

    public Question2() {
        super.gCounterMapInstruction = new TreeMap<String, Integer>();
        super.dcCounterMapInstruction = new TreeMap<String, Integer>();
    }

    @Override
    public String getQuestionTitle() {
        return "Multiplicação de Números Inteiros Grandes";
    }

    @Override
    public TwoLargeNumbers getInput() {
        return twoLargeNumbers;
    }

    @Override
    public void setInput(TwoLargeNumbers input) {
        this.twoLargeNumbers = input;
    }

    @Override
    public void result() {
        System.out.println("\n——————————————————————————————————————————————————————");
        System.out.println("Generic Algorithm Map instruction:");
        printMapAsGraphPoint(gCounterMapInstruction);
        System.out.println("Divide and Conquer Algorithm Map instruction:");
        printMapAsGraphPoint(dcCounterMapInstruction);

        Charts.plotChartStringInteger(gCounterMapInstruction, dcCounterMapInstruction);
    }

    @Override
    protected void genericAlgorithm() {
        String num1 = twoLargeNumbers.num1;
        String num2 = twoLargeNumbers.num2;
        String result = multipleNumbers(twoLargeNumbers.num1, twoLargeNumbers.num2);
        System.out.println("Multiplication result of " + num1 + " x " + num2 + " = " + result);

        dcCounterMapInstruction.put(StringUtils.leftPad(result, 42, "0"), counter.getCounter());
        counter.resetCounter();
    }

    private String multipleNumbers(String value1, String value2) {
        char[] num1 = value1.toCharArray(); counter.countInstructions();
        char[] num2 = value2.toCharArray(); counter.countInstructions();

        BigInteger finalResult = new BigInteger("0"); counter.countInstructions();

        for(int i=num2.length - 1; i>=0; i--) { counter.countInstructions();
            int upNum = 0; counter.countInstructions();
            int resultPosition = num2.length - 1 - i; counter.countInstructions();
            String numericCompaund = ""; counter.countInstructions();
            counter.countInstructions(4); counter.countInstructions();
            if(resultPosition != 0) { counter.countInstructions();
                numericCompaund = String.format("%0"+resultPosition+"d", 0); counter.countInstructions();
            }

            int unit2 = toInt(num2[i]); counter.countInstructions();
            counter.countInstructions(2); counter.countInstructions();
            for(int j=num1.length - 1; j>=0; j--) { counter.countInstructions();
                int unit1 = toInt(num1[j]); counter.countInstructions();
                int multiplication = unit2 * unit1 + upNum; counter.countInstructions();
                upNum = multiplication / 10; counter.countInstructions();
                numericCompaund = (multiplication % 10) + numericCompaund; counter.countInstructions();
            }
            if(upNum > 0) { counter.countInstructions();
                numericCompaund = upNum + numericCompaund; counter.countInstructions();
                counter.countInstructions(1); counter.countInstructions();
            }
            finalResult = finalResult.add(new BigInteger(numericCompaund)); counter.countInstructions();
        }
        return finalResult.toString();
    }

    @Override
    protected void divideAndConquerAlgorithm() {
        String num1 = twoLargeNumbers.num1;
        String num2 = twoLargeNumbers.num2;
        String result = karatsuba(twoLargeNumbers.num1, twoLargeNumbers.num2);
        System.out.println("Multiplication result of " + num1 + " x " + num2 + " = " + result);

        gCounterMapInstruction.put(StringUtils.leftPad(result, 42, "0"), counter.getCounter());
        counter.resetCounter();
    }

    private String karatsuba(String value1, String value2) {
        char[] num1 = value1.toCharArray(); counter.countInstructions();
        char[] num2 = value2.toCharArray(); counter.countInstructions();
        if(value1.length() <= 1 || value2.length() <= 1) { counter.countInstructions();
            int unitNum1 = toInt(num1[num1.length - 1]); counter.countInstructions();
            int unitNum2 = toInt(num2[num2.length - 1]); counter.countInstructions();
            return Integer.toString(unitNum1 * unitNum2);
        }

        int splitSize = Math.max(num1.length, num2.length) / 2; counter.countInstructions();

        String high1 = value1.length() > splitSize ? value1.substring(0, value1.length() - splitSize) : "0"; counter.countInstructions();
        String low1 = value1.substring(splitSize); counter.countInstructions();
        String high2 = value2.length() > splitSize ? value2.substring(0, splitSize) : "0"; counter.countInstructions();
        String low2 = value2.substring(splitSize); counter.countInstructions();

        String z0 = karatsuba(low1, low2);
        String z1 = karatsuba(sumStr(low1, high1), sumStr(low2, high2));
        String z2 = karatsuba(high1, high2);

        long p1 = (long) (Long.parseLong(z2) * (Math.pow(10, splitSize * 2))); counter.countInstructions();
        long p2 = (long) ((Long.parseLong(z1) - Long.parseLong(z2) - Long.parseLong(z0)) * Math.pow(10, splitSize)); counter.countInstructions();

        return Long.toString(p1 + p2 + Long.parseLong(z0));
    }

    private String sumStr(String num1, String num2) {
        counter.countInstructions();
        return Long.toString(Long.parseLong(num1) + Long.parseLong(num2));
    }

    @Override
    protected void dynamicProgramingAlgorithm() {

    }

    private int toInt(char number) {
        counter.countInstructions();
        return Character.getNumericValue(number);
    }
}
