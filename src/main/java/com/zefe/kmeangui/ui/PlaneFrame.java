package com.zefe.kmeangui.ui;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

public class PlaneFrame extends JFrame {

    private final PlanePanel planePanel;

    public PlaneFrame(PlanePanel planePanel) {
        this.planePanel = planePanel;
        this.setTitle("Plano");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(400, 300));
        this.getContentPane().setBackground(Color.BLACK);
        this.getContentPane().add(this.planePanel);
    }

    public PlanePanel getPlanePanel() {
        return this.planePanel;
    }
}
