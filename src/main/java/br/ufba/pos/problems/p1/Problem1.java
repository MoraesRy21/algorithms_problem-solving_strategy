package br.ufba.pos.problems.p1;

import br.ufba.pos.problems.input.InputGenerator;
import br.ufba.pos.problems.ProblemCase;
import br.ufba.pos.solutions.Solution1;

import java.util.List;

public class Problem1 extends ProblemCase<List<Point>> {

	/**
	 * Default constructor for the Problem1 class.
	 *
	 * This constructor initializes the Problem1 instance with default values
	 * for the number of executions and the increment range as 50 and 4 respectively.
	 * It delegates to the parameterized constructor to set up the instance.
	 */
	public Problem1() {
		this(50, 4);
	}

	/**
	 * Initializes a Problem1 instance with the given number of executions and range increment.
	 * This method sets up the problem instance with a title, description, and generated input data.
	 *
	 * @param numbersExecution the number of executions to perform
	 * @param incrementRange the increment range for input generation
	 */
	public Problem1(Integer numbersExecution, Integer incrementRange) {
		super(new Solution1());

		super.questionTitle = "Par de Pontos Mais Próximos";
		super.questionDescription = """
				Dado um conjunto de n pontos em um plano cartesiano, desenvolva um algoritmo para
				encontrar o par de pontos mais próximos nesse conjunto. O tempo de execução deve
				ser de 𝑂(𝑛 log(𝑛)) no pior caso.""";

		super.input = InputGenerator.generateInputOfPointList(numbersExecution, incrementRange);
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
