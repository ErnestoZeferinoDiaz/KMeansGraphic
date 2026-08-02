package com.zefe.kmeangui.domain.service;

import com.zefe.kmeangui.domain.model.ClusterResult;
import com.zefe.kmeangui.domain.model.PointSet;
import com.zefe.kmeangui.domain.port.IKmeansIterationPort;
import com.zefe.kmeangui.domain.port.IKmeansResourcesPort;
import com.zefe.kmeangui.domain.port.IKmeansUpdatePort;

public class KMeansAlgoritmService {

    private static final int MAX_ITERATIONS = 100;

    private final IKmeansResourcesPort resourcesPort;
    private final IKmeansUpdatePort updatePort;
    private final IKmeansIterationPort iterationPort;

    public KMeansAlgoritmService(IKmeansResourcesPort resourcesPort, IKmeansUpdatePort updatePort) {
        this(resourcesPort, updatePort, null);
    }

    public KMeansAlgoritmService(IKmeansResourcesPort resourcesPort, IKmeansUpdatePort updatePort, IKmeansIterationPort iterationPort) {
        this.resourcesPort = resourcesPort;
        this.updatePort = updatePort;
        this.iterationPort = iterationPort;
    }

    public void execute() {
        PointSet points = this.resourcesPort.getPoints();
        PointSet centroids = this.resourcesPort.getCentroids();

        if (!this.hasValidInput(points, centroids)) {
            return;
        }

        int n = points.getCount();
        int k = centroids.getCount();
        int[] px = points.getXs();
        int[] py = points.getYs();

        int[] centroidXs = this.initCentroidXs(centroids, k);
        int[] centroidYs = this.initCentroidYs(centroids, k);
        int[] newCentroidXs = new int[k];
        int[] newCentroidYs = new int[k];
        int[] assignment = new int[n];
        long[] sumX = new long[k];
        long[] sumY = new long[k];
        int[] count = new int[k];

        int iterations = 0;
        boolean changed = true;

        while (this.shouldContinue(changed, iterations)) {
            iterations++;
            this.resetAccumulators(sumX, sumY, count);

            changed = this.assignPoints(px, py, centroidXs, centroidYs, assignment, sumX, sumY, count, n, k);
            this.recomputeCentroids(sumX, sumY, count, centroidXs, centroidYs, newCentroidXs, newCentroidYs, k);

            if (this.hasConverged(centroidXs, centroidYs, newCentroidXs, newCentroidYs, k)) {
                changed = false;
            } else {
                this.commitCentroids(centroidXs, centroidYs, newCentroidXs, newCentroidYs, k);
            }

            this.notifyIteration(assignment, centroidXs, centroidYs, k, iterations);
        }

        ClusterResult result = new ClusterResult(assignment, centroidXs, centroidYs, k, iterations);
        this.updatePort.saveResult(result);
        this.notifyCompleted(iterations);
    }

    private boolean shouldContinue(boolean changed, int iterations) {
        return changed && iterations < MAX_ITERATIONS;
    }

    private boolean hasValidInput(PointSet points, PointSet centroids) {
        return points != null && centroids != null
                && points.getCount() > 0 && centroids.getCount() > 0;
    }

    private int[] initCentroidXs(PointSet centroids, int k) {
        int[] src = centroids.getXs();
        int[] xs = new int[k];
        for (int i = 0; i < k; i++) {
            xs[i] = src[i];
        }
        return xs;
    }

    private int[] initCentroidYs(PointSet centroids, int k) {
        int[] src = centroids.getYs();
        int[] ys = new int[k];
        for (int i = 0; i < k; i++) {
            ys[i] = src[i];
        }
        return ys;
    }

    private void resetAccumulators(long[] sumX, long[] sumY, int[] count) {
        for (int i = 0; i < count.length; i++) {
            sumX[i] = 0L;
            sumY[i] = 0L;
            count[i] = 0;
        }
    }

    private boolean assignPoints(int[] px, int[] py, int[] centroidXs, int[] centroidYs,
                                 int[] assignment, long[] sumX, long[] sumY, int[] count,
                                 int n, int k) {
        boolean changed = false;
        for (int i = 0; i < n; i++) {
            int x = px[i];
            int y = py[i];
            int best = this.nearestCentroid(x, y, centroidXs, centroidYs, k);
            if (assignment[i] != best) {
                assignment[i] = best;
                changed = true;
            }
            this.accumulate(sumX, sumY, count, best, x, y);
        }
        return changed;
    }

    private int nearestCentroid(int x, int y, int[] centroidXs, int[] centroidYs, int k) {
        int best = 0;
        long bestDist = this.distanceSquared(x, y, centroidXs[0], centroidYs[0]);
        for (int c = 1; c < k; c++) {
            long d = this.distanceSquared(x, y, centroidXs[c], centroidYs[c]);
            if (d < bestDist) {
                bestDist = d;
                best = c;
            }
        }
        return best;
    }

    private long distanceSquared(int ax, int ay, int bx, int by) {
        long dx = (long) ax - bx;
        long dy = (long) ay - by;
        return dx * dx + dy * dy;
    }

    private void accumulate(long[] sumX, long[] sumY, int[] count, int cluster, int x, int y) {
        sumX[cluster] += x;
        sumY[cluster] += y;
        count[cluster]++;
    }

    private void recomputeCentroids(long[] sumX, long[] sumY, int[] count,
                                    int[] centroidXs, int[] centroidYs,
                                    int[] newCentroidXs, int[] newCentroidYs, int k) {
        for (int c = 0; c < k; c++) {
            if (count[c] > 0) {
                newCentroidXs[c] = (int) (sumX[c] / count[c]);
                newCentroidYs[c] = (int) (sumY[c] / count[c]);
            } else {
                newCentroidXs[c] = centroidXs[c];
                newCentroidYs[c] = centroidYs[c];
            }
        }
    }

    private boolean hasConverged(int[] oldX, int[] oldY, int[] newX, int[] newY, int k) {
        for (int c = 0; c < k; c++) {
            if (newX[c] != oldX[c] || newY[c] != oldY[c]) {
                return false;
            }
        }
        return true;
    }

    private void commitCentroids(int[] centroidXs, int[] centroidYs,
                                int[] newCentroidXs, int[] newCentroidYs, int k) {
        for (int i = 0; i < k; i++) {
            centroidXs[i] = newCentroidXs[i];
            centroidYs[i] = newCentroidYs[i];
        }
    }

    private void notifyIteration(int[] assignment, int[] centroidXs, int[] centroidYs, int k, int iteration) {
        if (this.iterationPort != null) {
            this.iterationPort.onIteration(assignment, centroidXs, centroidYs, k, iteration);
        }
    }

    private void notifyCompleted(int iterations) {
        if (this.iterationPort != null) {
            this.iterationPort.onCompleted(iterations);
        }
    }
}
