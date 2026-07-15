package com.zefe.kmeangui.domain.model;

public class ClusterResult {

    private final int[] assignment;
    private final int[] centroidXs;
    private final int[] centroidYs;
    private final int centroidCount;
    private final int iterations;

    public ClusterResult(int[] assignment, int[] centroidXs, int[] centroidYs, int centroidCount, int iterations) {
        this.assignment = assignment;
        this.centroidXs = centroidXs;
        this.centroidYs = centroidYs;
        this.centroidCount = centroidCount;
        this.iterations = iterations;
    }

    public int[] getAssignment() {
        return this.assignment;
    }

    public int[] getCentroidXs() {
        return this.centroidXs;
    }

    public int[] getCentroidYs() {
        return this.centroidYs;
    }

    public int getCentroidCount() {
        return this.centroidCount;
    }

    public int getIterations() {
        return this.iterations;
    }
}
