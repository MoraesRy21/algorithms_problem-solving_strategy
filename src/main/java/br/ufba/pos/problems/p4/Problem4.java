package br.ufba.pos.problems.p4;

import br.ufba.pos.problems.ProblemCase;
import br.ufba.pos.problems.input.InputGenerator;
import br.ufba.pos.solutions.Solution4;

/**
 * Represents a specific problem subclass that extends the ProblemCase class
 * with the type parameter TwoListNumber. This problem consists of finding
 * the k-th element in the union of two sorted arrays in optimal time complexity.
 * It initializes a description, title, and prepared input data
 * for the problem statement and solution execution.
 */
public class Problem4 extends ProblemCase<TwoListNumber> {

	/**
	 * Default constructor for the Problem4 class.
	 * Initializes the problem instance with predefined execution parameters.
	 * When invoked, it sets up the problem to generate 50 input test cases
	 * with an increment range of 5.
	 */
	public Problem4() {
		this(50, 5);
	}

	/**
	 * Constructs a new instance of the Problem4 class with specified execution settings.
	 * Initializes the problem statement and generates input data according to the provided parameters.
	 *
	 * @param numbersExecution the number of input test cases to generate
	 * @param incrementRange the size of the incremental range to use for generating input test cases
	 */
	public Problem4(Integer numbersExecution, Integer incrementRange) {
		super(new Solution4());

		super.questionTitle = "União Ordenada de Vetores";
		super.questionDescription = """
				Sejam 𝑋 e 𝑌 dois vetores ordenados de tamanho 𝑛 e 𝑚, respectivamente.
				Desenvolva um algoritmo para encontrar o k-ésimo elemento
				de 𝑋 U 𝑌. O algoritmo deve executar em 𝑂(log 𝑚 + log 𝑛) unidades de tempo.""";

		super.input = InputGenerator.generateInputOfTwoListNumber(numbersExecution, incrementRange);
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
