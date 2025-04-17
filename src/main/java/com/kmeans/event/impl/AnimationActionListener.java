package com.kmeans.event.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.kmeans.algoritm.KMeansHistory;

public class AnimationActionListener implements ActionListener {
    private final KMeansHistory historyKM;    
    private final JPanel jpanel; 

    public AnimationActionListener(JPanel jpanel, KMeansHistory historyKM){
        this.historyKM = historyKM;
        this.jpanel = jpanel;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Timer timer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {                
                historyKM.next();
                jpanel.repaint();
                if (!historyKM.isNext()) {
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Animacion Terminada");
                }
            }
        });
        timer.start();
    }
}