package com.zefe.kmeans.impl;


import java.util.ArrayList;

public class KMeansHistory{
    private static final KMeansHistory kmeansHistory = new KMeansHistory();
    private final ArrayList<DataKMeans> history = new ArrayList();
    private int posicion = -1;
    private int maxItems = 2;

    private KMeansHistory(){}

    public static KMeansHistory getInstance(){
        return kmeansHistory;
    }

    public void save(DataKMeans data) {
        this.history.add(data);

        if(this.posicion == this.maxItems){
            this.history.get(0).setCentroids(null);
            this.history.get(0).setPoints(null);
            this.history.get(0).setRelationsPointsWithCentroid(null);
            this.history.remove(0);
        }else{
            this.posicion = this.posicion + 1;
        }

    }

    public boolean isBack() {
        return this.posicion > 0;
    }

    public boolean isNext() {
        return this.posicion < this.history.size() - 2;
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

    public DataKMeans getBack(){
        if (this.isBack()) {
            return this.history.get(this.posicion - 1);
        }
        return null;
    }

    public DataKMeans getNext(){
        if (this.isNext()) {
            return this.history.get(this.posicion + 1);
        }
        return null;
    }

    public DataKMeans getCurrentStatus() {
        if(this.posicion>=0 && this.posicion<this.history.size()){
            return this.history.get(this.posicion);
        }
        return null;
    }
}