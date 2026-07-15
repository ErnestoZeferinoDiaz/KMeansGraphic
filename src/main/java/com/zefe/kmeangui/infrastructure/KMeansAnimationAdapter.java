package com.zefe.kmeangui.infrastructure;

import com.zefe.kmeangui.domain.port.IKmeansIterationPort;
import com.zefe.kmeangui.ui.PlanePanel;

import javax.swing.SwingUtilities;

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
        this.paintSync();
        System.out.println("Iteracion " + iteration + " pintada");
        this.sleep();
    }

    @Override
    public void onCompleted(int iterations) {
        this.paintSync();
        System.out.println("KMeans completado en " + iterations + " iteraciones");
    }

    private void paintSync() {
        if (SwingUtilities.isEventDispatchThread()) {
            this.paintNow();
        } else {
            try {
                SwingUtilities.invokeAndWait(this::paintNow);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void paintNow() {
        int w = this.planePanel.getWidth();
        int h = this.planePanel.getHeight();
        if (w > 0 && h > 0) {
            this.planePanel.paintImmediately(0, 0, w, h);
        }
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
