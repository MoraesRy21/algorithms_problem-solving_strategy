package br.ufba.pos.solutions;

import br.ufba.pos.counter.CounterHolder;
import br.ufba.pos.input.structure.TwoLargeNumbers;
import br.ufba.pos.solutions.strategies.*;
import br.ufba.pos.utils.Algorithms;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Q02: Multiplicação de Números Inteiros Grandes
 * Sejam X e Y dois números inteiros de n dígitos.
 * Desenvolva um algoritmo que encontre a multiplicação de X e Y em menos de O(n²) passos.
 * Encontre a complexidade do algoritmo desenvolvido.
 */
public class Solution2 extends Solution<TwoLargeNumbers> implements GenericAlgorithm, DivideAndConquerAlgorithm {

    private TwoLargeNumbers twoLargeNumbers;

    public Solution2() {
        fillMapCountersHolders();
    }

    /**
     * Fill the map counter holders by the implemented interfaces algorithms strategies.
     */
    @Override
    protected void fillMapCountersHolders() {
        List<Class<?>> interfaces = Arrays.asList(this.getClass().getInterfaces());
        if(interfaces.contains(GenericAlgorithm.class)) {
            String gcName = Algorithms.GENERIC_ALGORITHM.algorithmName;
            mapCountersHolders.put(gcName, new CounterHolder<String>(gcName, new LinkedHashMap<String, Integer>()));
        }
        if(interfaces.contains(DivideAndConquerAlgorithm.class)) {
            String dcName = Algorithms.DIVIDE_AND_CONQUER_ALGORITHM.algorithmName;
            mapCountersHolders.put(dcName, new CounterHolder<String>(dcName, new LinkedHashMap<String, Integer>()));
        }
        if(interfaces.contains(DynamicProgramingAlgorithm.class)) {
            String dpName = Algorithms.DYNAMIC_PROGRAMING_ALGORITHM.algorithmName;
            mapCountersHolders.put(dpName, new CounterHolder<String>(dpName, new LinkedHashMap<String, Integer>()));
        }
        if(interfaces.contains(GreedyAlgorithm.class)) {
            String gName = Algorithms.GREEDY_ALGORITHM.algorithmName;
            mapCountersHolders.put(gName, new CounterHolder<String>(gName, new LinkedHashMap<String, Integer>()));
        }
        if(interfaces.contains(BacktrackingAlgorithm.class)) {
            String btName = Algorithms.BACKTRACKING_ALGORITHM.algorithmName;
            mapCountersHolders.put(btName, new CounterHolder<String>(btName, new LinkedHashMap<String, Integer>()));
        }
    }

    @Override
    public void setInput(TwoLargeNumbers input) {
        this.twoLargeNumbers = input;
    }

    @Override
    public void executeSolutions() {
        System.out.println(">>>> GENERIC ALGORITHM");
        genericAlgorithm();

        System.out.println(">>>> DIVIDE AND CONQUER ALGORITHM");
        divideAndConquerAlgorithm();

        // Printing the expected and corresponding actual product
        System.out.println("Expected multiplication: " + twoLargeNumbers.multiplication().toString()+"\n");
    }



    @Override
    public void genericAlgorithm() {
        CounterHolder counterHolder = mapCountersHolders.get(Algorithms.GENERIC_ALGORITHM.algorithmName);
        counter = counterHolder.getInstructionCounter();

        String num1 = twoLargeNumbers.getNum1();
        String num2 = twoLargeNumbers.getNum2();
        String result = multipleNumbers(num1, num2);
        System.out.println("Multiplication result of " + num1 + " x " + num2 + " = " + result);

        counterHolder.putCounterMapInstructionAndResetCounter(StringUtils.leftPad(result, 42, "0"));
    }

    private String multipleNumbers(String value1, String value2) {
        char[] num1 = value1.toCharArray();
        char[] num2 = value2.toCharArray();
        counter.increment(2);

        BigInteger finalResult = new BigInteger("0");
        counter.increment(2);

        for(int i=num2.length - 1; i>=0; i--) { counter.increment();
            int upNum = 0;
            int resultPosition = num2.length - 1 - i;
            String numericCompaund = ""; counter.increment(4);
            if(resultPosition != 0) {
                numericCompaund = String.format("%0"+resultPosition+"d", 0); counter.increment();
            }

            int unit2 = toInt(num2[i]);
            counter.increment(3);
            for(int j=num1.length - 1; j>=0; j--) { counter.increment();
                int unit1 = toInt(num1[j]);
                int multiplication = unit2 * unit1 + upNum;
                upNum = multiplication / 10;
                numericCompaund = (multiplication % 10) + numericCompaund; counter.increment(7);
            }
            counter.increment();
            if(upNum > 0) { counter.increment();
                numericCompaund = upNum + numericCompaund; counter.increment();
            }
            finalResult = finalResult.add(new BigInteger(numericCompaund)); counter.increment(2);
            counter.increment();
        }
        return finalResult.toString();
    }

