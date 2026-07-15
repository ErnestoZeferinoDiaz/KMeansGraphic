package com.zefe.kmeans.model.impl;

import com.zefe.kmeans.algoritm.event.IExecutedIterationObserver;
import com.zefe.kmeans.algoritm.impl.CompareCentroidsCondition;
import com.zefe.kmeans.algoritm.impl.DataKMeans;
import com.zefe.kmeans.algoritm.impl.EuclideanDistance;
import com.zefe.kmeans.algoritm.impl.KMeans;
import com.zefe.kmeans.input.impl.RandomKMeans;
import com.zefe.kmeans.model.IModel;

public class KMeansModel implements IModel{
    private DataKMeans dataTmp;
    private KMeans kMeans;

    public KMeansModel(){
        this.kMeans = KMeans.getInstance();
        this.kMeans.setCalculatorDistance(EuclideanDistance.getInstance());
        this.kMeans.setConditionLoop(new CompareCentroidsCondition(0.1));
    }

    @Override
    public DataKMeans addDataRandom(int numberPoints, int numberCentroids) {
        RandomKMeans randomKMeans = RandomKMeans.getInstance();
        randomKMeans.setNumberItems(numberPoints,numberCentroids);
        this.dataTmp = randomKMeans.loadData();
        this.kMeans.setData(dataTmp);
        return dataTmp;
    }

    @Override
    public void setChangeRangeRandom(int minX, int maxX, int minY, int maxY) {
        RandomKMeans randomKMeans = RandomKMeans.getInstance();
        randomKMeans.setRangeX(minX,maxX);
        randomKMeans.setRangeY(minY, maxY);
    }

    @Override
    public void runKmeans(DataKMeans dataTmp2) {
        this.kMeans.setData(dataTmp2);
        this.kMeans.oneLoop(0);
    }

}
