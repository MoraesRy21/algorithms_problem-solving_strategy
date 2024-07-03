package br.ufba.pos.questions;

import br.ufba.pos.input.structure.Point;
import br.ufba.pos.input.structure.TwoLargeNumbers;
import br.ufba.pos.input.structure.TwoListNumber;
import br.ufba.pos.solutions.*;

import java.util.List;

public class QuestionFactory {

	/**
	 * <h2>Par de Pontos Mais Próximos</h2>
	 */
	public static Question<List<Point>> question1() {
		String questionTitle = "Par de Pontos Mais Próximos";
		String questionDescription = """
				Dado um conjunto de n pontos em um plano cartesiano, desenvolva um algoritmo para
				encontrar o par de pontos mais próximos nesse conjunto. O tempo de execução deve
				ser de 𝑂(𝑛 log(𝑛)) no pior caso.""";

		return new Question(new Solution1(), questionTitle, questionDescription);
	}

	/**
	 * <h2>Multiplicação de Números Inteiros Grandes</h2>
	 */
	public static Question<TwoLargeNumbers> question2() {
		String questionTitle = "Multiplicação de Números Inteiros Grandes";
		String questionDescription = """
				Multiplicação de Números Inteiros Grandes
				Sejam X e Y dois números inteiros de n dígitos.
				Desenvolva um algoritmo que encontre a multiplicação de X e Y em menos de O(n²) passos.
				Encontre a complexidade do algoritmo desenvolvido.""";

		return new Question(new Solution2(), questionTitle, questionDescription);
	}

	/**
	 * <h2>Busca Binária em Vetor Ordenado</h2>
	 */
	public static Question<List<Integer>> question3() {
		String questionTitle = "Busca Binária em Vetor Ordenado";
		String questionDescription = """
				Seja 𝑋 um vetor de 𝑛 inteiros distintos dispostos em ordem crescente.
				Desenvolva um algoritmo para encontrar algum índice 𝑖 tal que 𝑋𝑖 = 𝑖.
				O tempo de execução do algoritmo deve ser de 𝑂(log 𝑛).""";

		return new Question(new Solution3(), questionTitle, questionDescription);
	}

	/**
	 * <h2>União Ordenada de Vetores</h2>
	 */
	public static Question<TwoListNumber> question4() {
		String questionTitle = "União Ordenada de Vetores";
		String questionDescription = """
				Sejam 𝑋 e 𝑌 dois vetores ordenados de tamanho 𝑛 e 𝑚, respectivamente.
				Desenvolva um algoritmo para encontrar o k-ésimo elemento
				de 𝑋 U 𝑌. O algoritmo deve executar em 𝑂(log 𝑚 + log 𝑛) unidades de tempo.""";

		return new Question(new Solution4(), questionTitle, questionDescription);
	}

	/**
	 * <h2>Majoritária em Pesquisa de Opinião</h2>
	 */
	public static Question<List<String>> question5() {
		String questionTitle = "Majoritária em Pesquisa de Opinião";
		String questionDescription = """
				Suponha uma pesquisa de opinião pública onde os entrevistados
				respondem à seguinte pergunta: qual a marca de produto
				mais popular dentre todas que você conhece? As respostas de 𝑛
				entrevistados são armazenadas em um vetor |𝑉|.
				
				Elabore um algoritmo de tempo de execução linear para identificar se existe
				uma marca citada por mais da metade dos entrevistados.
				O algoritmo não deve alocar memória extra além da necessária para armazenar |𝑉|""";

		return new Question(new Solution5(), questionTitle, questionDescription);
	}

}
