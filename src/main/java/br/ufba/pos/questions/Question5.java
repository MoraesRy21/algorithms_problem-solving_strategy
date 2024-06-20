package br.ufba.pos.questions;

import br.ufba.pos.counter.CounterHolder;
import br.ufba.pos.solutions.DivideAndConquerAlgorithm;
import br.ufba.pos.utils.Algorithms;

import java.util.LinkedHashMap;
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
public class Question5 extends Question<List<String>> implements DivideAndConquerAlgorithm {

    private List<String> interviewList;

    public Question5() {
        fillMapCountersHolders();
    }

    @Override
    public String getQuestionTitle() {
        return "Majoritária em Pesquisa de Opinião";
    }

    @Override
    public void setInput(List<String> input) {
        this.interviewList = input;
    }

    @Override
    public void result() {
        super.result();

        System.out.println(">>>> DIVIDE AND CONQUER ALGORITHM");
        divideAndConquerAlgorithm();
    }

    @Override
    protected void genericAlgorithm() {
        CounterHolder counterHolder = mapCountersHolders.get(Algorithms.GENERIC_ALGORITHM.algorithmName);
        counter = counterHolder.getInstructionCounter();

        Object[] majorProduct = searchForMajor(interviewList);
        printAlgorithmResult((Boolean) majorProduct[0], (String) majorProduct[1]);

        counterHolder.putCounterMapInstructionAndResetCounter(interviewList.size());
    }

    @Override
    public void divideAndConquerAlgorithm() {
        CounterHolder counterHolder = mapCountersHolders.get(Algorithms.DIVIDE_AND_CONQUER_ALGORITHM.algorithmName);
        counter = counterHolder.getInstructionCounter();

        Object[] majorProduct = searchForMajorRecursive(interviewList);

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

    private Object[] searchForMajor(List<String> interviewList) {
        int interviewListHalfSize = interviewList.size() / 2; counter.increment();

        String majorProduct = interviewList.get(0); counter.increment();
        int count = 1; counter.increment();
        for(int i=1; i < interviewList.size(); i++) { counter.increment();
            if(count == 0) { counter.increment();
                majorProduct = interviewList.get(i); counter.increment();
                count = 1; counter.increment();
            } else if(majorProduct.equals(interviewList.get(i))) { counter.increment();
                count++; counter.increment();
            } else { counter.increment();
                count--; counter.increment();
            }
        }

        count = 0; counter.increment();
        for(String product : interviewList) { counter.increment();
            if(product.equals(majorProduct)) { counter.increment();
                count++; counter.increment();
            }
        }

        if(count > interviewListHalfSize) { counter.increment();
            return new Object[]{Boolean.TRUE, majorProduct};
        } else { counter.increment();
            return new Object[]{Boolean.TRUE, majorProduct};
        }
    }

    private Object[] searchForMajorRecursive(List<String> interviewList) {
        int interviewListHalfSize = interviewList.size() / 2; counter.increment();

        Object[] major = findMajor(interviewList);

        String majorProduct = (String) major[0]; counter.increment();
        int count = (Integer) major[1]; counter.increment();

        if(count >= interviewListHalfSize) { counter.increment();
            return new Object[]{Boolean.TRUE, majorProduct};
        } else { counter.increment();
            return new Object[]{Boolean.TRUE, majorProduct};
        }
    }

    /**
     *
     *
     * @param interviewList
     * @return array of two elements. The fist is the major product as String
     * and the second a counter as int.
     */
    private Object[] findMajor(List<String> interviewList) {
        int listSize = interviewList.size(); counter.increment();
        if(listSize == 1) { counter.increment();
            String product = interviewList.get(0); counter.increment();
            return new Object[]{product, 1};
        }

        int middle = listSize / 2; counter.increment();
        Object[] left = findMajor(interviewList.subList(0, middle)); counter.increment();
        Object[] right = findMajor(interviewList.subList(middle, listSize)); counter.increment();

        int countLeft = counter(interviewList, (String) left[0]); counter.increment();
        int countRight = counter(interviewList, (String) right[0]); counter.increment();

        if(listSize > 2) {

        }
        if(countLeft > countRight) { counter.increment();
            return new Object[]{left[0], countLeft};
        } else { counter.increment();
            return new Object[]{right[0], countLeft};
        }
    }

    private int counter(List<String> interviewList, String product) {
        int count = 0; counter.increment();
        for (int i=0; i < interviewList.size(); i++) { counter.increment();
            if (interviewList.get(i) == product) { counter.increment();
                count++; counter.increment();
            }
        }
        return count;
    }
}
