package com.example.model;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;

public class KMeansConfig {
    private double radioOfCentroid;
    private double radioOfPoints;
    private int numberOfStartingPoints;
    private int milliseconds;
    private int numberOfIterations;
    private double maxXRange;
    private double minXRange;
    private double maxYRange;
    private double minYRange;
    public boolean reset;
    
    private Rectangle rectanglePane;
    public LinkedList<Circle> centroids;
    public LinkedList<Circle> centroids_tmp;
    public LinkedList<Circle> points;
    
    public LinkedList<Color> colors;
    
    public KMeansConfig(){
        centroids = new LinkedList();
        centroids_tmp = new LinkedList();
        points = new LinkedList();
        rectanglePane = new Rectangle();
        reset=true;
        colors = new LinkedList();
    }
    
    public double rangeX(){
        return minXRange+Math.random()*(maxXRange-minXRange);
    }
    
    public double rangeY(){
        return minYRange+Math.random()*(maxYRange-minYRange);
    }

    public Rectangle getRectanglePane() {
        return rectanglePane;
    }

    public void setRectanglePane(Rectangle rectanglePane) {
        this.rectanglePane.setBounds(rectanglePane);
    }
    
    public double getRadioOfCentroid() {
        return radioOfCentroid;
    }

    public void setRadioOfCentroid(double radioOfCentroid) {
        this.radioOfCentroid = radioOfCentroid;
        for(int x=0; x<this.centroids.size(); x++){
            this.centroids.get(x).setRadio(this.radioOfCentroid);
        }
    }

    public double getRadioOfPoints() {
        return radioOfPoints;
    }

    public void setRadioOfPoints(double radioOfPoints) {
        this.radioOfPoints = radioOfPoints;
        for(int x=0; x<this.points.size(); x++){
            this.points.get(x).setRadio(this.radioOfPoints);
        }
    }

    public int getNumberOfStartingPoints() {
        return numberOfStartingPoints;
    }

    public void setNumberOfStartingPoints(String numberOfStartingPoints) {
        this.numberOfStartingPoints = Integer.parseInt(numberOfStartingPoints);
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(String milliseconds) {
        this.milliseconds = Integer.parseInt(milliseconds);
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(String numberOfIterations) {
        this.numberOfIterations = Integer.parseInt(numberOfIterations);
    }

    public double getMaxXRange() {
        return maxXRange;
    }

    public void setMaxXRange(String maxXRange) {
        this.maxXRange = Double.parseDouble(maxXRange);
    }

    public double getMinXRange() {
        return minXRange;
    }

    public void setMinXRange(String minXRange) {
        this.minXRange = Double.parseDouble(minXRange);
    }

    public double getMaxYRange() {
        return maxYRange;
    }

    public void setMaxYRange(String maxYRange) {
        this.maxYRange = Double.parseDouble(maxYRange);
    }

    public double getMinYRange() {
        return minYRange;
    }

    public void setMinYRange(String minYRange) {
        this.minYRange = Double.parseDouble(minYRange);
    }

    public LinkedList<Circle> getCentroids() {
        return centroids;
    }

    public void setCentroids(LinkedList<Circle> centroids) {
        this.centroids = centroids;
    }

    public LinkedList<Circle> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Circle> points) {
        this.points = points;
    }
    
    
}
