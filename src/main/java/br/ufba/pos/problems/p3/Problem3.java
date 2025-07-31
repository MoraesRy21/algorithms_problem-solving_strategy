package br.ufba.pos.problems.p3;

import br.ufba.pos.problems.ProblemCase;
import br.ufba.pos.problems.input.InputGenerator;
import br.ufba.pos.solutions.Solution3;

import java.util.List;

/**
 * Represents the Problem3 class which extends the ProblemCase class and is designed to work on a binary search problem
 * in an ordered array. The purpose of this class is to find an index `i` such that the value at index `i` in the
 * array equals `i`, specifically designed to execute with a time complexity of O(log n).
 */
public class Problem3 extends ProblemCase<List<Integer>> {

	/**
	 * Default constructor for the Problem3 class.
	 *
	 * Creates an instance of the Problem3 class with default parameters.
	 * Utilizes the auxiliary constructor to initialize the number of executions
	 * to be performed and the increment range for generating input values.
	 */
	public Problem3() {
		this(100, 5);
	}

	/**
	 * Constructor for the Problem3 class.
	 *
	 * Initializes an instance of the Problem3 class with specific parameters such as
	 * the number of executions and the increment range. Sets up the problem description
	 * and input for solving the binary search problem in an ordered array.
	 *
	 * @param numbersExecution the number of executions to be performed
	 * @param incrementRange the range of increment values used for generating the input
	 */
	public Problem3(Integer numbersExecution, Integer incrementRange) {
		super(new Solution3());

		super.questionTitle = "Busca Binária em Vetor Ordenado";
		super.questionDescription = """
				Seja 𝑋 um vetor de 𝑛 inteiros distintos dispostos em ordem crescente.
				Desenvolva um algoritmo para encontrar algum índice 𝑖 tal que 𝑋𝑖 = 𝑖.
				O tempo de execução do algoritmo deve ser de 𝑂(log 𝑛).""";

		super.input = InputGenerator.generateInputOfListOfIntegers(numbersExecution, incrementRange);
	}

	@Override
	public String problemHelp() {
		return super.problemHelp();
	}

	@Override
	public int numberParameters() {
		return 2;
	}
}
