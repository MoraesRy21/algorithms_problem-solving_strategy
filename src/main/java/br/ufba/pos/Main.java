package br.ufba.pos;

import br.ufba.pos.input.Input;
import br.ufba.pos.input.InputGenerator;
import br.ufba.pos.input.structure.Point;
import br.ufba.pos.input.structure.TwoLargeNumbers;
import br.ufba.pos.perform.TestPerformance;
import br.ufba.pos.questions.Question1;
import br.ufba.pos.questions.Question2;
import br.ufba.pos.questions.Question3;
import br.ufba.pos.questions.Question5;

import java.util.Arrays;
import java.util.List;

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
            Input<List<Point>> input1 = InputGenerator.generateInputOfPointList(50, 2);
            TestPerformance.executeTestSolutionQuestion(new Question1(), input1);
            questionCalled = true;
        }

        if (listArgs.contains("q2")) {
            Input<TwoLargeNumbers> input2 = InputGenerator.generateInputOfTwoLargeNumbers(10, 4);
            TestPerformance.executeTestSolutionQuestion(new Question2(), input2);
            questionCalled = true;
        }

        if (listArgs.contains("q3")) {
            Input<List<Integer>> input3 = InputGenerator.generateInputOfListOfIntegers(100, 5);
            TestPerformance.executeTestSolutionQuestion(new Question3(), input3);
            questionCalled = true;
        }

        if (listArgs.contains("q5")) {
            Input<List<String>> input5 = InputGenerator.generateInputOfInterviewList(50, 10);
            TestPerformance.executeTestSolutionQuestion(new Question5(), input5);
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
