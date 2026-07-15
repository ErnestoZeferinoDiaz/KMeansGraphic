package com.zefe.kmeangui.ui.controller;

import com.zefe.kmeangui.infrastructure.DataStore;
import com.zefe.kmeangui.ui.PlanePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaneClickController extends MouseAdapter {

    private final DataStore store;
    private final PlanePanel planePanel;

    public PlaneClickController(DataStore store, PlanePanel planePanel) {
        this.store = store;
        this.planePanel = planePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        this.store.addCentroid(x, y);
        int k = this.store.getCentroidCount();
        this.planePanel.rebuildPalette(k);
        this.planePanel.markDirty();
        this.planePanel.repaint();
    }
}
