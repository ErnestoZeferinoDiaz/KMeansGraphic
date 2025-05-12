package com.zefe.kmeans.algoritm.impl;

import com.zefe.kmeans.algoritm.event.IExecuteIterationSubject;
import com.zefe.kmeans.algoritm.event.IExecutedIterationObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class KMeansSubject implements IExecuteIterationSubject {
    private final List<IExecutedIterationObserver> executedIterationObservers = new ArrayList<>();

    @Override
    public void addExecutedIterationObserver(IExecutedIterationObserver observer) {
        this.executedIterationObservers.add(observer);
    }

    @Override
    public void removeExecutedIterationObserver(IExecutedIterationObserver observer) {
        this.executedIterationObservers.remove(observer);
    }

    @Override
    public void notifyIterationExecutedObservers(DataKMeans dataBefore, DataKMeans dataAfter, int numberIteration) {
        for (IExecutedIterationObserver observer : this.executedIterationObservers) {
            observer.onIterationExecuted(dataBefore, dataAfter, numberIteration);
        }
    }

    @Override
    public void notifyRelatedPointsObservers(DataKMeans dataRelated, int numberIteration) {
        for (IExecutedIterationObserver observer : this.executedIterationObservers) {
            observer.onRelatedPoints(dataRelated, numberIteration);
        }
    }

    @Override
    public void notifyIterationCompletedObservers() {
        for (IExecutedIterationObserver observer : this.executedIterationObservers) {
            observer.onIterationCompleted();
        }
    }


}
