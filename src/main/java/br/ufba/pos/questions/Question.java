package br.ufba.pos.questions;


import br.ufba.pos.utils.Charts;
import br.ufba.pos.utils.InstructionCounter;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class Question<T> {

    InstructionCounter counter = new InstructionCounter();

    Map gCounterMapInstruction;
    Map dcCounterMapInstruction;

    public void printMapAsGraphPoint(Map<?, ?> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> "(" + key + ", " + map.get(key) + ")")
                .collect(Collectors.joining(" ", "[", "]"));
        System.out.println(mapAsString);
    }

    public void result() {
        System.out.println("\n——————————————————————————————————————————————————————");
        System.out.println("Generic Algorithm Map instruction:");
        printMapAsGraphPoint(gCounterMapInstruction);
        System.out.println("Divide and Conquer Algorithm Map instruction:");
        printMapAsGraphPoint(dcCounterMapInstruction);

        Charts.plotChart(gCounterMapInstruction, dcCounterMapInstruction);
    }

    public void execute() {
        System.out.println(">> GENERIC ALGORITHM");
        genericAlgorithm();

        System.out.println(">> DIVIDE AND CONQUER ALGORITHM");
        divideAndConquerAlgorithm();
    }

    public abstract String getQuestionTitle();

    public abstract T getInput();

    public abstract void setInput(T input);

    protected abstract void genericAlgorithm();

    protected abstract void divideAndConquerAlgorithm();

    protected abstract void dynamicProgramingAlgorithm();

}
