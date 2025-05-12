package com.zefe.kmeans.algoritm;

public interface IConditionLoop {

    public void calculateConditionLoop(IPoint[] centroidsBefore, IPoint[] centroidsAfter, int numberIteration);
    public boolean isNextLoop();
}
