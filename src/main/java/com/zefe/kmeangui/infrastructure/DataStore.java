package com.zefe.kmeangui.infrastructure;

import com.zefe.kmeangui.domain.model.ClusterResult;
import com.zefe.kmeangui.domain.model.PointSet;

public class DataStore {

    private int[] pointsX;
    private int[] pointsY;
    private int pointCount;

    private int[] centroidsX;
    private int[] centroidsY;
    private int centroidCount;

    private int[] assignment;
    private int[] resultCentroidXs;
    private int[] resultCentroidYs;
    private int resultCentroidCount;
    private int iterations;

    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    public void setPoints(int[] xs, int[] ys, int count) {
        this.pointsX = xs;
        this.pointsY = ys;
        this.pointCount = count;
        this.assignment = null;
        this.resultCentroidXs = null;
        this.resultCentroidYs = null;
        this.resultCentroidCount = 0;
        this.iterations = 0;
    }

    public void setCentroids(int[] xs, int[] ys, int count) {
        this.centroidsX = xs;
        this.centroidsY = ys;
        this.centroidCount = count;
        this.assignment = null;
        this.resultCentroidXs = null;
        this.resultCentroidYs = null;
        this.resultCentroidCount = 0;
        this.iterations = 0;
    }

    public boolean addCentroid(int x, int y) {
        int newCount = this.centroidCount + 1;
        int[] newXs = new int[newCount];
        int[] newYs = new int[newCount];
        if (this.centroidsX != null && this.centroidCount > 0) {
            System.arraycopy(this.centroidsX, 0, newXs, 0, this.centroidCount);
            System.arraycopy(this.centroidsY, 0, newYs, 0, this.centroidCount);
        }
        newXs[this.centroidCount] = x;
        newYs[this.centroidCount] = y;
        this.centroidsX = newXs;
        this.centroidsY = newYs;
        this.centroidCount = newCount;
        this.assignment = null;
        this.resultCentroidXs = null;
        this.resultCentroidYs = null;
        this.resultCentroidCount = 0;
        this.iterations = 0;
        return true;
    }

    public void clearResult() {
        this.assignment = null;
        this.resultCentroidXs = null;
        this.resultCentroidYs = null;
        this.resultCentroidCount = 0;
        this.iterations = 0;
    }

    public void clearAll() {
        this.pointsX = null;
        this.pointsY = null;
        this.pointCount = 0;
        this.centroidsX = null;
        this.centroidsY = null;
        this.centroidCount = 0;
        this.assignment = null;
        this.resultCentroidXs = null;
        this.resultCentroidYs = null;
        this.resultCentroidCount = 0;
        this.iterations = 0;
    }

    public void setRange(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public PointSet getPointsView() {
        if (this.pointCount == 0) {
            return null;
        }
        return new PointSet(this.pointsX, this.pointsY, this.pointCount);
    }

    public PointSet getCentroidsView() {
        if (this.centroidCount == 0) {
            return null;
        }
        return new PointSet(this.centroidsX, this.centroidsY, this.centroidCount);
    }

    public void setDisplayState(int[] assignment, int[] centroidXs, int[] centroidYs, int centroidCount) {
        this.assignment = assignment;
        this.resultCentroidXs = centroidXs;
        this.resultCentroidYs = centroidYs;
        this.resultCentroidCount = centroidCount;
    }

    public void saveResult(ClusterResult result) {
        this.setDisplayState(result.getAssignment(), result.getCentroidXs(), result.getCentroidYs(), result.getCentroidCount());
        this.iterations = result.getIterations();
    }

    public int[] getPointsX() { return this.pointsX; }
    public int[] getPointsY() { return this.pointsY; }
    public int getPointCount() { return this.pointCount; }

    public int[] getCentroidsX() { return this.centroidsX; }
    public int[] getCentroidsY() { return this.centroidsY; }
    public int getCentroidCount() { return this.centroidCount; }

    public int[] getAssignment() { return this.assignment; }
    public int[] getResultCentroidXs() { return this.resultCentroidXs; }
    public int[] getResultCentroidYs() { return this.resultCentroidYs; }
    public int getResultCentroidCount() { return this.resultCentroidCount; }
    public int getIterations() { return this.iterations; }

    public int getMinX() { return this.minX; }
    public int getMaxX() { return this.maxX; }
    public int getMinY() { return this.minY; }
    public int getMaxY() { return this.maxY; }
}
