package com.zefe.kmeans.impl;

import com.zefe.kmeans.IDistance;
import com.zefe.kmeans.IPoint;

public class EuclideanDistance implements IDistance {
    private static final EuclideanDistance instanceEuclideanDistance = new EuclideanDistance();

    private EuclideanDistance(){

    }

    public static EuclideanDistance getInstance(){
        return instanceEuclideanDistance;
    }

    @Override
    public double distance(IPoint point1, IPoint point2) {
        return Math.sqrt(
                Math.pow(point1.getX() - point2.getX(),2) +
                Math.pow(point1.getY() - point2.getY(),2)
        );
    }
}
