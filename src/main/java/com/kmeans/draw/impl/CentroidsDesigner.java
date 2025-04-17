package com.kmeans.draw.impl;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.Color;
import com.kmeans.draw.IDesigner;
import com.kmeans.draw.ColorManager;
import com.kmeans.algoritm.KMeansHistory;

public class CentroidsDesigner implements IDesigner{   
    private final ColorManager colorManager;
    private final KMeansHistory history;
    private Point2D[] tmpPoints;
    
    public CentroidsDesigner(KMeansHistory historyKM,ColorManager colorManager){
        this.colorManager = colorManager;
        this.history = historyKM;
    }

    @Override
    public void render(Graphics2D g) {
        this.tmpPoints = this.history.getCurrentStatus().getCentroids();

        if(this.tmpPoints != null){
            g.setColor(Color.RED);
            for(int i=0; i<this.tmpPoints.length; i++){                
                g.fillOval(
                    ((int)this.tmpPoints[i].getX()),
                    ((int)this.tmpPoints[i].getY()),
                    10, 10
                );
                this.colorManager.addColor(i);
            }
        }
        
    }

   
}
