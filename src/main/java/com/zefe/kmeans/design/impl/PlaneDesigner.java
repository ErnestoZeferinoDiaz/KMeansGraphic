package com.zefe.kmeans.design.impl;

import com.zefe.kmeans.design.AbstractDesigner;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PlaneDesigner extends AbstractDesigner {
    private BufferedImage planeImage;

    @Override
    public BufferedImage render() {
        BufferedImage imageResult, imageIDesigner;
        System.out.println("Iniciando plano");
        imageResult = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        if(!this.isDraw()){
            System.out.println("Dibujando plano");
            this.planeImage = this.getImagePlane();
            this.setDraw(true);
        }

        imageResult.getGraphics().drawImage(this.planeImage,0,0,null);

        if(this.getNextDesigner() != null){
            this.getNextDesigner().setDataDraw(this.getDataDraw());
            this.getNextDesigner().setDimention(this.getWidth(),this.getHeight());
            imageIDesigner = this.getNextDesigner().render();
            imageResult.getGraphics().drawImage(
                imageIDesigner, 0,0,null
            );
        }
        System.out.println("Retornando plano");
        return imageResult;
    }

    private BufferedImage getImagePlane(){
        Graphics2D g;
        BufferedImage tmpImage;
        int sGui=5;
        int width = this.getWidth();
        int height = this.getHeight();
        int middleWidth = width/2;
        int middleHeight = height/2;


        tmpImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = tmpImage.createGraphics();

        g.setColor(Color.DARK_GRAY);
        for(int x=0; x<width; x=x+20){
            g.drawLine(x, 0, x, height);
        }
        for(int y=0; y<height; y=y+20){
            g.drawLine(0, y,width, y); 
        }

        g.setColor(Color.WHITE);
        g.drawLine(0, middleHeight, width, middleHeight);
        g.drawLine(middleWidth, 0, middleWidth, height);

        for(int x=0; x<width; x=x+10){
            g.drawLine(x, middleHeight-sGui, x, middleHeight+sGui);
        }
        for(int y=0; y<height; y=y+10){
            g.drawLine(middleWidth-sGui, y,middleWidth+sGui, y);
        }

        return tmpImage;

    }
}

