package com.kmeans.configure;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;

public class KMeansResource {
    private static KMeansResource kMeansResource;
    

    private int planeWidth;
    private int planeHeight;
    private int maxWidth;
    private int maxHeight;
    private int pointWidth;
    private int pointHeight;
    private int centroidWidth;
    private int centroidHeight;

    private KMeansResource(){
        
    }

    public static KMeansResource getInstance(){
        if ( kMeansResource == null){
            kMeansResource = new KMeansResource();
            kMeansResource.setSizeScreen(Toolkit.getDefaultToolkit().getScreenSize());
        }
        return kMeansResource;
    }

    public void setSizePlane(int width, int height){
        this.planeWidth = width;
        this.planeHeight = height;
        
    }

    public void setSizeScreen(Dimension sizeScreen){
        this.maxWidth = sizeScreen.width;
        this.maxHeight = sizeScreen.height;
    }

    public int getPlaneWidth() {
        return planeWidth;
    }

    public int getPlaneHeight() {
        return planeHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getPointWidth() {
        return pointWidth;
    }

    public int getPointHeight() {
        return pointHeight;
    }

    public int getCentroidWidth() {
        return centroidWidth;
    }
    
    public int getCentroidHeight() {
        return centroidHeight;
    }

}
