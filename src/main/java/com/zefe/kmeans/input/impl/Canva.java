package com.zefe.kmeans.input.impl;

import com.zefe.kmeans.algoritm.impl.DataKMeans;
import com.zefe.kmeans.design.IDesigner;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Canva extends JPanel {
    private IDesigner iDesigners;
    private DataKMeans dataKMeans;
    private BufferedImage imageCanva;

    public Canva(){
        this.setBackground(new Color(0,0,0));
        this.setLayout(new BorderLayout());
        this.dataKMeans = null;
    }

    public void addIDesigner(IDesigner iDesigner){
        this.iDesigners = iDesigner;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.getWidth() <= 0){
            return;
        }

        if(this.getHeight() <= 0){
            return;
        }

        this.iDesigners.setDataDraw(this.dataKMeans);
        this.iDesigners.setDimention(this.getWidth(),this.getHeight());
        this.imageCanva = this.iDesigners.render();
        g.drawImage(
            this.imageCanva,
            0,0,null
        );
    }

    public void setDataKMeans(DataKMeans dataKMeans){
        this.dataKMeans = dataKMeans;
    }

    public void setIDesigners(IDesigner iDesigners){
        this.iDesigners = iDesigners;
    }


}