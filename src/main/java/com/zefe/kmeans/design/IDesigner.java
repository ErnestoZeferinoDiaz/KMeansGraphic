package com.zefe.kmeans.design;

import com.zefe.kmeans.algoritm.impl.DataKMeans;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public interface IDesigner {

    public BufferedImage render();
    public int getWidth();
    public int getHeight();
    public void setDimention(int width, int height);
    public DataKMeans getDataDraw();
    public void setDataDraw(DataKMeans dataKMeans);
    public IDesigner getNextDesigner();
}
