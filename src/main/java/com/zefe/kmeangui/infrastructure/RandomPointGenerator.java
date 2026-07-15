package com.zefe.kmeangui.infrastructure;

import java.util.Random;

public class RandomPointGenerator {

    private final Random random;

    public RandomPointGenerator() {
        this.random = new Random();
    }

    public void generatePoints(DataStore store, int count, int minX, int maxX, int minY, int maxY) {
        if (count <= 0) {
            return;
        }
        int[] xs = new int[count];
        int[] ys = new int[count];
        int rangeX = Math.max(1, maxX - minX);
        int rangeY = Math.max(1, maxY - minY);
        for (int i = 0; i < count; i++) {
            xs[i] = minX + this.random.nextInt(rangeX);
            ys[i] = minY + this.random.nextInt(rangeY);
        }
        store.setPoints(xs, ys, count);
    }

    public void generateCentroids(DataStore store, int count, int minX, int maxX, int minY, int maxY) {
        if (count <= 0) {
            return;
        }
        int[] xs = new int[count];
        int[] ys = new int[count];
        int rangeX = Math.max(1, maxX - minX);
        int rangeY = Math.max(1, maxY - minY);
        for (int i = 0; i < count; i++) {
            xs[i] = minX + this.random.nextInt(rangeX);
            ys[i] = minY + this.random.nextInt(rangeY);
        }
        store.setCentroids(xs, ys, count);
    }
}
