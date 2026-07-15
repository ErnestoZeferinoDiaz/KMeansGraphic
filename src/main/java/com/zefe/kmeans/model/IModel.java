package com.zefe.kmeans.model;

import com.zefe.kmeans.algoritm.impl.DataKMeans;

public interface IModel {

    public DataKMeans addDataRandom(int numberPoints, int numberCentroids);
    public void setChangeRangeRandom(int minX, int maxX, int minY, int maxY);
    public void runKmeans(DataKMeans dataKMeans);
}
