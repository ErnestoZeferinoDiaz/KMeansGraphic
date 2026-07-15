package com.zefe.kmeangui.domain.model;

public class PointSet {

    private final int[] xs;
    private final int[] ys;
    private final int count;

    public PointSet(int[] xs, int[] ys, int count) {
        this.xs = xs;
        this.ys = ys;
        this.count = count;
    }

    public int[] getXs() {
        return this.xs;
    }

    public int[] getYs() {
        return this.ys;
    }

    public int getCount() {
        return this.count;
    }
}
