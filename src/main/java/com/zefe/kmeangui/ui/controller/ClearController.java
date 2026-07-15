package com.zefe.kmeangui.ui.controller;

import com.zefe.kmeangui.infrastructure.DataStore;
import com.zefe.kmeangui.ui.PlanePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearController implements ActionListener {

    private final DataStore store;
    private final PlanePanel planePanel;

    public ClearController(DataStore store, PlanePanel planePanel) {
        this.store = store;
        this.planePanel = planePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.store.clearAll();
        this.planePanel.rebuildPalette(0);
        this.planePanel.markDirty();
        this.planePanel.repaint();
    }
}
