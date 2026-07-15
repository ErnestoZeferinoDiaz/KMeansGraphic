package com.zefe.kmeans.design;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorManager {
    private final Map<Integer, Color> colorForEachCentroid = new HashMap<>();
    private final IColorProvider colorProvider;
    private final Color defaultColor = Color.WHITE;

    public ColorManager(IColorProvider colorProvider){
        this.colorProvider = colorProvider;
    }

    public void addColor(int indexCentroid){
        if(!this.colorForEachCentroid.containsKey(indexCentroid)){
            this.colorForEachCentroid.put(indexCentroid,this.colorProvider.getColor(indexCentroid));
        }
    }

    public Color getColorForCentroid(int indexCentroid) {
        if(this.colorForEachCentroid.containsKey(indexCentroid)){
            return this.colorForEachCentroid.get(indexCentroid);
        }
        return this.defaultColor;
    }

    public void reset() {
        this.colorForEachCentroid.clear();
    }
}