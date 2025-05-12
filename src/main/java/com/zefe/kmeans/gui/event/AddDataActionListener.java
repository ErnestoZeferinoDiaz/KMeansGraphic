package com.zefe.kmeans.gui.event;

import com.zefe.kmeans.algoritm.impl.DataKMeans;
import com.zefe.kmeans.gui.DataFormInput;
import com.zefe.kmeans.input.impl.Canva;
import com.zefe.kmeans.model.IModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDataActionListener implements ActionListener {
    private final DataFormInput viewPrincipal;
    private final IModel iModel;
    private final Canva planeCanva;

    public AddDataActionListener(DataFormInput viewPrincipal, Canva planeCanva, IModel iModel){
        this.viewPrincipal = viewPrincipal;
        this.iModel = iModel;
        this.planeCanva = planeCanva;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Click addDataActionListener");
        DataKMeans dataKMeans = this.iModel.addDataRandom(
            Integer.parseInt(this.viewPrincipal.txtNumberPoints.getText()),
            Integer.parseInt(this.viewPrincipal.txtNumberCentroids.getText())
        );

        this.planeCanva.setDataKMeans(dataKMeans);
        this.planeCanva.repaint();
    }


}