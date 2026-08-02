package com.zefe.kmeans;

import com.zefe.kmeangui.domain.service.KMeansAlgoritmService;
import com.zefe.kmeangui.infrastructure.DataStore;
import com.zefe.kmeangui.infrastructure.KMeansAnimationAdapter;
import com.zefe.kmeangui.infrastructure.KMeansResourcesAdapter;
import com.zefe.kmeangui.infrastructure.KMeansUpdateAdapter;
import com.zefe.kmeangui.infrastructure.RandomPointGenerator;
import com.zefe.kmeangui.ui.DataFormInput;
import com.zefe.kmeangui.ui.PlaneFrame;
import com.zefe.kmeangui.ui.PlanePanel;
import com.zefe.kmeangui.ui.controller.AddDataController;
import com.zefe.kmeangui.ui.controller.CalculateController;
import com.zefe.kmeangui.ui.controller.ClearController;
import com.zefe.kmeangui.ui.controller.PlaneClickController;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class App {
    public static void main(String[] args) {
        DataStore store = new DataStore();
        store.setRange(20, 800, 20, 600);

        RandomPointGenerator generator = new RandomPointGenerator();

        PlanePanel planePanel = new PlanePanel(store);
        PlaneFrame planeFrame = new PlaneFrame(planePanel);

        KMeansResourcesAdapter resourcesAdapter = new KMeansResourcesAdapter(store);
        KMeansUpdateAdapter updateAdapter = new KMeansUpdateAdapter(store);
        KMeansAnimationAdapter animationAdapter = new KMeansAnimationAdapter(store, planePanel, 300);
        KMeansAlgoritmService service = new KMeansAlgoritmService(resourcesAdapter, updateAdapter, animationAdapter);

        DataFormInput form = new DataFormInput();
        form.setTitle("Formulario");
        form.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        planePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = planePanel.getWidth();
                int h = planePanel.getHeight();
                int margin = 20;
                store.setRange(margin, Math.max(margin + 1, w - margin), margin, Math.max(margin + 1, h - margin));
                planePanel.markDirty();
                planePanel.repaint();
            }
        });

        planePanel.addMouseListener(new PlaneClickController(store, planePanel));

        form.btnAddData.addActionListener(new AddDataController(form, store, generator, planePanel));
        form.btnCalculate.addActionListener(new CalculateController(form, store, service, planePanel));
        form.btnClear.addActionListener(new ClearController(store, planePanel));

        form.setVisible(true);
        planeFrame.setVisible(true);
    }
}
