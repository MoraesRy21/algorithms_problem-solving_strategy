package br.ufba.pos;

import br.ufba.pos.input.Input;
import br.ufba.pos.input.InputGenerator;
import br.ufba.pos.input.structure.Point;
import br.ufba.pos.input.structure.TwoLargeNumbers;
import br.ufba.pos.input.structure.TwoListNumber;
import br.ufba.pos.perform.QuestionExecutor;
import br.ufba.pos.questions.*;
import br.ufba.pos.solutions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Proponha duas soluções, uma usando divisão e conquista enquanto a outra não faz
 * uso desta técnica. Você deve comparar ambas as soluções experimentalmente, para cada um dos problemas.
 * Para tanto, várias instâncias devem ser geradas, com crescentes tamanhos de entrada.
 * O esforço computacional medido para encontrar as soluções deve então ser comparado com as
 * respectivas complexidades computacionais dadas pelas expressões assintóticas associadas.
 * <p>
 * Por exemplo, suponha que um problema P a ser resolvido tem instâncias de tamanho n, com n representando
 * o número de elementos contidos numa sequência de entrada. </br>
 * <p>
 * Para a avaliação experimental, diversas sequências de
 * entrada devem ser geradas para cada valor de n considerado. Se A e B são dois algoritmos que
 * resolvem P, o segundo baseado em divisão e conquista, com complexidades O(n²) e O(n·log(n)), respectivamente,
 * eles seriam avaliados experimentalmente contabilizando o esforço médio que fazem para resolver P para as diversas
 * instâncias de tamanho n a fim de:
 * <ul>
 *     <li>A) Saber se o esforço médio está próximo das respectivas complexidades (no pior caso)</li>
 *     <li>B) mensurar o ganho obtido com a versão baseada em divisão e conquista.</li>
 * </ul>
 */
public class Main {
    public static void main(String[] args) {

        boolean questionCalled = false;
        List<String> listArgs = Arrays.asList(args);

        if(listArgs.contains("q1")) {
            Input<List<Point>> input = InputGenerator.generateInputOfPointList(50, 4);
            Question<List<Point>> question = QuestionFactory.question1();
            QuestionExecutor.executeTestSolutionQuestion(question, input);
            questionCalled = true;
        }

        if (listArgs.contains("q2")) {
            Input<TwoLargeNumbers> input = InputGenerator.generateInputOfTwoLargeNumbers(10, 4);
            Question<TwoLargeNumbers> question = QuestionFactory.question2();
            QuestionExecutor.executeTestSolutionQuestion(question, input);
            questionCalled = true;
        }

        if (listArgs.contains("q3")) {
            Input<List<Integer>> input = InputGenerator.generateInputOfListOfIntegers(100, 5);
            Question<List<Integer>> question = QuestionFactory.question3();
            QuestionExecutor.executeTestSolutionQuestion(question, input);
            questionCalled = true;
        }

        if (listArgs.contains("q4")) {
            Input<TwoListNumber> input = InputGenerator.generateInputOfTwoListNumber(50, 5);
            Question<TwoListNumber> question = QuestionFactory.question4();
            QuestionExecutor.executeTestSolutionQuestion(question, input);
            questionCalled = true;
        }

        if (listArgs.contains("q5")) {
            Input<List<String>> input = InputGenerator.generateInputOfInterviewList(50, 10);
            Question<List<String>> question = QuestionFactory.question5();
            QuestionExecutor.executeTestSolutionQuestion(question, input);
            questionCalled = true;
        }

        if(!questionCalled) {
            String message = "Invalid args. Args were not set.\n" +
                    "Valid Args: 'q1', 'q2', 'q3', 'q4', 'q5'.\n\n" +
                    "Non execution was performed! Set the write args!";
            System.err.println(message);
        }
    }
}
