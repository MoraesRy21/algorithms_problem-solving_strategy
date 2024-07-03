package br.ufba.pos.solutions;

import br.ufba.pos.counter.CounterHolder;
import br.ufba.pos.counter.InstructionCounter;
import br.ufba.pos.solutions.strategies.*;
import br.ufba.pos.utils.Algorithms;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that's hold standard behaviour of the algorithms
 *
 * @param <T> type of input of the question
 */
public abstract class Solution<T> {

    InstructionCounter counter;

    protected Map<String, CounterHolder> mapCountersHolders = new LinkedHashMap<>();

    /**
     * Set the input
     *
     * @param input used by the executed algorithm in question.
     */
    public abstract void setInput(T input);

    /**
     * Execute algorithms.
     */
    public abstract void executeSolutions();

    /**
     * Fill the map counter holders by the implemented interfaces algorithms strategies.
     */
    protected void fillMapCountersHolders() {
        List<Class<?>> interfaces = Arrays.asList(this.getClass().getInterfaces());
        if(interfaces.contains(GenericAlgorithm.class)) {
            String gcName = Algorithms.GENERIC_ALGORITHM.algorithmName;
            mapCountersHolders.put(gcName, new CounterHolder<Integer>(gcName, new LinkedHashMap<Integer, Integer>()));
        }
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

    public Map<String, CounterHolder> getMapCountersHolders() {
        return mapCountersHolders;
    }
}
