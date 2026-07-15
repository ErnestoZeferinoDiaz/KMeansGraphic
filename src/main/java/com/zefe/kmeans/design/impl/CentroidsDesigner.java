package com.zefe.kmeans.design.impl;

import com.zefe.kmeans.algoritm.IPoint;
import com.zefe.kmeans.design.AbstractDesigner;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class CentroidsDesigner extends AbstractDesigner {
    private BufferedImage centroidsImage;

    @Override
    public BufferedImage render() {
        BufferedImage tmp = null;
        System.out.println("Iniciando centroids");
        System.out.println("Dibujando centroids");
        this.centroidsImage = this.getCentroidsImage();
        this.setDraw(true);

        if(this.getNextDesigner() != null){
            this.getNextDesigner().setDataDraw(this.getDataDraw());
            this.getNextDesigner().setDimention(this.getWidth(),this.getHeight());
            tmp = this.getNextDesigner().render();
            this.centroidsImage.getGraphics().drawImage(
                    tmp, 0,0,null
            );
        }

        System.out.println("Retornando centroids");
        return this.centroidsImage;
    }

    public BufferedImage getCentroidsImage(){
        BufferedImage tmpPointsImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g;
        IPoint[] centroids;
        g = (Graphics2D) tmpPointsImage.getGraphics();

        if(this.getDataDraw() == null) {
            return tmpPointsImage;
        }

        centroids = this.getDataDraw().getCentroids();
        if(centroids == null){
            return tmpPointsImage;
        }

        g.setColor(Color.RED);
        for(int i=0; i<centroids.length; i++) {
            g.fillOval(
                ((int)centroids[i].getX()),
                ((int)centroids[i].getY()),
                8, 8
            );
        }

        return tmpPointsImage;

    }
}
