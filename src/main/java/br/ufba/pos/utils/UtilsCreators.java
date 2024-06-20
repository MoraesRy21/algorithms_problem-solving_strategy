package br.ufba.pos.utils;

import br.ufba.pos.input.structure.Point;
import br.ufba.pos.input.structure.TwoLargeNumbers;

import java.math.BigInteger;
import java.util.*;

public class UtilsCreators {

    static final List<String> products = Arrays.asList("Smartphones", "Notebooks", "Consoles", "Smartwatches", "Wireless Ears Buds",
            "Smart light", "Air Fryers", "Portable Vacuum Cleaner", "Office chair", "Yoga Mats", "Posture Corrector", "Water bottles",
            "Modeling Belt", "Books", "Board games", "Home appliances");

    private static Random random = new Random();

    public static List<Point> createListRandomPoints(int numPoints) {
        List<Point> pointList = new ArrayList<>();

        for(int i=0; i<numPoints; i++) {
            int x = random.nextInt(0, 100);
            int y = random.nextInt(0, 100);
            pointList.add(new Point(x, y));
        }

        System.out.println(pointList);
        return pointList;
    }

    /**
     * Create a list of product as answer of interview persons.
     *
     * @param interviewNumber needs to be a number more than 1.
     * @return a list of all product answered.
     */
    public static List<String> createListWithUniqueAnswer(int interviewNumber) {
        List<String> interviewProducts = new ArrayList<>();
        for(int i=0; i<interviewNumber; i++) {
            int productIndex = random.nextInt(0, products.size());
            interviewProducts.add(products.get(productIndex));
        }
        return interviewProducts;
    }

    /**
     * Create an object with to large numbers
     *
     * @param numBits number of bits to generate a numbers.
     * @param absolute true for positive numbers only or false to integer numbers.
     * @return TwoLargeNumbers object.
     */
    public static TwoLargeNumbers createTwoLargeNumbers(int numBits, boolean absolute) {
        BigInteger value1 = new BigInteger(numBits, random);
        BigInteger value2 = new BigInteger(numBits, random);
        if(absolute) {
            return new TwoLargeNumbers(value1.abs(), value2.abs());
        }
        return new TwoLargeNumbers(value1, value2);
    }

    /**
     * Create a list of integer in a range of 1 to 100_000
     *
     * @return List<Integer> object.
     */
    public static List<Integer> createIntegerList(int quantityNum) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<quantityNum; i++) {
            list.add(random.nextInt(1, 100_000));
        }
        return list;
    }

    /**
     * Create a list of integer in a range of 1 to 100_000
     *
     * @return List<Integer> object.
     */
    public static List<Integer> createIntegerListOfUniqueNumbers(int quantityNum) {
        Set<Integer> set = new HashSet<>();
        while(set.size() < quantityNum) {
            set.add(random.nextInt(1, 100_000));
        }
        return set.stream().toList();
    }

    public static int getRandomInt() {
        return random.nextInt(1, 100_000);
    }
}
