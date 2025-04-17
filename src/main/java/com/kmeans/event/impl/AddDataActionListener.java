package com.kmeans.event.impl;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.kmeans.input.impl.RandomKMeans;
import com.kmeans.input.impl.DataFormInput;
import com.kmeans.algoritm.Kmeans;
import com.kmeans.algoritm.KMeansData;
import com.kmeans.algoritm.KMeansHistory;

public class AddDataActionListener implements ActionListener {
    private final DataFormInput viewPrincipal;
    private final RandomKMeans randKM = new RandomKMeans();
    private final KMeansHistory historyKM;
    private final JPanel jpanel; 

    public AddDataActionListener(DataFormInput viewPrincipal, JPanel jpanel, KMeansHistory historyKM){
        this.viewPrincipal = viewPrincipal;
        this.historyKM = historyKM;
        this.jpanel = jpanel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.getData();
        this.jpanel.repaint();
    }

    private void getData(){
        this.randKM.setRangeX(50,1500);
        this.randKM.setRangeY(50,900);
        
        this.randKM.setNumberItems(
            Integer.parseInt(this.viewPrincipal.txtNumberPoints.getText()),
            Integer.parseInt(this.viewPrincipal.txtNumberCentroids.getText())
        );        
        this.historyKM.save(this.randKM.loadData());
    }
}