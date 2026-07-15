package com.zefe.kmeangui.domain.port;

import com.zefe.kmeangui.domain.model.ClusterResult;

public interface IKmeansUpdatePort {

    public void saveResult(ClusterResult result);

}
