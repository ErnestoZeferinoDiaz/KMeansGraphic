package com.zefe.kmeangui.ui.controller;

import com.zefe.kmeangui.infrastructure.DataStore;
import com.zefe.kmeangui.infrastructure.RandomPointGenerator;
import com.zefe.kmeangui.ui.DataFormInput;
import com.zefe.kmeangui.ui.PlanePanel;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDataController implements ActionListener {

    private final DataFormInput form;
    private final DataStore store;
    private final RandomPointGenerator generator;
    private final PlanePanel planePanel;

    public AddDataController(DataFormInput form, DataStore store, RandomPointGenerator generator, PlanePanel planePanel) {
        this.form = form;
        this.store = store;
        this.generator = generator;
        this.planePanel = planePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int nPoints = this.parseInt(this.form.txtNumberPoints.getText(), -1);
        int nCentroids = this.parseInt(this.form.txtNumberCentroids.getText(), -1);

        if (nPoints <= 0 || nCentroids <= 0) {
            JOptionPane.showMessageDialog(this.form, "Ingrese enteros mayores a 0.", "Datos invalidos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int minX = this.store.getMinX();
        int maxX = this.store.getMaxX();
        int minY = this.store.getMinY();
        int maxY = this.store.getMaxY();
        if (maxX <= minX || maxY <= minY) {
            maxX = minX + 100;
            maxY = minY + 100;
        }

        this.generator.generatePoints(this.store, nPoints, minX, maxX, minY, maxY);
        this.generator.generateCentroids(this.store, nCentroids, minX, maxX, minY, maxY);

        this.planePanel.rebuildPalette(nCentroids);
        this.planePanel.markDirty();
        this.planePanel.repaint();
    }

    private int parseInt(String text, int fallback) {
        try {
            return Integer.parseInt(text.trim());
        } catch (Exception ex) {
            return fallback;
        }
    }
}