    @Override
    public void divideAndConquerAlgorithm() {
        CounterHolder counterHolder = mapCountersHolders.get(Algorithms.DIVIDE_AND_CONQUER_ALGORITHM.algorithmName);
        counter = counterHolder.getInstructionCounter();

        String num1 = twoLargeNumbers.getNum1();
        String num2 = twoLargeNumbers.getNum2();
        String result = mult(num1.toCharArray(), num2.toCharArray());
        System.out.println("Multiplication result of " + num1 + " x " + num2 + " = " + result);

        counterHolder.putCounterMapInstructionAndResetCounter(StringUtils.leftPad(result, 42, "0"));
    }

    private String karatsuba(String value1, String value2) {
        char[] num1 = value1.toCharArray(); counter.increment();
        char[] num2 = value2.toCharArray(); counter.increment();

        if(value1.length() <= 1 || value2.length() <= 1) { counter.increment();
            int unitNum1 = toInt(num1[num1.length - 1]); counter.increment();
            int unitNum2 = toInt(num2[num2.length - 1]); counter.increment();
            return Integer.toString(unitNum1 * unitNum2);
        }

        int splitSize = Math.max(num1.length, num2.length) / 2; counter.increment();

        String high1 = value1.length() > splitSize ? value1.substring(0, value1.length() - splitSize) : "0"; counter.increment();
        String low1 = value1.substring(splitSize); counter.increment();
        String high2 = value2.length() > splitSize ? value2.substring(0, splitSize) : "0"; counter.increment();
        String low2 = value2.substring(splitSize); counter.increment();

        String z0 = karatsuba(low1, low2);
        String z1 = karatsuba(sumStr(low1, high1), sumStr(low2, high2));
        String z2 = karatsuba(high1, high2);

        long p1 = (long) (Long.parseLong(z2) * (Math.pow(10, splitSize * 2))); counter.increment();
        long p2 = (long) ((Long.parseLong(z1) - Long.parseLong(z2) - Long.parseLong(z0)) * Math.pow(10, splitSize)); counter.increment();

        return Long.toString(p1 + p2 + Long.parseLong(z0));
    }

    private String sumStr(String num1, String num2) {
        counter.increment();
        return Long.toString(Long.parseLong(num1) + Long.parseLong(num2));
    }


    private int toInt(char number) {
        return Character.getNumericValue(number);
    }

    // Main driver method
    private String mult(char[] x, char[] y) {
        // Checking only if input is within range
        counter.increment(3);
        if (x.length < 2 && y.length < 2) {
            counter.increment();
            // Multiplying the inputs entered
            return Integer.toString(toInt(x[0]) * toInt(y[0]));
        }

        // Declaring variables in order to
        // Find length of both integer
        // numbers x and y
        int noOneLength = numLength(x);
        int noTwoLength = numLength(y);

        // Finding maximum length from both numbers
        // using math library max function
        int maxNumLength = Math.max(noOneLength, noTwoLength); counter.increment();

        // Rounding up the divided Max length
        Integer halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);
        counter.increment(3);

        // Multiplier
        long maxNumLengthTen = (long)Math.pow(10, halfMaxNumLength); counter.increment();

        // Compute the expressions
        long a = new BigInteger(String.valueOf(x)).longValue() / maxNumLengthTen; counter.increment();
        long b = new BigInteger(String.valueOf(x)).longValue() % maxNumLengthTen; counter.increment();
        long c = new BigInteger(String.valueOf(y)).longValue() / maxNumLengthTen; counter.increment();
        long d = new BigInteger(String.valueOf(y)).longValue() % maxNumLengthTen; counter.increment();


        // Compute all mutilpying variables
        // needed to get the multiplication
        long z0 = Long.parseLong(mult(Long.toString(a).toCharArray(), Long.toString(c).toCharArray())); counter.increment();
        long z1 = Long.parseLong(mult(Long.toString(a + b).toCharArray(), Long.toString(c + d).toCharArray())); counter.increment(3);
        long z2 = Long.parseLong(mult(Long.toString(b).toCharArray(), Long.toString(d).toCharArray())); counter.increment();

        long ans = (z0 * (long)Math.pow(10, halfMaxNumLength * 2) + ((z1 - z0 - z2) * (long)Math.pow(10, halfMaxNumLength) + z2));
        counter.increment(8);

        return Long.toString(ans);
    }

    private int numLength(char[] n)
    {
        int noLen = 0;
        long length = n.length;
        counter.increment(3);
        while (length > 0) {
            noLen++;
            length /= 10;
            counter.increment(4);
        }

        // Returning length of number n
        return noLen;
    }
}
