package com.zefe.kmeans.input.impl;

import com.zefe.kmeans.input.IKMeansInput;
import com.zefe.kmeans.algoritm.impl.Centroid;
import com.zefe.kmeans.algoritm.impl.Point;
import com.zefe.kmeans.algoritm.impl.DataKMeans;
import java.util.Random;

public class RandomKMeans implements IKMeansInput {
    private static final RandomKMeans randomKMeans = new RandomKMeans();
    private final Random random = new Random();
    private int numberPoints;
    private int numberCentroids;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    private RandomKMeans(){ }

    public static RandomKMeans getInstance(){
        return randomKMeans;
    }

    @Override
    public DataKMeans loadData(){
        DataKMeans tmpData = new DataKMeans();
        tmpData.setPoints(this.getRandPoints());
        tmpData.setCentroids(this.getRandCentroids());
        return tmpData;
    }

    public Centroid[] getRandCentroids(){
        Centroid[] centroids = new Centroid[this.numberCentroids];
        for(int i=0; i<this.numberCentroids; i++){
            centroids[i] = this.randCentroid(this.minX,this.maxX,this.minY,this.maxY);
        }
        return centroids;
    }

    public Point[] getRandPoints(){
        Point[] points = new Point[this.numberPoints];
        for(int i=0; i<this.numberPoints; i++){
            points[i] = this.randPoint(this.minX,this.maxX,this.minY,this.maxY);
        }
        return points;
    }

    public void setNumberItems(int numberPoints, int numberCentroids){
        this.numberPoints = numberPoints;
        this.numberCentroids = numberCentroids;
    }

    public void setRangeX(int minX, int maxX){
        this.minX = minX;
        this.maxX = maxX;
    }

    public void setRangeY(int minY, int maxY){
        this.minY = minY;
        this.maxY = maxY;
    }

    private Point randPoint(int minX, int maxX, int minY, int maxY) {
        short x = (short) (minX + this.random.nextInt(maxX - minX + 1));
        short y = (short) (minY + this.random.nextInt(maxY - minY + 1));
        return new Point(x,y);
    }

    private Centroid randCentroid(int minX, int maxX, int minY, int maxY) {
        int x = minX + this.random.nextInt(maxX - minX + 1);
        int y = minY + this.random.nextInt(maxY - minY + 1);
        return new Centroid(x,y);
    }

}
