package com.kmeans.event.impl;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import com.kmeans.algoritm.KMeansHistory;
import com.kmeans.algoritm.KMeansData;

public class DeleteHistoryActionListener implements ActionListener {
    private final KMeansHistory historyKM;
    private final JPanel jpanel;

    public DeleteHistoryActionListener(JPanel jpanel, KMeansHistory historyKM){
        this.historyKM = historyKM;
        this.jpanel = jpanel;
        
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.historyKM.clearHistory();
        this.historyKM.save(new KMeansData());
        this.jpanel.repaint();
    }
}