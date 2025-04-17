package com.kmeans.draw.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import com.kmeans.draw.IDesigner;
import javax.swing.JFrame;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


public class PlaneDesigner implements IDesigner,ComponentListener {
    private int width;
    private int heigth;
    
    @Override
    public void render(Graphics2D g) {
        int sGui=5;

        g.setColor(Color.DARK_GRAY);
        for(int x=0; x<this.width; x=x+20){
            g.drawLine(x, 0, x, this.heigth);
        }
        for(int y=0; y<this.heigth; y=y+20){
            g.drawLine(0, y,this.width, y);
        }

        g.setColor(Color.WHITE);
        g.drawLine(0, this.heigth/2, this.width, this.heigth/2);
        g.drawLine(this.width/2, 0, this.width/2, this.heigth);

        for(int x=0; x<this.width; x=x+10){
            g.drawLine(x, this.heigth/2-sGui, x, this.heigth/2+sGui);
        }
        for(int y=0; y<this.heigth; y=y+10){
            g.drawLine(this.width/2-sGui, y,this.width/2+sGui, y);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        JFrame frame = (JFrame) e.getComponent();
        this.width = frame.getWidth();
        this.heigth = frame.getHeight();
    }

    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}


}
