package br.ufba.pos.questions;

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
public class Question5 extends Question<List<String>> {

    private List<String> interviewList;

    public Question5() {
        super.gCounterMapInstruction = new LinkedHashMap<Integer, Integer>();
        super.dcCounterMapInstruction = new LinkedHashMap<Integer, Integer>();
    }

    @Override
    public String getQuestionTitle() {
        return "Majoritária em Pesquisa de Opinião";
    }

    @Override
    public List<String> getInput() {
        return interviewList;
    }

    @Override
    public void setInput(List<String> input) {
        this.interviewList = input;
    }

    @Override
    protected void genericAlgorithm() {
        Object[] majorProduct = searchForMajor(interviewList);

        printAlgorithmResult((Boolean) majorProduct[0], (String) majorProduct[1]);

        gCounterMapInstruction.put(interviewList.size(), counter.getCounter());
        counter.resetCounter();
    }

    @Override
    protected void divideAndConquerAlgorithm() {
        Object[] majorProduct = searchForMajorRecursive(interviewList);

        printAlgorithmResult((Boolean) majorProduct[0], (String) majorProduct[1]);

        dcCounterMapInstruction.put(interviewList.size(), counter.getCounter());
        counter.resetCounter();
    }

    @Override
    protected void dynamicProgramingAlgorithm() {

    }

    private void printAlgorithmResult(boolean isMajor, String product) {
        if(isMajor == true) {
            System.out.println("The product (" + product + ") is present in more of 50% of interviewed people");
        } else {
            System.out.println("The product (" + product + ") is major but it was cite by less 50% of interviewed people");
        }
    }

    private Object[] searchForMajor(List<String> interviewList) {
        int interviewListHalfSize = interviewList.size() / 2; counter.countInstructions();

        String majorProduct = interviewList.get(0); counter.countInstructions();
        int count = 1; counter.countInstructions();
        for(int i=1; i < interviewList.size(); i++) { counter.countInstructions();
            if(count == 0) { counter.countInstructions();
                majorProduct = interviewList.get(i); counter.countInstructions();
                count = 1; counter.countInstructions();
            } else if(majorProduct.equals(interviewList.get(i))) { counter.countInstructions();
                count++; counter.countInstructions();
            } else { counter.countInstructions();
                count--; counter.countInstructions();
            }
        }

        count = 0; counter.countInstructions();
        for(String product : interviewList) { counter.countInstructions();
            if(product.equals(majorProduct)) { counter.countInstructions();
                count++; counter.countInstructions();
            }
        }

        if(count > interviewListHalfSize) { counter.countInstructions();
            return new Object[]{Boolean.TRUE, majorProduct};
        } else { counter.countInstructions();
            return new Object[]{Boolean.TRUE, majorProduct};
        }
    }

    private Object[] searchForMajorRecursive(List<String> interviewList) {
        int interviewListHalfSize = interviewList.size() / 2; counter.countInstructions();

        Object[] major = findMajor(interviewList);

        String majorProduct = (String) major[0]; counter.countInstructions();
        int count = (Integer) major[1]; counter.countInstructions();

        if(count >= interviewListHalfSize) { counter.countInstructions();
            return new Object[]{Boolean.TRUE, majorProduct};
        } else { counter.countInstructions();
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
        int listSize = interviewList.size(); counter.countInstructions();
        if(listSize == 1) { counter.countInstructions();
            String product = interviewList.get(0); counter.countInstructions();
            return new Object[]{product, 1};
        }

        int middle = listSize / 2; counter.countInstructions();
        Object[] left = findMajor(interviewList.subList(0, middle)); counter.countInstructions();
        Object[] right = findMajor(interviewList.subList(middle, listSize)); counter.countInstructions();

        int countLeft = counter(interviewList, (String) left[0]); counter.countInstructions();
        int countRight = counter(interviewList, (String) right[0]); counter.countInstructions();

        if(listSize > 2) {

        }
        if(countLeft > countRight) { counter.countInstructions();
            return new Object[]{left[0], countLeft};
        } else { counter.countInstructions();
            return new Object[]{right[0], countLeft};
        }
    }

    private int counter(List<String> interviewList, String product) {
        int count = 0; counter.countInstructions();
        for (int i=0; i < interviewList.size(); i++) { counter.countInstructions();
            if (interviewList.get(i) == product) { counter.countInstructions();
                count++; counter.countInstructions();
            }
        }
        return count;
    }
}
