package com.kmeans.event.impl;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Integer;
import javax.swing.JOptionPane;
import com.kmeans.input.impl.RandomKMeans;
import com.kmeans.input.impl.DataFormInput;
import com.kmeans.algoritm.Kmeans;
import com.kmeans.algoritm.KMeansData;
import com.kmeans.algoritm.KMeansHistory;

public class ExecuteActionListener implements ActionListener {
    private final DataFormInput viewPrincipal;
    private final RandomKMeans randKM = new RandomKMeans();
    private final KMeansHistory historyKM;
    private final Kmeans km;

    public ExecuteActionListener(DataFormInput viewPrincipal, KMeansHistory historyKM, Kmeans km){
        this.viewPrincipal = viewPrincipal;
        this.historyKM = historyKM;
        this.km = km;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        int tmpPosition = this.historyKM.getPosition();
        this.run();
        this.historyKM.setPosition(tmpPosition+1);
        JOptionPane.showMessageDialog(null, "Calculo terminado.");
    }

    private void run(){
        if(this.historyKM.getCurrentStatus().getPoints() == null){
            JOptionPane.showMessageDialog(null, "No hay puntos");
            return;
        }

        if(this.historyKM.getCurrentStatus().getCentroids() == null){
            JOptionPane.showMessageDialog(null, "No hay centroides");
            return;
        }
        KMeansData tmpKM;        
        for(int i=0; i<6; i++){
            this.km.setData(
                this.historyKM.getCurrentStatus().getPoints(),
                this.historyKM.getCurrentStatus().getCentroids()
            );
            this.km.updateCentroid();

            tmpKM = new KMeansData();
            tmpKM.setPoints(this.historyKM.getCurrentStatus().getPoints());
            tmpKM.setCentroids(this.km.getUpdateCentroid());
            tmpKM.setRelationsPointsWithCentroid(this.km.getRelationPointsWithCentroids());
            this.historyKM.save(tmpKM);
        }
    }
}