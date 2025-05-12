package com.zefe.kmeans.algoritm.event;


import com.zefe.kmeans.algoritm.impl.DataKMeans;

public interface IExecutedIterationObserver {

    public void onIterationExecuted(DataKMeans dataBefore, DataKMeans dataAfter, int numberIteration);
    public void onRelatedPoints(DataKMeans dataRelated, int numberIteration);
    public void onIterationCompleted();

}
