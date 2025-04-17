package com.example.model;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Circle{
    private double xScreen,yScreen;
    private double xPlane,yPlane;
    private double radio;
    private Rectangle ref;
    private int label;
    
    public Circle(){
        label=-1;
    }
    public double distance(Circle c){
        double r;
        r=Math.pow(this.getXPlane()-c.getXPlane(),2)+Math.pow(this.getYPlane()-c.getYPlane(),2);
        r=Math.sqrt(r);
        return r;
    }
    public Ellipse2D getCircle(){
        return new Ellipse2D.Double(this.getXScreen()-this.radio/2,this.getYScreen()-this.radio/2,this.radio,this.radio);
    }
    
    public double getXScreen() {
        double xO;
        xO=this.ref.getWidth()/2;
        this.xScreen = xO+xPlane;
        return this.xScreen;
    }

    public void setXScreen(double xScreen) {
        double xO;
        xO=this.ref.getWidth()/2;
        this.xScreen = xScreen;
        this.xPlane = xScreen-xO;
    }

    public double getYScreen() {
        double yO;
        yO=this.ref.getHeight()/2;
        this.yScreen = yO-yPlane;
        return yScreen;
    }

    public void setYScreen(double yScreen) {
        double yO;
        yO=this.ref.getHeight()/2;
        this.yScreen = yScreen;
        this.yPlane = yO-yScreen;        
    }

    public double getXPlane() {
        return xPlane;
    }

    public void setXPlane(double xPlane) {
        this.xPlane = xPlane;
        this.getXScreen();
    }

    public double getYPlane() {
        return yPlane;
    }

    public void setYPlane(double yPlane) {
        this.yPlane = yPlane;
        this.getYScreen();
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
    
    public void setRectangleRef(Rectangle r) {
        this.ref = r;
        this.setXPlane(this.getXPlane());
        this.setYPlane(this.getYPlane());
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }
    
    public String toString(){
        String cad="\n";
        cad+="\n Point Screen: ("+this.getXScreen()+","+this.getYScreen()+")";
        cad+="\n Point Plane: ("+this.getXPlane()+","+this.getYPlane()+")";
        
        return cad;
    }
    
}
