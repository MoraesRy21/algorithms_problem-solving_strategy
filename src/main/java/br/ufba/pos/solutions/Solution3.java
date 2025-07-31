package br.ufba.pos.solutions;

import br.ufba.pos.solutions.counter.CounterHolder;
import br.ufba.pos.solutions.strategies.DivideAndConquerAlgorithm;
import br.ufba.pos.solutions.strategies.GenericAlgorithm;
import br.ufba.pos.solutions.strategies.Algorithms;
import br.ufba.pos.problems.input.UtilsCreators;

import java.util.List;

/**
 * <h2>Busca Binária em Vetor Ordenado</h2>
 *
 * Seja 𝑋 um vetor de 𝑛 inteiros distintos dispostos em ordem crescente.
 * Desenvolva um algoritmo para encontrar algum índice 𝑖 tal que 𝑋𝑖 = 𝑖.
 * O tempo de execução do algoritmo deve ser de 𝑂(log 𝑛).
 */
public class Solution3 extends Solution<List<Integer>> implements GenericAlgorithm, DivideAndConquerAlgorithm {

    private int[] numList;

    public Solution3() {
        fillMapCountersHolders();
    }

    @Override
    public void setInput(List<Integer> input) {
        this.numList = input.stream().mapToInt(i -> i).toArray();
    }

    @Override
    public void executeSolutions() {
        System.out.println(">>>> GENERIC ALGORITHM");
        genericAlgorithm();

        System.out.println(">>>> DIVIDE AND CONQUER ALGORITHM");
        divideAndConquerAlgorithm();
    }

    @Override
    public void genericAlgorithm() {
        CounterHolder counterHolder = mapCountersHolders.get(Algorithms.GENERIC_ALGORITHM.algorithmName);
        counter = counterHolder.getInstructionCounter();

        int target = UtilsCreators.getRandomInt();
        int indexFounded = genericSearch(numList, target);
        if(indexFounded != -1) {
            System.out.println("The element "+target+" was founded in the index of array "+ indexFounded);
        }
        System.out.println("The element "+target+" was not found in the array!");

        counterHolder.putCounterMapInstructionAndResetCounter(numList.length);
    }

    private int genericSearch(int[] input, int target) {
        counter.increment();
        for (int i = 0; i < input.length; i++) { counter.increment();
            if (input[i] == target) { counter.increment();
                counter.increment();
                return i;
            }
        }
        counter.increment();
        return -1;
    }

    @Override
    public void divideAndConquerAlgorithm() {
        CounterHolder counterHolder = mapCountersHolders.get(Algorithms.DIVIDE_AND_CONQUER_ALGORITHM.algorithmName);
        counter = counterHolder.getInstructionCounter();

        int target = UtilsCreators.getRandomInt();
        int indexFounded = binarySearch(numList, target);
        if(indexFounded != -1) {
            System.out.println("The element "+target+" was founded in the index of array "+ indexFounded);
        }
        System.out.println("The element "+target+" was not found in the array!");

        counterHolder.putCounterMapInstructionAndResetCounter(numList.length);
    }

    private int binarySearch(int[] input, int target) {
        int left = 0, right = input.length - 1; counter.increment();
        while (left <= right) { counter.increment();
            int mid = (left + right) / 2; counter.increment();
            if (input[mid] == target) { counter.increment();
                return mid;
            }else if (input[mid] < target) {
                left = mid + 1; counter.increment();
            }else{
                right = mid - 1; counter.increment();
            }
        }
        counter.increment();
        return -1;
    }

}
