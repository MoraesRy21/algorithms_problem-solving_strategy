package br.ufba.pos.problems.input;

import br.ufba.pos.problems.p1.Point;
import br.ufba.pos.problems.p2.TwoLargeNumbers;
import br.ufba.pos.problems.p4.TwoListNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Input Generator class witch provide static method to generate types of Inputs.
 */
public class InputGenerator {

	/**
	 * Generate an Input of List of Points starting with initial numbers of increment.
	 *
	 * @param numbersExecution value that's point to numbers of execution given by the size of type of given input.
	 * @param incrementRange value that's describe the range between inputs created by the create method by {@see UtilsCreators} class
	 * @return an input of Point list as {@code List<Point>}
	 */
	public static Input<List<Point>> generateInputOfPointList(int numbersExecution, int incrementRange, int initializeSize) {
		checkNumbersExecution(numbersExecution);
		checkIncrementRange(incrementRange);
		List<List<Point>> list = new ArrayList();

		int sizeListGenerator = initializeSize;
		for(int i=0; i<numbersExecution; i++) {
			List<Point> listRandomPoints = UtilsCreators.createListRandomPoints(sizeListGenerator);
			sizeListGenerator = sizeListGenerator + incrementRange;
			list.add(listRandomPoints);
		}
		return new Input<>(list);
	}

	/**
	 * Generate an Input of List of Points starting with initial size list of 10 elements.
	 *
	 * @param numbersExecution value that's point to numbers of execution given by the size of type of given input.
	 * @param incrementRange value that's describe the range between inputs created by the create method by {@see UtilsCreators} class
	 * @return an input of Point list as {@code List<Point>}
	 */
	public static Input<List<Point>> generateInputOfPointList(int numbersExecution, int incrementRange) {
		return generateInputOfPointList(numbersExecution, incrementRange, 10);
	}

	/**
	 * Generate an Input of
	 *
	 * @param numbersExecution value that's point to numbers of execution given by the size of type of given input.
	 * @param incrementRange value that's describe the range between inputs created by the create method by {@see UtilsCreators} class
	 * @return an input of interviewed list as {@code List<String>}
	 */
	public static Input<List<String>> generateInputOfInterviewList(int numbersExecution, int incrementRange) {
		checkNumbersExecution(numbersExecution);
		checkIncrementRange(incrementRange);
		List<List<String>> list = new ArrayList();

		int sizeListGenerator=incrementRange;
		for(int i=0; i<numbersExecution; i++) {
			List<String> interviewList = UtilsCreators.createListWithUniqueAnswer(sizeListGenerator);
			sizeListGenerator = sizeListGenerator + incrementRange;
			list.add(interviewList);
		}
		return new Input<>(list);
	}

	/**
	 * Test case scenario
	 *
	 * @return an input of interviewed list as {@code List<String>}
	 */
	public static Input<List<String>> generateInputOfInterviewListTest() {
		List<List<String>> list = new ArrayList();

		List<String> interviewList = Arrays.asList("Carro", "Sapato", "Roupa", "Sapato", "Roupa", "Roupa", "Carro", "Roupa");
		list.add(interviewList);
		return new Input<>(list);
	}

	/**
	 * Generate an Input of random two large numbers.
	 *
	 * @param numbersExecution value that's point to numbers of execution given by the size of type of given input.
	 * @param incrementNumBits value that's describe the number of bits between inputs created by the create method by {@see UtilsCreators} class.
	 * @return an input of two large numbers as {@code List<TwoLargeNumbers>}
	 */
	public static Input<TwoLargeNumbers> generateInputOfTwoLargeNumbers(int numbersExecution, int incrementNumBits) {
		checkNumbersExecution(numbersExecution);
		checkIncrementRange(incrementNumBits);
		List<TwoLargeNumbers> list = new ArrayList();

		int bits = 64;
		for(int i=0; i<numbersExecution; i++) {
			TwoLargeNumbers twoLargeNumbers = UtilsCreators.createTwoLargeNumbers(bits, true);
			list.add(twoLargeNumbers);
			bits = bits + incrementNumBits;
		}
		return new Input<>(list);
	}

	/**
	 * Generate an Input of list of random positive numbers starting with a list of minimum of 10.
	 *
	 * @param numbersExecution value that's point to numbers of execution given by the size of type of given input.
	 * @param incrementRange value that's describe the range between inputs created by the create method by {@see UtilsCreators} class
	 * @return an input of random list of integer number as {@code List<Integer>}
	 */
	public static Input<List<Integer>> generateInputOfListOfIntegers(int numbersExecution, int incrementRange) {
		checkNumbersExecution(numbersExecution);
		checkIncrementRange(incrementRange);
		List<List<Integer>> list = new ArrayList();

		int increment = 10;
		for(int i=0; i<numbersExecution; i++) {
			List<Integer> numList = UtilsCreators.createIntegerListOfUniqueNumbers(increment);
			list.add(numList);
			increment = increment + incrementRange;
		}
		return new Input<>(list);
	}


	/**
	 * Generate an Input of two list numbers.
	 *
	 * @param numbersExecution value that's point to numbers of execution given by the size of type of given input.
	 * @param incrementRange value that's describe the range between inputs created by the create method by {@see UtilsCreators} class
	 * @return an input of random list of integer number as {@code List<Integer>}
	 */
	public static Input<TwoListNumber> generateInputOfTwoListNumber(int numbersExecution, int incrementRange) {
		checkNumbersExecution(numbersExecution);
		checkIncrementRange(incrementRange);
		List<TwoListNumber> list = new ArrayList();

		int listsSizes = 10;
		for(int i=0; i<numbersExecution; i++) {
			TwoListNumber twoListNumber = UtilsCreators.createTwoSortedListNumberOfSameSize(listsSizes);
			list.add(twoListNumber);
			listsSizes = listsSizes + incrementRange;
		}
		return new Input<>(list);
	}

	private static void checkNumbersExecution(int numbersExecution) {
		if(numbersExecution <= 0) {
			throw new IllegalArgumentException("The numbersExecution must be > 0");
		}
	}

	private static void checkIncrementRange(int incrementRange) {
		if(incrementRange < 0) {
			throw new IllegalArgumentException("The input increment must be >= 0");
		}
	}

}
