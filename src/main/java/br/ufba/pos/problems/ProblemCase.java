package br.ufba.pos.problems;

import br.ufba.pos.charts.Charts;
import br.ufba.pos.problems.input.Input;
import br.ufba.pos.solutions.Solution;

import java.util.Iterator;

/**
 * Represents a question that contains a title, description, and a solution.
 * The solution executes algorithms, and the question can display results
 * using the provided algorithms' counters and plotted charts.
 *
 * @param <IPS> Input Processed by the Solution - the type of input processed by the solution
 */
public abstract class ProblemCase<IPS> {

	protected String questionTitle;
	protected String questionDescription;

	protected Input<IPS> input;

	protected Solution<IPS> solution;

	public ProblemCase(Solution<IPS> solution) {
		this.solution = solution;
	}

	public String problemHelp() {
		String helper = """
				————————————————————————————————————————————————————————————————————————————————————————————————————————
				Problem: %s
				Description: %s
				
				Parameters:
					1º number of execution: The number of executions to be performed.
					2º increment range: The increment range for generating the input data.
				""";
		return String.format(helper, questionTitle, questionDescription);
	}

	abstract public int numberParameters();

	/**
	 * Give the title of the question.
	 *
	 * @return question title
	 */
	public String getQuestionTitle() {
		return questionTitle;
	}

	/**
	 * Give the question description.
	 *
	 * @return question description.
	 */
	public String getQuestionDescription() {
		return questionDescription;
	}

	/**
	 * @return the Solution
	 */
	public Solution<IPS> getSolution() {
		return solution;
	}

	/**
	 * Execute the solution
	 */
	public void execute() {
		solution.executeSolutions();
	}

	public void executeTestSolutionQuestion() {
		System.out.println("============================= " + questionTitle + " =============================");
		System.out.println(questionDescription);

		int i = 1;
		Iterator<IPS> it = input.iterator();
		while(it.hasNext()) {
			IPS element = it.next();
			printIteration(i, element);
			solution.setInput(element);
			execute();
			i++;
		}

		result();
	}

	/**
	 * Print in console what iteration is and what the input were passed.
	 *
	 * @param iterationNumber iteration number of the execution
	 * @param element input element.
	 */
	private static void printIteration(int iterationNumber, Object element) {
		System.out.println("\n———————————————————————————————————————— Iteration N "+iterationNumber);
		System.out.println("Input: " + element.toString());
	}

	/**
	 * Gives the result of the algorithms counters and plot the charts
	 */
	public void result() {
		System.out.println("\n—————————————————————————————————————————————————————— Final Results ——————————————————————————————————————————————————————");
		Charts.plotChart(solution.getMapCountersHolders());
	}
}
