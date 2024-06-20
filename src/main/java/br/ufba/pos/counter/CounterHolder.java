package br.ufba.pos.counter;

import java.util.Map;

public class CounterHolder<K> {

	/**
	 * Label describe witch algorithm it is.
	 */
	private final String labelAlgorithm;
	/**
	 * Map of instruction counter.
	 */
	private Map<K, Integer> counterMapInstruction;
	/**
	 * Instruction Counter
	 */
	private InstructionCounter instructionCounter = new InstructionCounter();

	/**
	 * Default constructor
	 *
	 * @param counterMap
	 */
	public CounterHolder(String labelAlgorithm, Map<K, Integer> counterMap) {
		this.labelAlgorithm = labelAlgorithm;
		this.counterMapInstruction = counterMap;
	}

	/**
	 * Put in the map the values referenced by the measurement value of the instruction counter
	 * and reset the counter.
	 *
	 * @param key as type of measurement value of the instruction counter.
	 */
	public void putCounterMapInstructionAndResetCounter(K key) {
		counterMapInstruction.put(key, instructionCounter.getCounter());
		instructionCounter.resetCounter();
	}

	public Map getCounterMapInstruction() {
		return counterMapInstruction;
	}

	public InstructionCounter getInstructionCounter() {
		return instructionCounter;
	}
}
