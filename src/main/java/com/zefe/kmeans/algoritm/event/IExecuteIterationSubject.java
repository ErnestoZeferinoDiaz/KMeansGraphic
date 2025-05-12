package com.zefe.kmeans.algoritm.event;

import com.zefe.kmeans.algoritm.impl.DataKMeans;

public interface IExecuteIterationSubject {

    public void addExecutedIterationObserver(IExecutedIterationObserver observer);
    public void removeExecutedIterationObserver(IExecutedIterationObserver observer);
    public void notifyIterationExecutedObservers(DataKMeans dataBefore, DataKMeans dataAfter, int numberIteration);
    public void notifyRelatedPointsObservers(DataKMeans dataRelated, int numberIteration);
    public void notifyIterationCompletedObservers();
}
