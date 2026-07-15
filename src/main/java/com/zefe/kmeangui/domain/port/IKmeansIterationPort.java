package com.zefe.kmeangui.domain.port;

public interface IKmeansIterationPort {

    public void onIteration(int[] assignment, int[] centroidXs, int[] centroidYs, int centroidCount, int iteration);

    public void onCompleted(int iterations);

}
