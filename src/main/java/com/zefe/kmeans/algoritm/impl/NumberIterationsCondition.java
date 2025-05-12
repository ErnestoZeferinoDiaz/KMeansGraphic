package com.zefe.kmeans.algoritm.impl;

import com.zefe.kmeans.algoritm.IConditionLoop;
import com.zefe.kmeans.algoritm.IPoint;

public class NumberIterationsCondition implements IConditionLoop {
    private int numberMaxIterations = 10;
    private boolean isCodition;

    public void setNumberMaxIterations(int numberMaxIterations){ this.numberMaxIterations = numberMaxIterations; }

    @Override
    public void calculateConditionLoop(IPoint[] centroidsBefore, IPoint[] centroidsAfter, int numberIteration) {
        this.isCodition = numberIteration < this.numberMaxIterations;
    }

    @Override
    public boolean isNextLoop() {
        return this.isCodition;
    }
}
