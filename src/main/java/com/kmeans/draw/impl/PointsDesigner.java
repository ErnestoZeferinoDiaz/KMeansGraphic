package com.kmeans.draw.impl;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.Color;
import com.kmeans.draw.IDesigner;
import com.kmeans.draw.ColorManager;
import com.kmeans.algoritm.KMeansHistory;

public class PointsDesigner implements IDesigner{   
    private final ColorManager colorManager;
    private final KMeansHistory history;
    private Point2D[] tmpPoints;
    private int[] relation;
    private Color tmpColor;
    
    public PointsDesigner(KMeansHistory historyKM,ColorManager colorManager){
        this.colorManager = colorManager;
        this.history = historyKM;
    }

    @Override
    public void render(Graphics2D g) {
        
        this.tmpPoints = this.history.getCurrentStatus().getPoints();
        this.relation = this.history.getCurrentStatus().getRelationsPointsWithCentroid();

        if(this.tmpPoints != null){            
            for(int i=0; i<this.tmpPoints.length; i++){
                tmpColor = this.colorManager.getColorForCentroid(-1);
                if(this.relation != null){
                    if(i<this.relation.length){
                        tmpColor = this.colorManager.getColorForCentroid(
                            this.relation[i]
                        );                    
                    }
                }
                
                g.setColor(tmpColor);
                g.fillOval(
                    ((int)this.tmpPoints[i].getX()),
                    ((int)this.tmpPoints[i].getY()),
                    4, 4
                );
            }
        }
        
    }

   
}