package com.zefe.kmeans.algoritm.impl;

import com.zefe.kmeans.algoritm.IConditionLoop;
import com.zefe.kmeans.algoritm.IPoint;

public class CompareCentroidsCondition implements IConditionLoop {
    private double error;
    private boolean isCodition;

    public CompareCentroidsCondition(double error){
        this.setError(error);
    }

    @Override
    public void calculateConditionLoop(IPoint[] centroidsBefore, IPoint[] centroidsAfter, int numberIteration) {
        IPoint pointBefore, pointAfter;
        double distanceTmp;
        boolean isEqualsCentroids = true;
        for(int i=0; i<centroidsBefore.length; i++) {
            pointBefore = centroidsBefore[i];
            pointAfter = centroidsAfter[i];
            distanceTmp = Math.abs(pointBefore.getX() - pointAfter.getX());
            isEqualsCentroids = isEqualsCentroids && (distanceTmp < this.error);
            distanceTmp = Math.abs(pointBefore.getY() - pointAfter.getY());
            isEqualsCentroids = isEqualsCentroids && (distanceTmp < this.error);
        }
        this.isCodition = !isEqualsCentroids;
    }

    @Override
    public boolean isNextLoop() {
        return this.isCodition;
    }


    public void setError(double error){ this.error = error; }
}
