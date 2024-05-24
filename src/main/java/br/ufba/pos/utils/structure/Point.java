package br.ufba.pos.utils.structure;

public record Point(int x, int y) {
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
