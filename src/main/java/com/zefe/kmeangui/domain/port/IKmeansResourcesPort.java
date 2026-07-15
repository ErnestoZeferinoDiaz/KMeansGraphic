package com.zefe.kmeangui.domain.port;

import com.zefe.kmeangui.domain.model.PointSet;

public interface IKmeansResourcesPort {

    public PointSet getPoints();
    public PointSet getCentroids();

}
