package br.ufba.pos.problems.p2;

import br.ufba.pos.problems.input.InputGenerator;
import br.ufba.pos.problems.ProblemCase;
import br.ufba.pos.solutions.Solution2;

public class Problem2 extends ProblemCase<TwoLargeNumbers> {

	/**
	 * Default constructor for the Problem2 class.
	 * Initializes the Problem2 instance with default values for executing the
	 * algorithm to multiply large integers.
	 * Specifically calls the overloaded constructor with pre-defined parameters
	 * to set up the problem input and description.
	 */
	public Problem2() {
		this(10, 4);
	}

	/**
	 * Constructs a Problem2 instance with specific parameters defining the number
	 * of executions and the incremental number of bits for generating input of two
	 * large numbers. Initializes the relevant problem metadata, such as title,
	 * description, and input data.
	 *
	 * @param numbersExecution the number of executions to be performed for the algorithm
	 * @param incrementNumBits the increment for the number of bits used in generating the input numbers
	 */
	public Problem2(Integer numbersExecution, Integer incrementNumBits) {
		super(new Solution2());

		super.questionTitle = "Multiplicação de Números Inteiros Grandes";
		super.questionDescription = """
				Multiplicação de Números Inteiros Grandes
				Sejam X e Y dois números inteiros de n dígitos.
				Desenvolva um algoritmo que encontre a multiplicação de X e Y em menos de O(n²) passos.
				Encontre a complexidade do algoritmo desenvolvido.""";

		super.input = InputGenerator.generateInputOfTwoLargeNumbers(numbersExecution, incrementNumBits);
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
