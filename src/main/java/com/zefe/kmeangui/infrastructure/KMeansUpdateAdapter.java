package com.zefe.kmeangui.infrastructure;

import com.zefe.kmeangui.domain.model.ClusterResult;
import com.zefe.kmeangui.domain.port.IKmeansUpdatePort;

public class KMeansUpdateAdapter implements IKmeansUpdatePort {

    private final DataStore dataStore;

    public KMeansUpdateAdapter(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void saveResult(ClusterResult result) {
        this.dataStore.saveResult(result);
    }
}
