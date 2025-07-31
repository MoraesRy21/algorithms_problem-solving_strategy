package br.ufba.pos.problems.p5;

import br.ufba.pos.problems.input.InputGenerator;
import br.ufba.pos.problems.ProblemCase;
import br.ufba.pos.solutions.Solution5;

import java.util.List;

/**
 * Represents the Problem5 class that extends the abstract class ProblemCase and utilizes
 * the Solution5 implementation to solve a specific problem related to opinion survey analysis.
 *
 * This problem focuses on determining the most frequently mentioned brand by the respondents
 * in a public opinion survey. The algorithm for solving this problem operates in linear time complexity
 * and does not allocate additional memory outside of what is required for the input data.
 */
public class Problem5 extends ProblemCase<List<String>> {

	/**
	 * Default constructor for the Problem5 class.
	 * This constructor initializes an instance of Problem5 with default parameters:
	 * 50 for the number of executions and 10 for the increment range.
	 */
	public Problem5() {
		this(50, 10);
	}

	/**
	 * Constructs a Problem5 instance with the specified parameters.
	 *
	 * @param numbersExecution the number of executions for generating input data
	 * @param incrementRange the range increment value used for generating input data
	 */
	public Problem5(Integer numbersExecution, Integer incrementRange) {
		super(new Solution5());

		super.questionTitle = "Majoritária em Pesquisa de Opinião";
		super.questionDescription = """
				Suponha uma pesquisa de opinião pública onde os entrevistados
				respondem à seguinte pergunta: qual a marca de produto
				mais popular dentre todas que você conhece? As respostas de 𝑛
				entrevistados são armazenadas em um vetor |𝑉|.
				
				Elabore um algoritmo de tempo de execução linear para identificar se existe
				uma marca citada por mais da metade dos entrevistados.
				O algoritmo não deve alocar memória extra além da necessária para armazenar |𝑉|""";

		super.input = InputGenerator.generateInputOfInterviewList(numbersExecution, incrementRange);
	}

	public String problemHelp() {
		return super.problemHelp();
	}

	@Override
	public int numberParameters() {
		return 2;
	}
}
