package com.zefe.kmeans.design.impl;

import com.zefe.kmeans.design.IColorProvider;
import java.awt.Color;

public class ColorRandomProvider implements IColorProvider {

    public Color getColor(int index){
        return new Color(
                ((int) (Math.random()*(255))),
                ((int) (Math.random()*(255))),
                ((int) (Math.random()*(255)))
        );
    }

}