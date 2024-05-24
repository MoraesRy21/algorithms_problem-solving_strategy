package br.ufba.pos.utils;

public enum Algorithm {

    GENERIC_ALGORITHM("Generic"),
    DIVIDE_AND_CONQUER_ALGORITHM("Divide and Conquer"),
    DYNAMIC_PROGRAMING_ALGORITHM("Dynamic Programing");

    private final String name;

    Algorithm(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
