package br.ufba.pos.solutions;

import br.ufba.pos.solutions.counter.CounterHolder;
import br.ufba.pos.solutions.strategies.DivideAndConquerAlgorithm;
import br.ufba.pos.solutions.strategies.GenericAlgorithm;
import br.ufba.pos.solutions.strategies.Algorithms;

import java.util.List;

/**
 * <h2>Majoritária em Pesquisa de Opinião</h2>
 *
 * Suponha uma pesquisa de opinião pública onde os entrevistados
 * respondem à seguinte pergunta: qual a marca de produto
 * mais popular dentre todas que você conhece? As respostas de 𝑛
 * entrevistados são armazenadas em um vetor |𝑉|.
 * <br/>
 * Elabore um algoritmo de tempo de execução linear para identificar se existe
 * uma marca citada por mais da metade dos entrevistados.
 * O algoritmo não deve alocar memória extra além da necessária para armazenar |𝑉|
 */
public class Solution5 extends Solution<List<String>> implements GenericAlgorithm, DivideAndConquerAlgorithm {

    private List<String> interviewList;

    public Solution5() {
        fillMapCountersHolders();
    }

    @Override
    public void setInput(List<String> input) {
        this.interviewList = input;
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
        String[] interviews = interviewList.toArray(new String[interviewList.size()]);
        Object[] majorProduct = searchForMajor(interviews);
        printAlgorithmResult((Boolean) majorProduct[0], (String) majorProduct[1]);

        counterHolder.putCounterMapInstructionAndResetCounter(interviewList.size());
    }

    @Override
    public void divideAndConquerAlgorithm() {
        CounterHolder counterHolder = mapCountersHolders.get(Algorithms.DIVIDE_AND_CONQUER_ALGORITHM.algorithmName);
        counter = counterHolder.getInstructionCounter();
        String[] interviews = interviewList.toArray(new String[interviewList.size()]);
        Object[] majorProduct = searchForMajorRecursive(interviews);

        printAlgorithmResult((Boolean) majorProduct[0], (String) majorProduct[1]);

        counterHolder.putCounterMapInstructionAndResetCounter(interviewList.size());
    }

    private void printAlgorithmResult(boolean isMajor, String product) {
        if(isMajor == true) {
            System.out.println("The product (" + product + ") is present in more of 50% of interviewed people");
        } else {
            System.out.println("The product (" + product + ") is major but it was cite by less 50% of interviewed people");
        }
    }

    private Object[] searchForMajor(String[] interviewList) {
        int interviewListHalfSize = interviewList.length / 2; counter.increment();

        String majorProduct = interviewList[0]; counter.increment();
        int count = 1; counter.increment();
        counter.increment();
        for(int i=1; i < interviewList.length; i++) {
            counter.increment();
            if(count == 0 && counter.increment()) {
                majorProduct = interviewList[i]; counter.increment();
                count = 1; counter.increment();
            } else if(majorProduct.equals(interviewList[i]) && counter.increment()) {
                count++; counter.increment();
            } else {
                count--; counter.increment();
            }
        }

        count = 0; counter.increment();
        for(String product : interviewList) { counter.increment();
            if(product.equals(majorProduct) && counter.increment()) {
                count++; counter.increment();
            }
        }

        if(count > interviewListHalfSize && counter.increment()) {
            counter.increment();
            return new Object[]{Boolean.TRUE, majorProduct};
        } else {
            counter.increment();
            return new Object[]{Boolean.TRUE, majorProduct};
        }
    }

    private Object[] searchForMajorRecursive(String[] interviewList) {

        Object[] major = findMajorRecursive(interviewList, 0, interviewList.length - 1);

        String majorProduct = (String) major[0]; counter.increment();
        int count = (Integer) major[1]; counter.increment();

        if(count > interviewList.length / 2 && counter.increment()) {
            counter.increment();
            return new Object[]{Boolean.TRUE, majorProduct};
        } else {
            counter.increment();
            return new Object[]{Boolean.TRUE, majorProduct};
        }
    }

    private Object[] findMajorRecursive(String[] interviewList, int start, int end) {
        if(start == end && counter.increment()) {
            String product = interviewList[start]; counter.increment();
            return new Object[]{product, 1};
        }

        int middle = (start + end) / 2; counter.increment();
        Object[] left = findMajorRecursive(interviewList, start, middle); counter.increment();
        Object[] right = findMajorRecursive(interviewList, middle, end); counter.increment();

        int countLeft = counter(interviewList, start, end, (String) left[0]); counter.increment();
        int countRight = counter(interviewList, start, end, (String) right[0]); counter.increment();

        if(countLeft > (end - start + 1) / 2 && counter.increment()) {
            return new Object[]{left[0], countLeft};
        } else if(countRight > (end - start + 1) / 2 && counter.increment()) {
            return new Object[]{right[0], countLeft};
        } else {
            return new Object[]{null, 0};
        }
    }

    private int counter(String[] interviewList, int start, int end, String product) {
        int count = 0; counter.increment();
        for (int i = start; i <= end + 1; i++) {
            counter.increment();
            if (interviewList[i] == product && counter.increment()) {
                count++; counter.increment();
            }
        }
        return count;
    }
}
