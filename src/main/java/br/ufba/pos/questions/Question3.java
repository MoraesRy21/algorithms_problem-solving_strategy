package br.ufba.pos.questions;

import br.ufba.pos.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <h2>Busca Binária em Vetor Ordenado</h2>
 *
 * Seja 𝑋 um vetor de 𝑛 inteiros distintos dispostos em ordem crescente.
 * Desenvolva um algoritmo para encontrar algum índice 𝑖 tal que 𝑋𝑖 = 𝑖.
 * O tempo de execução do algoritmo deve ser de 𝑂(log 𝑛).
 */
public class Question3 extends Question<List<Integer>> {

    private int[] numList;

    public Question3() {
        super.gCounterMapInstruction = new LinkedHashMap<Integer, Integer>();
        super.dcCounterMapInstruction = new LinkedHashMap<Integer, Integer>();
    }

    @Override
    public String getQuestionTitle() {
        return "Busca Binária em Vetor Ordenado";
    }

    @Override
    public List<Integer> getInput() {
        return new ArrayList(Arrays.asList(numList));
    }

    @Override
    public void setInput(List<Integer> input) {
        this.numList = input.stream().mapToInt(i -> i).toArray();
    }

    @Override
    protected void genericAlgorithm() {
        int target = Utils.getRandomInt();
        int indexFounded = genericSearch(numList, target);

        if(indexFounded != -1) {
            System.out.println("The element "+target+" was founded in the index of array "+ indexFounded);
        }
        System.out.println("The element "+target+" was not found in the array!");

        gCounterMapInstruction.put(numList.length, counter.getCounter());
        counter.resetCounter();
    }

    private int genericSearch(int[] input, int target) {
        counter.countInstructions();
        for (int i = 0; i < input.length; i++) { counter.countInstructions();
            if (input[i] == target) { counter.countInstructions();
                counter.countInstructions();
                return i;
            }
        }
        counter.countInstructions();
        return -1;
    }

    @Override
    protected void divideAndConquerAlgorithm() {
        int target = Utils.getRandomInt();
        int indexFounded = binarySearch(numList, target);

        if(indexFounded != -1) {
            System.out.println("The element "+target+" was founded in the index of array "+ indexFounded);
        }
        System.out.println("The element "+target+" was not found in the array!");

        dcCounterMapInstruction.put(numList.length, counter.getCounter());
        counter.resetCounter();
    }

    private int binarySearch(int[] input, int target) {
        int left = 0, right = input.length - 1; counter.countInstructions();
        while (left <= right) { counter.countInstructions();
            int mid = (left + right) / 2; counter.countInstructions();
            if (input[mid] == target) { counter.countInstructions();
                return mid;
            }else if (input[mid] < target) {
                left = mid + 1; counter.countInstructions();
            }else{
                right = mid - 1; counter.countInstructions();
            }
        }
        counter.countInstructions();
        return -1;
    }

    @Override
    protected void dynamicProgramingAlgorithm() {

    }
}
