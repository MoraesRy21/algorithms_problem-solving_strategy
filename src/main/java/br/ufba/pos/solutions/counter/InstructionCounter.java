package br.ufba.pos.solutions.counter;

public class InstructionCounter {

    private int counter = 0;

    public InstructionCounter() {
    }

    public boolean increment() {
        counter++;
        return true;
    }

    public boolean increment(int numberInstructions) {
        counter = counter + numberInstructions;
        return true;
    }

    public int getCounter() {
        return counter;
    }

    public void resetCounter() {
        counter = 0;
    }
}
