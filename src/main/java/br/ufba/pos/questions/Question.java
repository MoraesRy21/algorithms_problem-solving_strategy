package br.ufba.pos.questions;

import br.ufba.pos.charts.Charts;
import br.ufba.pos.solutions.Solution;

/**
 *
 * @param <T> type of input of the question
 */
public class Question<T> {

	String questionTitle;
	String questionDescription;

	Solution<T> solution;

	public Question(Solution<T> solution) {
		this.solution = solution;
	}

	public Question(Solution<T> solution, String questionTitle, String questionDescription) {
		this.solution = solution;
		this.questionTitle = questionTitle;
		this.questionDescription = questionDescription;
	}

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
	public Solution<T> getSolution() {
		return solution;
	}

	/**
	 * Execute the solution
	 */
	public void execute() {
		solution.executeSolutions();
	}

	/**
	 * Gives the result of the algorithms counters and plot the charts
	 */
	public void result() {
		System.out.println("\n—————————————————————————————————————————————————————— Final Results ——————————————————————————————————————————————————————");
		Charts.plotChart(solution.getMapCountersHolders());
	}
}
