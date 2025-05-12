package com.zefe.kmeans.algoritm.impl;

import com.zefe.kmeans.algoritm.IPoint;

public class Point implements IPoint {
    private short x;
    private short y;

    public Point(){

    }

    public Point(short x, short y){
        this.setPoint(x,y);
    }

    public double getX() {
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setPoint(short x, short y){
        this.x = x;
        this.y = y;
    }
}
