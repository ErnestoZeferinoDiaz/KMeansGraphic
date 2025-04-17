package com.example.kmeans;

import com.example.controller.KEvent;
import com.example.view.VPlano;
import com.example.view.VPrincipal;

public class Main {

    public static void main(String[] args) {
        
        VPlano plano = new VPlano();
        VPrincipal principal = new VPrincipal();
        
        new KEvent(principal,plano);
        plano.setVisible(true);
        principal.setVisible(true);
        
        
        
    }
    
}
