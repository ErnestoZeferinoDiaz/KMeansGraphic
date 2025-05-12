package com.zefe.kmeans.algoritm.impl;

import com.zefe.kmeans.algoritm.IPoint;

public class DataKMeans {
    private Point[] points;
    private Centroid[] centroids;
    private byte[] relationsPointsWithCentroid;

    public DataKMeans(){
        this.points = null;
        this.centroids = null;
        this.relationsPointsWithCentroid = null;
    }

    public DataKMeans(IPoint[] points){
        this.points = (Point[]) points;
        this.centroids = null;
        this.relationsPointsWithCentroid = null;
    }

    public DataKMeans(IPoint[] points, IPoint[] centroids){
        this.points = (Point[]) points;
        this.centroids = (Centroid[]) centroids;
        this.relationsPointsWithCentroid = null;
    }

    public DataKMeans(IPoint[] points, IPoint[] centroids, byte[] relationsPointsWithCentroid){
        this.points = (Point[]) points;
        this.centroids = (Centroid[]) centroids;
        this.relationsPointsWithCentroid = relationsPointsWithCentroid;
    }

    public Point[] getPoints(){
        return this.points;
    }

    public Centroid[] getCentroids(){
        return this.centroids;
    }

    public byte[] getRelationsPointsWithCentroid(){
        return this.relationsPointsWithCentroid;
    }

    public void setPoints(IPoint[] points){
        if(this.points == null){
            this.points = (Point[]) points;
        }
    }

    public void setCentroids(IPoint[] centroids){
        if(this.centroids == null){
            this.centroids = (Centroid[]) centroids;
        }
    }

    public void setRelationsPointsWithCentroid(byte[] relationsPointsWithCentroid){
        if(this.relationsPointsWithCentroid == null){
            this.relationsPointsWithCentroid = relationsPointsWithCentroid;
        }
    }
}
