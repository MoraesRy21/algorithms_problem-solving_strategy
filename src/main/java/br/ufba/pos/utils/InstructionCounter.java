package br.ufba.pos.utils;

public class InstructionCounter {

    private int counter = 0;

    public InstructionCounter() {
    }

    public boolean countInstructions() {
        counter++;
        return true;
    }

    public boolean countInstructions(int numberInstructions) {
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
