package br.ufba.pos.utils;

public enum Algorithms {

	GENERIC_ALGORITHM("Generic Algorithm"),
	DIVIDE_AND_CONQUER_ALGORITHM("Divide and Conquer Algorithm"),
	DYNAMIC_PROGRAMING_ALGORITHM("Dynamic Programing Algorithm"),
	GREEDY_ALGORITHM("Greedy Algorithm"),
	BACKTRACKING_ALGORITHM("Backtracking Algorithm");

	public final String algorithmName;

	Algorithms(String algorithmName) {
		this.algorithmName = algorithmName;
	}
}
