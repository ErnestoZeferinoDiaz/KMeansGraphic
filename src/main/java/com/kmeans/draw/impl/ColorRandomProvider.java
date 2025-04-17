package com.kmeans.draw.impl;

import com.kmeans.draw.IColorProvider;
import java.awt.Color;

public class ColorRandomProvider implements IColorProvider{

    public Color getColor(int index){
        return new Color(
            ((int) (Math.random()*(255))),
            ((int) (Math.random()*(255))),
            ((int) (Math.random()*(255)))
        );
    }

}