package com.kmeans.input.impl;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;
import java.util.ArrayList;
import com.kmeans.draw.IDesigner;
import com.kmeans.draw.ColorManager;
import com.kmeans.algoritm.KMeansData;

public class Canva extends JPanel implements ComponentListener{
    private final List<IDesigner> iDesigners = new ArrayList();
    
    public Canva(){
        this.setBackground(new Color(0,0,0));
        this.setLayout(new BorderLayout());
    }

    public void addIDesigner(IDesigner iDesigner){
        this.iDesigners.add(iDesigner);
    }

    public void paint(Graphics g){
        super.paint(g);
        for(IDesigner iDesigner : this.iDesigners){
            iDesigner.render((Graphics2D) g);
        }        
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}
}