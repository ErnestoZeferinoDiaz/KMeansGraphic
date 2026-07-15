package com.zefe.kmeangui.ui.controller;

import com.zefe.kmeangui.domain.service.KMeansAlgoritmService;
import com.zefe.kmeangui.infrastructure.DataStore;
import com.zefe.kmeangui.ui.DataFormInput;
import com.zefe.kmeangui.ui.PlanePanel;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
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

        SwingWorker<long[], Void> worker = new SwingWorker<long[], Void>() {
            @Override
            protected long[] doInBackground() throws Exception {
                long t0 = System.currentTimeMillis();
                CalculateController.this.service.execute();
                long tTotal = System.currentTimeMillis() - t0;
                return new long[]{tTotal};
            }

            @Override
            protected void done() {
                CalculateController.this.setButtonsEnabled(true);
                CalculateController.this.planePanel.markDirty();
                CalculateController.this.planePanel.repaint();
                try {
                    long[] result = this.get();
                    int iters = CalculateController.this.store.getIterations();
                    System.out.println("KMeans finalizado | tiempoTotal=" + result[0] + "ms | iteraciones=" + iters);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CalculateController.this.form,
                            "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void setButtonsEnabled(boolean enabled) {
        this.form.btnAddData.setEnabled(enabled);
        this.form.btnCalculate.setEnabled(enabled);
        this.form.btnClear.setEnabled(enabled);
    }
}
