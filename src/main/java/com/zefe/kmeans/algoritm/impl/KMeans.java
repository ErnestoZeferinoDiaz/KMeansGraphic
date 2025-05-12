package com.zefe.kmeans.algoritm.impl;

import com.zefe.kmeans.algoritm.IConditionLoop;
import com.zefe.kmeans.algoritm.IDistance;
import com.zefe.kmeans.algoritm.IPoint;

public class KMeans extends KMeansSubject {
    private static final KMeans km = new KMeans();
    private IDistance calculatorDistance;
    private IPoint[] points;
    private IPoint[] centroidsOrigin;
    private IPoint[] centroidsUpdate;
    private byte[] indexCentroidsForEachPoint;
    private IConditionLoop conditionLoop;

    private KMeans(){ }

    public static KMeans getInstance(){
        return km;
    }

    public IPoint[] getCentroidsUpdate(){ return this.centroidsUpdate; }
    public byte[] getRelationCentroidsForEachPoint() { return this.indexCentroidsForEachPoint; }

    public void executeLoop(){
        boolean isEqualsCentroids = true;
        int iteration = 0;

        while(isEqualsCentroids){
            isEqualsCentroids = this.oneLoop(iteration);
            iteration = iteration + 1;
        }
    }

    public boolean oneLoop(int iteration){
        DataKMeans dataKMeansBefore, dataKMeansAfter;
        boolean isEqualsCentroids;

        this.relatePointsToCentroids();
        dataKMeansBefore = new DataKMeans(this.points,this.centroidsOrigin,this.indexCentroidsForEachPoint);
        this.notifyRelatedPointsObservers(dataKMeansBefore,iteration);

        this.updatedCentroids();
        dataKMeansAfter = new DataKMeans(this.points,this.centroidsUpdate,this.indexCentroidsForEachPoint);
        this.notifyIterationExecutedObservers(dataKMeansBefore, dataKMeansAfter, iteration);

        this.conditionLoop.calculateConditionLoop(this.centroidsOrigin, this.centroidsUpdate, iteration);
        isEqualsCentroids = this.conditionLoop.isNextLoop();

        if(isEqualsCentroids){
            this.centroidsOrigin = this.centroidsUpdate;
            this.centroidsUpdate = null;
            this.indexCentroidsForEachPoint = null;
        }else{
            this.notifyIterationCompletedObservers();
        }

        return isEqualsCentroids;
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

    public void setData(IPoint[] points, IPoint[] centroids){
        this.clear();
        this.points = points;
        this.centroidsOrigin = centroids;
    }

    public void setData(DataKMeans dataKMeans){
        this.setData(dataKMeans.getPoints(), dataKMeans.getCentroids());
    }

    public void setCalculatorDistance(IDistance calculatorDistance){
        this.calculatorDistance = calculatorDistance;
    }
    public void setConditionLoop(IConditionLoop conditionLoop){ this.conditionLoop = conditionLoop; }

    public void clear(){
        this.centroidsUpdate = null;
        this.centroidsOrigin = null;
        this.indexCentroidsForEachPoint = null;
        this.points = null;
    }


}
