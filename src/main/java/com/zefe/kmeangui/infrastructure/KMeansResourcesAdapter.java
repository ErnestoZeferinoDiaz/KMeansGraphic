package com.zefe.kmeangui.infrastructure;

import com.zefe.kmeangui.domain.model.PointSet;
import com.zefe.kmeangui.domain.port.IKmeansResourcesPort;

public class KMeansResourcesAdapter implements IKmeansResourcesPort {

    private final DataStore dataStore;

    public KMeansResourcesAdapter(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public PointSet getPoints() {
        return this.dataStore.getPointsView();
    }

    @Override
    public PointSet getCentroids() {
        return this.dataStore.getCentroidsView();
    }
}
