package com.zefe.kmeans.input.event;

import com.zefe.kmeans.algoritm.impl.DataKMeans;

public interface IDataCreatedObserver {

    public void onCreatedData(DataKMeans dataCreated);
}
