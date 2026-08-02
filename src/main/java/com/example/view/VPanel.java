package com.example.view;

import com.example.controller.KEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import com.example.model.Circle;
import com.example.model.KMeans;
import com.example.model.KMeansConfig;

public class VPanel extends JPanel implements Runnable{
    private KMeansConfig kmconf;
    public KMeans km;
    public VPanel(){
        
        //this.setBackground(Color.WHITE);
        this.setBackground(Color.BLACK);
        
    }
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D)g;
        this.drawPlano(g2);
        
        if(!this.kmconf.reset){
            this.drawLines(g2);
            this.drawPoints(g2);
            this.drawCentroids(g2);
        }else{            
            this.drawPoints(g2);
            this.drawCentroids(g2);
        }        
    }
    
    public void drawPlano(Graphics2D g){
        int w,h;
        int sGui=5;
        w=this.getWidth();
        h=this.getHeight();
        
        //g.setColor(Color.LIGHT_GRAY);
        g.setColor(Color.DARK_GRAY);
        for(int x=0; x<w; x=x+20){
            g.drawLine(x, 0, x, h);
        }
        for(int y=0; y<h; y=y+20){
            g.drawLine(0, y,w, y);
        }
        //g.setColor(Color.BLACK);
        g.setColor(Color.WHITE);
        g.drawLine(0, h/2, w, h/2);
        g.drawLine(w/2, 0, w/2, h);
        
        for(int x=0; x<w; x=x+10){
            g.drawLine(x, h/2-sGui, x, h/2+sGui);
        }
        for(int y=0; y<h; y=y+10){
            g.drawLine(w/2-sGui, y,w/2+sGui, y);
        }
        
    }
    
    public void drawCentroids(Graphics2D g){
        LinkedList<Circle> l;
        l=this.kmconf.centroids;
        g.setColor(Color.red);
        for(int x=0; x<l.size(); x++){
            g.fill(l.get(x).getCircle());
        }
    }
    
    public void drawPoints(Graphics2D g){
        LinkedList<Circle> l;
        int i;
        l=this.kmconf.points;
        
        for(int x=0; x<l.size(); x++){
            if(l.get(x).getLabel()==-1){
                g.setColor(Color.WHITE);
            }else{
                g.setColor(this.kmconf.colors.get(l.get(x).getLabel()));
            }
            g.fill(l.get(x).getCircle());
        }
    }

    public void drawLines(Graphics2D g){
        LinkedList<Circle> l,m;
        int i;
        
        l=this.kmconf.points;
        m=this.kmconf.centroids;
        
        for(int x=0; x<l.size(); x++){
            i=l.get(x).getLabel();
            if(i!=-1){
                g.setColor(this.kmconf.colors.get(i));
                g.draw(new Line2D.Double(l.get(x).getXScreen(),l.get(x).getYScreen(),m.get(i).getXScreen(),m.get(i).getYScreen()));
            }
            
        }
    }

    public void setKm(KMeansConfig kmconf) {
        this.kmconf = kmconf;
    }
    
    
    @Override
    public void run() {
        for(int x=0; x<10; x++){
            this.km.run();
            this.repaint();
            
            try {
                Thread.sleep(this.kmconf.getMilliseconds());
            } catch (InterruptedException ex) {
                Logger.getLogger(KEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    
}
 