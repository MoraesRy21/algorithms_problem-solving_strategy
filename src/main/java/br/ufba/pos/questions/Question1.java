package br.ufba.pos.questions;

import br.ufba.pos.utils.structure.PairPoints;
import br.ufba.pos.utils.structure.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <h2>Par de Pontos Mais Próximos</h2>
 *
 * Dado um conjunto de n pontos em um plano cartesiano, desenvolva um algoritmo para
 * encontrar o par de pontos mais próximos nesse conjunto. O tempo de execução deve
 * ser de 𝑂(𝑛 log(𝑛)) no pior caso.
 */
public class Question1 extends Question<List<Point>> {

    private List<Point> pointList;

    @Override
    public List<Point> getInput() {
        return this.pointList;
    }

    public Question1() {
        super.gCounterMapInstruction = new LinkedHashMap<Integer, Integer>();
        super.dcCounterMapInstruction = new LinkedHashMap<Integer, Integer>();
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
    protected void genericAlgorithm() {
        PairPoints pairPoints = minimumDistancePoints(pointList);
        System.out.println("Minimum distance:       " + pairPoints.minimumDistance);
        System.out.println("Closet pair of points:  " + pairPoints.printPairPoints());

        gCounterMapInstruction.put(pointList.size(), counter.getCounter());
        counter.resetCounter();
    }

    @Override
    protected void divideAndConquerAlgorithm() {
        PairPoints pairPoints = nearbyPointsRecursive(pointList);
        System.out.println("Minimum distance:       " + pairPoints.minimumDistance);
        System.out.println("Closet pair of points:  " + pairPoints.printPairPoints());

        dcCounterMapInstruction.put(pointList.size(), counter.getCounter());
        counter.resetCounter();
    }

    @Override
    protected void dynamicProgramingAlgorithm() {

    }

    private PairPoints nearbyPointsRecursive(List<Point> pointList) {
        int n = pointList.size(); counter.countInstructions();
        if (pointList.size() <= 3) { counter.countInstructions();
            return minimumDistancePoints(pointList);
        }

        int mid = n / 2; counter.countInstructions();

        List<Point> leftPoints = pointList.subList(0, mid); counter.countInstructions();
        List<Point> rightPoints = pointList.subList(mid, pointList.size()); counter.countInstructions();

        PairPoints leftPairPoints = nearbyPointsRecursive(leftPoints); counter.countInstructions();
        PairPoints rightPairPoints = nearbyPointsRecursive(rightPoints); counter.countInstructions();

        double minorMinimumDistance = Double.min(leftPairPoints.minimumDistance, rightPairPoints.minimumDistance); counter.countInstructions();

        /*
         * Find the pair of points with minor minimum distance.
         */
        PairPoints minPair; counter.countInstructions();
        if (minorMinimumDistance == leftPairPoints.minimumDistance) { counter.countInstructions();
            minPair = leftPairPoints; counter.countInstructions();
        } else {
            minPair = rightPairPoints; counter.countInstructions();
        }

        List<Point> strip = new ArrayList<>(); counter.countInstructions();
        double midX = pointList.get(mid).x(); counter.countInstructions();
        for (Point point : pointList) { counter.countInstructions();
            if (Math.abs(point.x() - midX) < minorMinimumDistance) { counter.countInstructions();
                strip.add(point); counter.countInstructions();
            }
        }
        strip.sort(Comparator.comparingDouble(Point::y)); counter.countInstructions();

        for(int i=0; i<strip.size(); i++) { counter.countInstructions();
            int j = i+1; counter.countInstructions();
            while (j < strip.size() && (strip.get(j).y() - strip.get(i).y()) < minorMinimumDistance) { counter.countInstructions();
                double distance = distance(strip.get(i), strip.get(j)); counter.countInstructions();
                if (distance < minorMinimumDistance) { counter.countInstructions();
                    minorMinimumDistance = distance; counter.countInstructions();
                    minPair = new PairPoints(strip.get(i), strip.get(j), minorMinimumDistance); counter.countInstructions();
                }
                j += 1; counter.countInstructions();
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
        double minimumDistance = Double.MAX_VALUE; counter.countInstructions();
        PairPoints pairPoints = null; counter.countInstructions();
        for (int i = 0; i < pointList.size(); i++) { counter.countInstructions();
            for (int j = i + 1; j < pointList.size(); j++) { counter.countInstructions();
                double distance = distance(pointList.get(i), pointList.get(j)); counter.countInstructions(5);
                if (distance < minimumDistance) { counter.countInstructions();
                    minimumDistance = distance; counter.countInstructions();
                    pairPoints = new PairPoints(pointList.get(i), pointList.get(j), minimumDistance); counter.countInstructions(6);
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
