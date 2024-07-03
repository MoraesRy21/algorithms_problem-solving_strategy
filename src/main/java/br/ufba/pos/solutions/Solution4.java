package br.ufba.pos.solutions;

import br.ufba.pos.input.structure.TwoListNumber;
import br.ufba.pos.solutions.strategies.DivideAndConquerAlgorithm;
import br.ufba.pos.solutions.strategies.GenericAlgorithm;

/**
 * <h2>União Ordenada de Vetores</h2>
 *
 * Sejam 𝑋 e 𝑌 dois vetores ordenados de tamanho 𝑛 e 𝑚, respectivamente.
 * Desenvolva um algoritmo para encontrar o k-ésimo elemento de 𝑋 e 𝑌.
 * O algoritmo deve executar em 𝑂(log 𝑚 + log 𝑛) unidades de tempo
 */
public class Solution4 extends Solution<TwoListNumber> implements GenericAlgorithm, DivideAndConquerAlgorithm {

    private TwoListNumber twoListNumber;

    public Solution4() {
        fillMapCountersHolders();
    }

    @Override
    public void setInput(TwoListNumber input) {
        twoListNumber = input;
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

    }

    @Override
    public void divideAndConquerAlgorithm() {

    }

    private void searchElement(int[] x, int[] y, int k) {
        
    }
}
