package com.zefe.kmeans.gui.event;

import com.zefe.kmeans.gui.DataFormInput;
import com.zefe.kmeans.input.impl.Canva;
import com.zefe.kmeans.model.IModel;

import javax.swing.JComponent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class PlaneComponentListener implements ComponentListener {
    private final IModel iModel;
    private final Canva planeCanva;

    public PlaneComponentListener(Canva planeCanva, IModel iModel){
        this.iModel = iModel;
        this.planeCanva = planeCanva;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        JComponent jComponent = (JComponent) e.getSource();
        this.iModel.setChangeRangeRandom(
            20,jComponent.getWidth()-20,
            20,jComponent.getHeight()-20
        );
        this.planeCanva.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
}
