package com.kmeans.algoritm;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class KMeansHistory{
    private final ArrayList<KMeansData> history = new ArrayList();
    private int posicion = -1;

    public void save(KMeansData data) {
        this.history.add(data);
        this.posicion = this.history.size() - 1;
    }

    public boolean isBack() {
        return this.posicion > 0;
    }

    public boolean isNext() {
        return this.posicion < this.history.size() - 1;
    }

    public void back() {
        if (this.isBack()) {
            this.posicion = this.posicion - 1;
            
        }        
    }

    public void next() {
        if (this.isNext()) {
            this.posicion = this.posicion + 1;
        }        
    }

    public void clearHistory() {
        this.history.clear();
        this.posicion = -1;
    }

    public void setPosition(int index) {
        if(index >= 0 && index<this.history.size()){
            this.posicion = index;
        }        
    }

    public int getPosition() {
        return this.posicion;
    }

    public KMeansData getCurrentStatus() {
        if(this.posicion>=0){
            return this.history.get(this.posicion);
        }
        return null;
    }
}