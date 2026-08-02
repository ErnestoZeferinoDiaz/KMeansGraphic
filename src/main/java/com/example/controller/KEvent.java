package com.example.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import com.example.model.KMeans;
import com.example.model.KMeansConfig;
import com.example.view.VPlano;
import com.example.view.VPrincipal;

public class KEvent extends KEventAdapter{
    public VPrincipal principal;
    public VPlano plano;
    private KMeans km;
    public KMeansConfig kmc;
    
    public KEvent(VPrincipal principal, VPlano plano) {
        this.principal = principal;
        this.plano = plano;

        this.principal.btn_calcular.addActionListener(this);
        this.principal.btn_init.addActionListener(this);
        this.principal.btn_add.addActionListener(this);
        
        this.principal.sld_sizeCentroid.addChangeListener(this);
        this.principal.sld_sizePoint.addChangeListener(this);

        this.plano.addComponentListener(this);
        
        this.plano.vpanel.addMouseListener(this);
        
        this.km = new KMeans();
        this.kmc = new KMeansConfig();
        this.km.kmc=this.kmc;
        
        this.initKMC();
        System.out.println(this.kmc.getRadioOfPoints());
        this.km.init();
        
        this.plano.vpanel.km=this.km;
        this.plano.vpanel.setKm(this.kmc);
        
        
    }
    
    
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource().equals(this.principal.btn_add)){
            if(this.principal.rbtn_centroid.isSelected()){
                this.km.addCentroidScreen(this.principal.txt_xc.getText(),this.principal.txt_yc.getText());
            }else{
                this.km.addPointScreen(this.principal.txt_xc.getText(),this.principal.txt_yc.getText());
            }
            //this.plano.vpanel.setKm(this.kmc);
            this.plano.vpanel.repaint();
            this.principal.txt_xc.setText("");
            this.principal.txt_yc.setText("");
        }else if(e.getSource().equals(this.principal.btn_init)){
            this.initKMC();
            this.km.init();
            this.plano.vpanel.repaint();
            
        }else if(e.getSource().equals(this.principal.btn_calcular)){
            this.initKMC();
            this.kmc.reset=false;
            Thread hilo = new Thread(this.plano.vpanel);
            hilo.start();            
        }
    }
    
    public void initKMC(){
        
        this.kmc.setRectanglePane(this.plano.getBounds());
        this.kmc.setRadioOfCentroid(this.principal.sld_sizeCentroid.getValue());
        this.kmc.setRadioOfPoints(this.principal.sld_sizePoint.getValue());
        this.kmc.setMinXRange(this.principal.txt_minX.getText());
        this.kmc.setMaxXRange(this.principal.txt_maxX.getText());
        this.kmc.setMinYRange(this.principal.txt_minY.getText());
        this.kmc.setMaxYRange(this.principal.txt_maxY.getText());
        this.kmc.setNumberOfStartingPoints(this.principal.txt_noPointsInit.getText());
        this.kmc.setMilliseconds(this.principal.txt_retardo.getText());
    }
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
        if(this.principal.rbtn_centroid.isSelected()){
            this.km.addCentroidPlane(e.getX(),e.getY());
        }else{
            this.km.addPointPlane(e.getX(),e.getY());
        }
        //this.plano.vpanel.setKm(this.kmc);
        this.plano.vpanel.repaint();
    }

    public void componentResized(ComponentEvent e) {
        super.componentResized(e); //To change body of generated methods, choose Tools | Templates.
        this.plano.vpanel.setBounds(0,0,this.plano.getWidth(),this.plano.getHeight());
        kmc.setRectanglePane(this.plano.getBounds());
    }
    
    public void stateChanged(ChangeEvent e) {
        super.stateChanged(e); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource().equals(this.principal.sld_sizeCentroid)){
            this.kmc.setRadioOfCentroid(this.principal.sld_sizeCentroid.getValue());
            //this.plano.vpanel.setKm(kmc);
            this.plano.vpanel.repaint();
        }else if(e.getSource().equals(this.principal.sld_sizePoint)){
            this.kmc.setRadioOfPoints(this.principal.sld_sizePoint.getValue());
            //this.plano.vpanel.setKm(kmc);
            this.plano.vpanel.repaint();
        }
    }

}
