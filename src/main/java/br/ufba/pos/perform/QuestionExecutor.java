package br.ufba.pos.perform;

import br.ufba.pos.input.Input;
import br.ufba.pos.questions.Question;

import java.util.Iterator;


public class QuestionExecutor {

    public static <T> void executeTestSolutionQuestion(Question<T> question, Input<T> input) {
        System.out.println("============================= " + question.getQuestionTitle() + " =============================");
        System.out.println(question.getQuestionDescription());

        int i = 1;
        Iterator<T> it = input.iterator();
        while(it.hasNext()) {
            T element = it.next();
            printIteration(i, element);
            question.getSolution().setInput(element);
            question.execute();
            i++;
        }

        question.result();
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
}