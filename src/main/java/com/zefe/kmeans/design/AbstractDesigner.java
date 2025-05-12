package com.zefe.kmeans.design;

import com.zefe.kmeans.algoritm.impl.DataKMeans;

public abstract class AbstractDesigner implements IDesigner{
    private int width;
    private int heigth;
    private DataKMeans dataKMeans;
    private IDesigner iDesigner;
    private boolean isDrawn;

    public AbstractDesigner(){
        this.setDimention(100,100);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.heigth;
    }

    @Override
    public void setDimention(int width, int height) {
        this.isChangeSize(width,height);
        this.width = width;
        this.heigth = height;
    }

    public DataKMeans getDataDraw() {
        return this.dataKMeans;
    }

    public void setDataDraw(DataKMeans dataKmeans){
        this.dataKMeans = dataKmeans;
    }

    public IDesigner getNextDesigner(){
        return this.iDesigner;
    }

    public void setIDesigner(IDesigner iDesigner){
        this.iDesigner = iDesigner;
    }

    private void isChangeSize(int width, int height){
        this.isDrawn = !(this.getWidth() != width || this.getHeight() != height);
    }

    public boolean isDraw(){
        return this.isDrawn;
    }

    public void setDraw(boolean isDraw){
        this.isDrawn = isDraw;
    }


}
