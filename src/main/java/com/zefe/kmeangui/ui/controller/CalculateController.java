package com.zefe.kmeangui.ui.controller;

import com.zefe.kmeangui.domain.service.KMeansAlgoritmService;
import com.zefe.kmeangui.infrastructure.DataStore;
import com.zefe.kmeangui.ui.DataFormInput;
import com.zefe.kmeangui.ui.PlanePanel;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateController implements ActionListener {

    private final DataFormInput form;
    private final DataStore store;
    private final KMeansAlgoritmService service;
    private final PlanePanel planePanel;

    public CalculateController(DataFormInput form, DataStore store, KMeansAlgoritmService service, PlanePanel planePanel) {
        this.form = form;
        this.store = store;
        this.service = service;
        this.planePanel = planePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.store.getPointCount() == 0) {
            JOptionPane.showMessageDialog(this.form, "Genere puntos primero.", "Sin datos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (this.store.getCentroidCount() == 0) {
            JOptionPane.showMessageDialog(this.form, "Agregue al menos un centroide.", "Sin centroides", JOptionPane.WARNING_MESSAGE);
            return;
        }

        this.setButtonsEnabled(false);
        this.planePanel.rebuildPalette(this.store.getCentroidCount());

        Thread worker = new Thread(new KMeansRunnable(), "KMeans-Worker");
        worker.start();
    }

    private void setButtonsEnabled(boolean enabled) {
        this.form.btnAddData.setEnabled(enabled);
        this.form.btnCalculate.setEnabled(enabled);
        this.form.btnClear.setEnabled(enabled);
    }

    private class KMeansRunnable implements Runnable {

        @Override
        public void run() {
            long t0 = System.currentTimeMillis();
            try {
                CalculateController.this.service.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                long tTotal = System.currentTimeMillis() - t0;
                int iters = CalculateController.this.store.getIterations();
                CalculateController.this.planePanel.markDirty();
                CalculateController.this.planePanel.repaint();
                CalculateController.this.setButtonsEnabled(true);
                System.out.println("KMeans finalizado | tiempoTotal=" + tTotal + "ms | iteraciones=" + iters);
            }
        }
    }
}
