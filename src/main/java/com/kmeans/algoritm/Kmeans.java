package com.kmeans.algoritm;

import java.awt.geom.Point2D;
import java.lang.Integer;

public class Kmeans{
    private Point2D[] points;
    private int[] indexCentroidsForEachPoint;
    private Point2D[] centroidsOrigin;
    private Point2D[] centroidsUpdate;
    private double[] dx;
    private double[] dy;
    private double[] numberDeltas;
    private int sizeArrayCentroids;
    private int sizeArrayPoints;
    private boolean newData = false;
    
    public Kmeans(){
        this.dx=null;
        this.dy=null;
        this.numberDeltas=null;
    }

    public Point2D[] getUpdateCentroid(){
        return this.centroidsUpdate;
    }

    public int[] getRelationPointsWithCentroids(){
        return this.indexCentroidsForEachPoint;
    }

    public void updateCentroid(){
        int indexCentroid = -1;
        if(this.newData){
            for(int i=0; i<this.sizeArrayPoints; i++){
                indexCentroid = this.getIndexNearestCentroid(points[i]);
                this.indexCentroidsForEachPoint[i] = indexCentroid;
                dx[indexCentroid] = dx[indexCentroid] + points[i].getX();
                dy[indexCentroid] = dy[indexCentroid] + points[i].getY();
                numberDeltas[indexCentroid] = numberDeltas[indexCentroid] + 1;
            }

            for(int i=0; i<this.sizeArrayCentroids; i++){
                dx[i] = dx[i] / numberDeltas[i];
                dy[i] = dy[i] / numberDeltas[i];

                centroidsUpdate[i] = new Point2D.Double(dx[i],dy[i]);
            }
            this.newData = false;
        }
        
    }

    private int getIndexNearestCentroid(Point2D point){
        
        double minDistance=-1, tmpDistance=-1;
        int indexNearestCentroid=0;

        if (this.sizeArrayCentroids == 1){
            return 0;
        }else {
            minDistance = point.distance(this.centroidsOrigin[indexNearestCentroid]);
            for(int i=1; i<this.sizeArrayCentroids; i++){
                tmpDistance = point.distance(this.centroidsOrigin[i]);
                if(tmpDistance<minDistance){
                    minDistance = tmpDistance;
                    indexNearestCentroid = i;
                }
                
            }
            return indexNearestCentroid;
        }        
    }

    public void setData(Point2D[] points,Point2D[] centroidsOrigin){
        this.points = points;
        this.centroidsOrigin = centroidsOrigin;
        this.sizeArrayCentroids = this.centroidsOrigin.length;
        this.sizeArrayPoints = this.points.length;

        this.indexCentroidsForEachPoint = new int[this.sizeArrayPoints];
        this.centroidsUpdate = new Point2D[this.sizeArrayCentroids];
        
        this.dx = new double[this.sizeArrayCentroids];
        this.dy = new double[this.sizeArrayCentroids];
        this.numberDeltas = new double[this.sizeArrayCentroids];
        
        for(int i=0; i<this.sizeArrayCentroids; i++){
            this.dx[i]=0;
            this.dy[i]=0;
            this.numberDeltas[i]=0;            
        }

        this.newData = true;
    }

}