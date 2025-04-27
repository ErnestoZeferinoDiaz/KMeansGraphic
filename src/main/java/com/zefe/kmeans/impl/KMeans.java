package com.zefe.kmeans.impl;

import com.zefe.kmeans.IDistance;
import com.zefe.kmeans.IPoint;

import java.util.ArrayList;

public class KMeans {
    private static final KMeans km = new KMeans();
    private IDistance calculatorDistance;
    private IPoint[] points;
    private IPoint[] centroidsOrigin;
    private IPoint[] centroidsUpdate;
    private byte[] indexCentroidsForEachPoint;

    private KMeans(){

    }

    public static KMeans getInstance(){
        return km;
    }

    public byte[] getIndexCentroidsForEachPoint(){
        return this.indexCentroidsForEachPoint;
    }

    public IPoint[] getCentroidsUpdate(){
        return this.centroidsUpdate;
    }

    public void execute(){
        this.relatePointsToCentroids();
        this.updatedCentroids();
    }

    private void updatedCentroids(){
        int sizeArrayCentroids = this.centroidsOrigin.length;
        int sizeArrayPoints = this.points.length;
        int i,j,indexTmp;
        double deltaX, deltaY;
        double numberDeltas;
        this.centroidsUpdate = new Centroid[sizeArrayCentroids];

        for(i=0; i<sizeArrayCentroids; i++){
            numberDeltas = 0;
            deltaX = 0;
            deltaY = 0;
            for(j=0; j<sizeArrayPoints; j++){
                indexTmp = this.indexCentroidsForEachPoint[j];
                if(i == indexTmp){
                    numberDeltas = numberDeltas + 1;
                    deltaX = deltaX + this.points[j].getX();
                    deltaY = deltaY + this.points[j].getY();
                }
            }

            if(numberDeltas == 0){
                this.centroidsUpdate[i] = this.centroidsOrigin[i];
            }else{
                this.centroidsUpdate[i] = new Centroid(
                        (float) (deltaX / numberDeltas),
                        (float) (deltaY / numberDeltas)
                );
            }
        }
    }

    private void relatePointsToCentroids(){
        int sizeArrayPoints = this.points.length;
        int indexCentroid,i;

        this.indexCentroidsForEachPoint = new byte[sizeArrayPoints];

        for(i=0; i<sizeArrayPoints; i++){
            indexCentroid = this.getIndexNearestCentroid(this.points[i]);
            this.indexCentroidsForEachPoint[i] = (byte) indexCentroid;
        }
    }

    private int getIndexNearestCentroid(IPoint point){
        double minDistance=-1, tmpDistance=-1;
        int indexNearestCentroid;
        int sizeArrayCentroids = this.centroidsOrigin.length;

        if (sizeArrayCentroids == 1){
            return 0;
        }

        indexNearestCentroid=0;
        minDistance = this.calculatorDistance.distance(
            point,this.centroidsOrigin[indexNearestCentroid]
        );
        for(int i=1; i<sizeArrayCentroids; i++){
            tmpDistance = this.calculatorDistance.distance(
                    point,this.centroidsOrigin[i]
            );
            if(tmpDistance<minDistance){
                minDistance = tmpDistance;
                indexNearestCentroid = i;
            }

        }
        return indexNearestCentroid;
    }

    public void setData(DataKMeans dataKMeans){
        this.points = dataKMeans.getPoints();
        this.centroidsOrigin = dataKMeans.getCentroids();
    }

    public void setCalculatorDistance(IDistance calculatorDistance){
        this.calculatorDistance = calculatorDistance;
    }

    public void clear(){
        this.centroidsUpdate = null;
        this.centroidsOrigin = null;
        this.indexCentroidsForEachPoint = null;
        this.points = null;
    }

}
