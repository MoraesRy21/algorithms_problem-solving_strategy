package br.ufba.pos.utils;

import br.ufba.pos.utils.structure.Point;
import br.ufba.pos.utils.structure.TwoLargeNumbers;

import java.util.*;
import java.util.function.Consumer;

/**
 *
 * @param <T> type of input
 */
public class Input<T> implements Iterable<T> {

    private List<T> list;

    private Input(List<T> list) {
        this.list = list;
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    public static Generator generator() {
        return new Generator();
    }

    public static class Generator {

        public static Input<List<Point>> generateInputOfPointList(int numbersExecution, int incrementRange) {
            List<List<Point>> list = new ArrayList();

            int sizeListGenerator=incrementRange;
            for(int i=0; i<numbersExecution; i++) {
                List<Point> listRandomPoints = Utils.creatListRandomPoints(sizeListGenerator);
                sizeListGenerator = sizeListGenerator + incrementRange;
                list.add(listRandomPoints);
            }
            return new Input<>(list);
        }

        public static Input<List<String>> generateInputOfInterviewList(int numbersExecution, int incrementRange) {
            List<List<String>> list = new ArrayList();

            int sizeListGenerator=incrementRange;
            for(int i=0; i<numbersExecution; i++) {
                List<String> interviewList = Utils.createListWithUniqueAnswer(sizeListGenerator);
                sizeListGenerator = sizeListGenerator + incrementRange;
                list.add(interviewList);
            }
            return new Input<>(list);
        }

        public static Input<List<String>> generateInputOfInterviewListTest() {
            List<List<String>> list = new ArrayList();

            List<String> interviewList = Arrays.asList("Carro", "Sapato", "Roupa", "Sapato", "Roupa", "Roupa", "Carro", "Roupa");
            list.add(interviewList);
            return new Input<>(list);
        }

        public static Input<TwoLargeNumbers> generateInputOfTwoLargeNumbers(int numbersExecution, int incrementNumBits) {
            List<TwoLargeNumbers> list = new ArrayList();

            int bits = 64;
            for(int i=0; i<numbersExecution; i++) {
                TwoLargeNumbers twoLargeNumbers = Utils.createTwoLargeNumbers(bits);
                list.add(twoLargeNumbers);
                bits = bits + incrementNumBits;
            }
            return new Input<>(list);
        }

        public static Input<List<Integer>> generateInputOfListOfIntegers(int numbersExecution, int incrementListSize) {
            List<List<Integer>> list = new ArrayList();

            int increment = 10;
            for(int i=0; i<numbersExecution; i++) {
                List<Integer> numList = Utils.createIntegerListOfUniqueNumbers(increment);
                list.add(numList);
                increment = increment + incrementListSize;
            }
            return new Input<>(list);
        }
    }
}
