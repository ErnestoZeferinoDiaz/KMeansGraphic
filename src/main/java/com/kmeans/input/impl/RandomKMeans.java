package com.kmeans.input.impl;

import com.kmeans.input.IKMeansInput;
import com.kmeans.algoritm.KMeansData;
import java.util.Random;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class RandomKMeans implements IKMeansInput,ComponentListener{
    private final Random random = new Random();
    private int numberPoints;
    private int numberCentroids;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    public KMeansData loadData(){
        KMeansData tmpData = new KMeansData();
        Point2D[] points = new Point2D[this.numberPoints];
        Point2D[] centroids = new Point2D[this.numberCentroids];

        for(int i=0; i<this.numberPoints; i++){
            points[i] = this.randPoint(this.minX,this.maxX,this.minY,this.maxY);            
        }

        for(int i=0; i<this.numberCentroids; i++){
            centroids[i] = this.randPoint(this.minX,this.maxX,this.minY,this.maxY);            
        }

        tmpData.setPoints(points);
        tmpData.setCentroids(centroids);
        return tmpData;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        JFrame frame = (JFrame) e.getComponent();
        this.setRangeX(100,frame.getWidth()-100);
        this.setRangeY(100,frame.getHeight()-100);
    }

    public void setNumberItems(int numberPoints, int numberCentroids){
        this.numberPoints = numberPoints;
        this.numberCentroids = numberCentroids;
    }

    public void setRangeX(double minX, double maxX){
        this.minX = minX;
        this.maxX = maxX;
    }

    public void setRangeY(double minY, double maxY){
        this.minY = minY;
        this.maxY = maxY;
    }

    private Point2D randPoint(double minX, double maxX, double minY, double maxY) {        
        double x = minX + (maxX - minX) * this.random.nextDouble();
        double y = minY + (maxY - minY) * this.random.nextDouble();

        return new Point2D.Double(x,y);
    }

    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}
}