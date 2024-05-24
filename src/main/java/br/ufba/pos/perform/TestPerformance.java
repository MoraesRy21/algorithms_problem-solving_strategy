package br.ufba.pos.perform;

import br.ufba.pos.questions.Question;
import br.ufba.pos.utils.Input;

import java.util.Iterator;


public class TestPerformance {

    public static <T> void executeTestSolutionQuestion(Question<T> question, Input<T> input) {
        System.out.println("============================= " + question.getQuestionTitle() + " =============================");

        int i = 1;
        Iterator<T> it = input.iterator();
        while(it.hasNext()) {
            T element = it.next();
            question.setInput(element);
            System.out.println("———————————————————————————————————————— Iteration N "+i);
            System.out.println("Input: " + element.toString());
            question.execute();
            i++;
        }

        question.result();
    }
}