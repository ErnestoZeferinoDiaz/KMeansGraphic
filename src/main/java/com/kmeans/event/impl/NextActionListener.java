package com.kmeans.event.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import com.kmeans.algoritm.KMeansHistory;

public class NextActionListener implements ActionListener {
    private final KMeansHistory historyKM;    
    private final JPanel jpanel; 

    public NextActionListener(JPanel jpanel, KMeansHistory historyKM){
        this.historyKM = historyKM;
        this.jpanel = jpanel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.historyKM.next();
        this.jpanel.repaint();
    }
}