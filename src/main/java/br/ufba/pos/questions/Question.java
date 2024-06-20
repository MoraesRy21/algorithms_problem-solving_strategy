package br.ufba.pos.questions;

import br.ufba.pos.charts.Charts;
import br.ufba.pos.counter.CounterHolder;
import br.ufba.pos.solutions.BacktrackingAlgorithm;
import br.ufba.pos.solutions.DivideAndConquerAlgorithm;
import br.ufba.pos.solutions.DynamicProgramingAlgorithm;
import br.ufba.pos.solutions.GreedyAlgorithm;
import br.ufba.pos.utils.Algorithms;
import br.ufba.pos.counter.InstructionCounter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class that's hold standard behaviour of the algorithms
 *
 * @param <T> type of input of the question
 */
public abstract class Question<T> {

    InstructionCounter counter;

    Map<String, CounterHolder> mapCountersHolders = new LinkedHashMap<>();

    /**
     * Fill the map counter holders by the implemented interfaces algorithms strategies.
     */
    protected void fillMapCountersHolders() {
        String gcName = Algorithms.GENERIC_ALGORITHM.algorithmName;
        mapCountersHolders.put(gcName, new CounterHolder<Integer>(gcName, new LinkedHashMap<Integer, Integer>()));

        List<Class<?>> interfaces = Arrays.asList(this.getClass().getInterfaces());
        if(interfaces.contains(DivideAndConquerAlgorithm.class)) {
            String dcName = Algorithms.DIVIDE_AND_CONQUER_ALGORITHM.algorithmName;
            mapCountersHolders.put(dcName, new CounterHolder<Integer>(dcName, new LinkedHashMap<Integer, Integer>()));
        }
        if(interfaces.contains(DynamicProgramingAlgorithm.class)) {
            String dpName = Algorithms.DYNAMIC_PROGRAMING_ALGORITHM.algorithmName;
            mapCountersHolders.put(dpName, new CounterHolder<Integer>(dpName, new LinkedHashMap<Integer, Integer>()));
        }
        if(interfaces.contains(GreedyAlgorithm.class)) {
            String gName = Algorithms.GREEDY_ALGORITHM.algorithmName;
            mapCountersHolders.put(gName, new CounterHolder<Integer>(gName, new LinkedHashMap<Integer, Integer>()));
        }
        if(interfaces.contains(BacktrackingAlgorithm.class)) {
            String btName = Algorithms.BACKTRACKING_ALGORITHM.algorithmName;
            mapCountersHolders.put(btName, new CounterHolder<Integer>(btName, new LinkedHashMap<Integer, Integer>()));
        }
    }

    /**
     * Gives the result of the algorithms counters and plot the charts
     */
    public void result() {
        System.out.println("\n——————————————————————————————————————————————————————");

        for(Map.Entry<String, CounterHolder> entry : mapCountersHolders.entrySet()) {
            System.out.println(entry.getKey()+" Map instruction:");
            printMapAsGraphPoint(entry.getValue().getCounterMapInstruction());
        }

        Charts.plotChart(mapCountersHolders);
    }

    protected void printMapAsGraphPoint(Map<?, ?> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> "(" + key + ", " + map.get(key) + ")")
                .collect(Collectors.joining(" ", "[", "]"));
        System.out.println(mapAsString);
    }

    public void execute() {
        System.out.println(">>>> GENERIC ALGORITHM");
        genericAlgorithm();
    }

    /**
     * Give the title of the question.
     *
     * @return question title
     */
    public abstract String getQuestionTitle();

    /**
     * Set the input
     *
     * @param input used by the executed algorithm in question.
     */
    public abstract void setInput(T input);

    /**
     * Generic algorithm that's solve the question
     */
    protected abstract void genericAlgorithm();

    /**
     *
     */
    protected abstract class Solution {

    }
}
