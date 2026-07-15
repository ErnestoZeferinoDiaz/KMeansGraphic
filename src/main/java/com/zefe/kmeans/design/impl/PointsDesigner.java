package com.zefe.kmeans.design.impl;

import com.zefe.kmeans.algoritm.IPoint;
import com.zefe.kmeans.design.AbstractDesigner;
import com.zefe.kmeans.design.ColorManager;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PointsDesigner extends AbstractDesigner {
    private BufferedImage pointsImage;
    private ColorManager colorManager;

    public PointsDesigner(ColorManager colorManager){
        this.colorManager = colorManager;
    }

    @Override
    public BufferedImage render() {
        BufferedImage tmp = null;
        System.out.println("Iniciando puntos");
        System.out.println("Dibujando puntos");
        this.pointsImage = this.getPointsImage();
        this.setDraw(true);

        if(this.getNextDesigner() != null){
            this.getNextDesigner().setDataDraw(this.getDataDraw());
            this.getNextDesigner().setDimention(this.getWidth(),this.getHeight());
            System.out.println("Next Centroids");
            tmp = this.getNextDesigner().render();
            this.pointsImage.getGraphics().drawImage(
                tmp, 0,0,null
            );
        }

        System.out.println("Retornando puntos");
        return this.pointsImage;
    }

    public BufferedImage getPointsImage(){
        BufferedImage tmpPointsImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g;
        IPoint[] points;
        Color colorApply;
        byte[] relation;
        g = (Graphics2D) tmpPointsImage.getGraphics();

        if(this.getDataDraw() == null) {
            return tmpPointsImage;
        }

        points = this.getDataDraw().getPoints();
        relation = this.getDataDraw().getRelationsPointsWithCentroid();
        if(points == null){
            return tmpPointsImage;
        }

        if(relation == null){
            g.setColor(Color.WHITE);
            for(int i=0; i<points.length; i++) {
                g.fillOval(
                        ((int)points[i].getX()),
                        ((int)points[i].getY()),
                        4, 4
                );
            }
            return tmpPointsImage;
        }

        for(int i=0; i<relation.length; i++){
            this.colorManager.addColor(i);
        }

        for(int i=0; i<points.length; i++) {
            g.setColor(
                this.colorManager.getColorForCentroid(relation[i])
            );

            g.fillOval(
                ((int)points[i].getX()),
                ((int)points[i].getY()),
                4, 4
            );
        }

        return tmpPointsImage;

    }


}
