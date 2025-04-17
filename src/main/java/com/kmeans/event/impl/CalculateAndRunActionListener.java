package com.kmeans.event.impl;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class CalculateAndRunActionListener implements ActionListener {
    private final List<ActionListener> listeners = new ArrayList();

    public void addListeners(ActionListener actionListener){
        this.listeners.add(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e){        
        for(ActionListener actionListener : this.listeners){
            actionListener.actionPerformed(e);
        }        
    }
}