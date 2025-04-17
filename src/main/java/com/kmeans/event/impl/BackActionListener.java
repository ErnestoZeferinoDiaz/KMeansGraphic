package com.kmeans.event.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import com.kmeans.algoritm.KMeansHistory;

public class BackActionListener implements ActionListener {
    private final KMeansHistory historyKM;    
    private final JPanel jpanel; 

    public BackActionListener(JPanel jpanel, KMeansHistory historyKM){
        this.historyKM = historyKM;
        this.jpanel = jpanel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.historyKM.back();
        this.jpanel.repaint();
    }
}