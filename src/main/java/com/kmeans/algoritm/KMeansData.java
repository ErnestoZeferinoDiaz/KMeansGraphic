package com.kmeans.algoritm;

import java.awt.geom.Point2D;
import java.lang.Cloneable;

public class KMeansData{
    private Point2D[] points;
    private Point2D[] centroids;
    private int[] relationsPointsWithCentroid;

    public KMeansData(){
        this.points = null;
        this.centroids = null;
        this.relationsPointsWithCentroid = null;
    }

    public Point2D[] getPoints(){
        return this.points;
    }

    public Point2D[] getCentroids(){
        return this.centroids;
    }

    public int[] getRelationsPointsWithCentroid(){
        return this.relationsPointsWithCentroid;
    }

    public void setPoints(Point2D[] points){
        this.points = points;
    }

    public void setCentroids(Point2D[] centroids){
        this.centroids = centroids;
    }

    public void setRelationsPointsWithCentroid(int[] relationsPointsWithCentroid){
        this.relationsPointsWithCentroid = relationsPointsWithCentroid;
    }
}