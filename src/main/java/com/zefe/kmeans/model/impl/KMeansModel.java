package com.zefe.kmeans.model.impl;

import com.zefe.kmeans.algoritm.impl.DataKMeans;
import com.zefe.kmeans.input.impl.RandomKMeans;
import com.zefe.kmeans.model.IModel;

public class KMeansModel implements IModel {

    @Override
    public DataKMeans addDataRandom(int numberPoints, int numberCentroids) {
        RandomKMeans randomKMeans = RandomKMeans.getInstance();
        randomKMeans.setNumberItems(numberPoints,numberCentroids);
        return randomKMeans.loadData();
    }

    @Override
    public void setChangeRangeRandom(int minX, int maxX, int minY, int maxY) {
        RandomKMeans randomKMeans = RandomKMeans.getInstance();
        randomKMeans.setRangeX(minX,maxX);
        randomKMeans.setRangeY(minY, maxY);
    }
}
