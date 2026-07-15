package com.zefe.kmeangui.domain.service;

import com.zefe.kmeangui.domain.model.ClusterResult;
import com.zefe.kmeangui.domain.model.PointSet;
import com.zefe.kmeangui.domain.port.IKmeansResourcesPort;
import com.zefe.kmeangui.domain.port.IKmeansUpdatePort;

public class KMeansAlgoritmService {

    private static final int MAX_ITERATIONS = 100;

    private final IKmeansResourcesPort iKmeansResourcesPort;
    private final IKmeansUpdatePort iKmeansUpdatePort;

    public KMeansAlgoritmService(IKmeansResourcesPort iKmeansResourcesPort, IKmeansUpdatePort iKmeansUpdatePort) {
        this.iKmeansResourcesPort = iKmeansResourcesPort;
        this.iKmeansUpdatePort = iKmeansUpdatePort;
    }

    public void execute() {
        PointSet points = this.iKmeansResourcesPort.getPoints();
        PointSet centroids = this.iKmeansResourcesPort.getCentroids();

        if (points == null || centroids == null
                || points.getCount() == 0 || centroids.getCount() == 0) {
            return;
        }

        int n = points.getCount();
        int k = centroids.getCount();
        int[] px = points.getXs();
        int[] py = points.getYs();

        int[] centroidXs = new int[k];
        int[] centroidYs = new int[k];
        int[] newCentroidXs = new int[k];
        int[] newCentroidYs = new int[k];
        int[] assignment = new int[n];
        long[] sumX = new long[k];
        long[] sumY = new long[k];
        int[] count = new int[k];

        System.arraycopy(centroids.getXs(), 0, centroidXs, 0, k);
        System.arraycopy(centroids.getYs(), 0, centroidYs, 0, k);

        int iterations = 0;
        boolean changed = true;

        while (changed && iterations < MAX_ITERATIONS) {
            iterations++;

            for (int i = 0; i < k; i++) {
                sumX[i] = 0L;
                sumY[i] = 0L;
                count[i] = 0;
            }

            changed = false;
            for (int i = 0; i < n; i++) {
                int x = px[i];
                int y = py[i];
                int best = 0;
                long bestDist = distanceSquared(x, y, centroidXs[0], centroidYs[0]);
                for (int c = 1; c < k; c++) {
                    long d = distanceSquared(x, y, centroidXs[c], centroidYs[c]);
                    if (d < bestDist) {
                        bestDist = d;
                        best = c;
                    }
                }
                if (assignment[i] != best) {
                    assignment[i] = best;
                    changed = true;
                }
                sumX[best] += x;
                sumY[best] += y;
                count[best]++;
            }

            for (int c = 0; c < k; c++) {
                if (count[c] > 0) {
                    newCentroidXs[c] = (int) (sumX[c] / count[c]);
                    newCentroidYs[c] = (int) (sumY[c] / count[c]);
                } else {
                    newCentroidXs[c] = centroidXs[c];
                    newCentroidYs[c] = centroidYs[c];
                }
            }

            boolean same = true;
            for (int c = 0; c < k && same; c++) {
                if (newCentroidXs[c] != centroidXs[c] || newCentroidYs[c] != centroidYs[c]) {
                    same = false;
                }
            }
            if (same) {
                changed = false;
            } else {
                System.arraycopy(newCentroidXs, 0, centroidXs, 0, k);
                System.arraycopy(newCentroidYs, 0, centroidYs, 0, k);
            }
        }

        ClusterResult result = new ClusterResult(assignment, centroidXs, centroidYs, k, iterations);
        this.iKmeansUpdatePort.saveResult(result);
    }

    private long distanceSquared(int ax, int ay, int bx, int by) {
        long dx = (long) ax - bx;
        long dy = (long) ay - by;
        return dx * dx + dy * dy;
    }
}
