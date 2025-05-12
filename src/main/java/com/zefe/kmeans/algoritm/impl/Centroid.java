package com.zefe.kmeans.algoritm.impl;

import com.zefe.kmeans.algoritm.IPoint;

public class Centroid implements IPoint{
    private float x;
    private float y;

    public Centroid(){

    }

    public Centroid(float x, float y){
        this.setPoint(x,y);
    }

    public double getX() {
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setPoint(float x, float y){
        this.x = x;
        this.y = y;
    }
}
