package br.ufba.pos.questions;

import br.ufba.pos.counter.CounterHolder;
import br.ufba.pos.solutions.DivideAndConquerAlgorithm;
import br.ufba.pos.input.structure.PairPoints;
import br.ufba.pos.input.structure.Point;
import br.ufba.pos.utils.Algorithms;

import java.util.*;

/**
 * <h2>Par de Pontos Mais Próximos</h2>
 *
 * Dado um conjunto de n pontos em um plano cartesiano, desenvolva um algoritmo para
 * encontrar o par de pontos mais próximos nesse conjunto. O tempo de execução deve
 * ser de 𝑂(𝑛 log(𝑛)) no pior caso.
 */
public class Question1 extends Question<List<Point>> implements DivideAndConquerAlgorithm {

    private List<Point> pointList;

    public Question1() {
        fillMapCountersHolders();
    }

    @Override
    public void setInput(List<Point> pointList) {
        this.pointList = pointList;
    }

    @Override
    public String getQuestionTitle() {
        return "Par de Pontos Mais Próximos";
    }

    @Override
    public void execute() {
        super.execute();

        System.out.println(">>>> DIVIDE AND CONQUER ALGORITHM");
        divideAndConquerAlgorithm();
    }

    @Override
    protected void genericAlgorithm() {
        CounterHolder counterHolder = mapCountersHolders.get(Algorithms.GENERIC_ALGORITHM.algorithmName);
        counter = counterHolder.getInstructionCounter();

        PairPoints pairPoints = minimumDistancePoints(pointList);
        System.out.println("Minimum distance:       " + pairPoints.minimumDistance);
        System.out.println("Closet pair of points:  " + pairPoints.printPairPoints());

        counterHolder.putCounterMapInstructionAndResetCounter(pointList.size());
    }

    @Override
    public void divideAndConquerAlgorithm() {
        CounterHolder counterHolder = mapCountersHolders.get(Algorithms.DIVIDE_AND_CONQUER_ALGORITHM.algorithmName);
        counter = counterHolder.getInstructionCounter();

        PairPoints pairPoints = nearbyPointsRecursive(pointList);
        System.out.println("Minimum distance:       " + pairPoints.minimumDistance);
        System.out.println("Closet pair of points:  " + pairPoints.printPairPoints());

        counterHolder.putCounterMapInstructionAndResetCounter(pointList.size());
    }

    private PairPoints nearbyPointsRecursive(List<Point> pointList) {
        int n = pointList.size(); counter.increment();
        if (pointList.size() <= 3) { counter.increment();
            return minimumDistancePoints(pointList);
        }

        int mid = n / 2; counter.increment();

        List<Point> leftPoints = pointList.subList(0, mid); counter.increment();
        List<Point> rightPoints = pointList.subList(mid, pointList.size()); counter.increment();

        PairPoints leftPairPoints = nearbyPointsRecursive(leftPoints); counter.increment();
        PairPoints rightPairPoints = nearbyPointsRecursive(rightPoints); counter.increment();

        double minorMinimumDistance = Double.min(leftPairPoints.minimumDistance, rightPairPoints.minimumDistance); counter.increment();

        /*
         * Find the pair of points with minor minimum distance.
         */
        PairPoints minPair; counter.increment();
        if (minorMinimumDistance == leftPairPoints.minimumDistance) { counter.increment();
            minPair = leftPairPoints; counter.increment();
        } else {
            minPair = rightPairPoints; counter.increment();
        }

        List<Point> strip = new ArrayList<>(); counter.increment();
        double midX = pointList.get(mid).x(); counter.increment();
        for (Point point : pointList) { counter.increment();
            if (Math.abs(point.x() - midX) < minorMinimumDistance) { counter.increment();
                strip.add(point); counter.increment();
            }
        }
        strip.sort(Comparator.comparingDouble(Point::y)); counter.increment();

        for(int i=0; i<strip.size(); i++) { counter.increment();
            int j = i+1; counter.increment();
            while (j < strip.size() && (strip.get(j).y() - strip.get(i).y()) < minorMinimumDistance) { counter.increment();
                double distance = distance(strip.get(i), strip.get(j)); counter.increment();
                if (distance < minorMinimumDistance) { counter.increment();
                    minorMinimumDistance = distance; counter.increment();
                    minPair = new PairPoints(strip.get(i), strip.get(j), minorMinimumDistance); counter.increment();
                }
                j += 1; counter.increment();
            }
        }

        return minPair;
    }

    /**
     * Calculate the minimum distance of the closest points in the list of point.
     *
     * @param pointList list of points
     * @return a struct of two pair of point with the distance between then
     */
    private PairPoints minimumDistancePoints(List<Point> pointList) {
        double minimumDistance = Double.MAX_VALUE; counter.increment();
        PairPoints pairPoints = null; counter.increment();
        for (int i = 0; i < pointList.size(); i++) { counter.increment();
            for (int j = i + 1; j < pointList.size(); j++) { counter.increment();
                double distance = distance(pointList.get(i), pointList.get(j)); counter.increment(5);
                if (distance < minimumDistance) { counter.increment();
                    minimumDistance = distance; counter.increment();
                    pairPoints = new PairPoints(pointList.get(i), pointList.get(j), minimumDistance); counter.increment(6);
                }
            }
        }
        return pairPoints;
    }

    private double distance(Point p1, Point p2) {
        double xCatheterSquare = Math.pow(p1.x() - p2.x(), 2);
        double yCatheterSquare = Math.pow(p1.y() - p2.y(), 2);
        return Math.sqrt(xCatheterSquare + yCatheterSquare);
    }
}
