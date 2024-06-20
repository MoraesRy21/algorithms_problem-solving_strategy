package br.ufba.pos.input.structure;

/**
 * Hold a pair of Point classes
 */
public class PairPoints {

    public Point p1, p2;

    public Double minimumDistance;

    public PairPoints(Point p1, Point p2, Double minimumDistance) {
        this.p1 = p1;
        this.p2 = p2;
        this.minimumDistance = minimumDistance;
    }

    public String printPairPoints() {
        return "[" + p1.toString() + "; " + p2.toString() + "]";
    }
}
