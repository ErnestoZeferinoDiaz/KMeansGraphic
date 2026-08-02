package com.zefe.kmeangui.infrastructure;

import com.zefe.kmeangui.domain.port.IKmeansIterationPort;
import com.zefe.kmeangui.ui.PlanePanel;

public class KMeansAnimationAdapter implements IKmeansIterationPort {

    private final DataStore dataStore;
    private final PlanePanel planePanel;
    private final long delayMs;

    public KMeansAnimationAdapter(DataStore dataStore, PlanePanel planePanel, long delayMs) {
        this.dataStore = dataStore;
        this.planePanel = planePanel;
        this.delayMs = delayMs;
    }

    @Override
    public void onIteration(int[] assignment, int[] centroidXs, int[] centroidYs, int centroidCount, int iteration) {
        this.dataStore.setDisplayState(assignment, centroidXs, centroidYs, centroidCount);
        this.planePanel.rebuildPalette(centroidCount);
        this.planePanel.markDirty();
        this.planePanel.repaint();
        System.out.println("Iteracion " + iteration + " pintada");
        this.sleep();
    }

    @Override
    public void onCompleted(int iterations) {
        this.planePanel.repaint();
        System.out.println("KMeans completado en " + iterations + " iteraciones");
    }

    private void sleep() {
        if (this.delayMs > 0) {
            try {
                Thread.sleep(this.delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
