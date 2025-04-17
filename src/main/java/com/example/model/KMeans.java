package com.example.model;

import java.awt.Color;
import java.util.LinkedList;

public class KMeans {
    public KMeansConfig kmc;
    private Circle tmp;
    public KMeans(){
        
    }
    
    public int randColor(){
        return (int) (Math.random()*(255));
    }
    public void addCentroidPlane(double x, double y){
        this.tmp = new Circle();
        this.tmp.setRadio(kmc.getRadioOfCentroid());
        this.tmp.setRectangleRef(kmc.getRectanglePane());
        this.tmp.setXScreen(x);
        this.tmp.setYScreen(y);
        this.kmc.centroids.add(tmp);
        
        this.kmc.colors.add(new Color(this.randColor(),this.randColor(),this.randColor()));
    }
    
    public void addCentroidScreen(String x, String y){
        this.tmp = new Circle();
        this.tmp.setRadio(kmc.getRadioOfCentroid());
        this.tmp.setRectangleRef(kmc.getRectanglePane());
        this.tmp.setXPlane(Double.parseDouble(x));
        this.tmp.setYPlane(Double.parseDouble(y));
        this.kmc.centroids.add(tmp);
        
        this.kmc.colors.add(new Color(this.randColor(),this.randColor(),this.randColor()));
    }
    
    public void addPointPlane(double x, double y){
        this.tmp = new Circle();
        this.tmp.setRadio(kmc.getRadioOfPoints());
        this.tmp.setRectangleRef(kmc.getRectanglePane());
        this.tmp.setXScreen(x);
        this.tmp.setYScreen(y);
        this.kmc.points.add(tmp);
        
    }
    
    public void addPointScreen(String x, String y){
        this.tmp = new Circle();
        this.tmp.setRadio(kmc.getRadioOfPoints());
        this.tmp.setRectangleRef(kmc.getRectanglePane());
        this.tmp.setXPlane(Double.parseDouble(x));
        this.tmp.setYPlane(Double.parseDouble(y));
        this.kmc.points.add(tmp);
    }

    public LinkedList<Circle> getCentroids() {
        return this.kmc.centroids;
    }
    
    public void init(){
        this.kmc.colors.clear();
        this.kmc.centroids.clear();
        this.kmc.points.clear();
        this.kmc.reset=true;
        for(int x=0; x<this.kmc.getNumberOfStartingPoints(); x++){
            this.tmp = new Circle();
            this.tmp.setRadio(kmc.getRadioOfPoints());
            this.tmp.setRectangleRef(kmc.getRectanglePane());
            this.tmp.setXPlane(this.kmc.rangeX());
            this.tmp.setYPlane(this.kmc.rangeY());
            this.kmc.points.add(tmp);
        }
    }
    
    public void run(){
        this.kmc.centroids_tmp.clear();
        this.kmc.centroids_tmp.addAll(this.kmc.centroids);
        this.kmc.centroids.clear();
        int sc=this.kmc.centroids_tmp.size();
        int sp=this.kmc.points.size();
        int i=0;
        double []distances = new double[sc];
        double nx,ny;
        for(int x=0; x<sp; x++){
            i=0;
            for(int y=0; y<sc; y++){
                distances[y]=this.kmc.points.get(x).distance(this.kmc.centroids_tmp.get(y));
                if(y>0){
                    if(distances[i]>distances[y]){
                        i=y;
                    }
                }
            }
            this.kmc.points.get(x).setLabel(i);
        }
        
        for(int x=0; x<sc; x++){            
            nx=0;
            ny=0;
            i=0;
            for(int y=0; y<sp; y++){
                if(this.kmc.points.get(y).getLabel()==x){
                    nx=nx+this.kmc.points.get(y).getXScreen();
                    ny=ny+this.kmc.points.get(y).getYScreen();
                    i++;
                }
            }
            nx=nx/i;
            ny=ny/i;
            this.addCentroidPlane(nx,ny);
        }
    }
    
    
}
