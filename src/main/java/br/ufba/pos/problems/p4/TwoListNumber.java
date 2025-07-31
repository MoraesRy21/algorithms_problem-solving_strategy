package br.ufba.pos.problems.p4;

import java.util.List;

/**
 * Simple structure to hold two list of numbers.
 */
public class TwoListNumber {

	private List<Integer> listNumber1;
	private List<Integer> listNumber2;

	private int[] arrayNumber1;
	private int[] arrayNumber2;

	/**
	 * Default constructor fill all attributes.
	 *
	 * @param listNumber1 list of number
	 * @param listNumber2 list of number
	 */
	public TwoListNumber(List<Integer> listNumber1, List<Integer> listNumber2) {
		this.listNumber1 = listNumber1;
		this.listNumber2 = listNumber2;
		arrayNumber1 = listNumber1.stream().mapToInt(Integer::intValue).toArray();
		arrayNumber2 = listNumber2.stream().mapToInt(Integer::intValue).toArray();
	}

	public List<Integer> getListNumber1() {
		return listNumber1;
	}

	public List<Integer> getListNumber2() {
		return listNumber2;
	}

	public int[] getListNumber1AsArray() {
		return arrayNumber1;
	}

	public int[] getListNumber2AsArray() {
		return arrayNumber2;
	}
}
