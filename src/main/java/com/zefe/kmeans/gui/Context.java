package com.zefe.kmeans.gui;

import com.zefe.kmeans.design.ColorManager;
import com.zefe.kmeans.design.impl.CentroidsDesigner;
import com.zefe.kmeans.design.impl.ColorRandomProvider;
import com.zefe.kmeans.design.impl.PlaneDesigner;
import com.zefe.kmeans.design.impl.PointsDesigner;
import com.zefe.kmeans.gui.event.AddDataActionListener;
import com.zefe.kmeans.gui.event.ExecuteAndAnimationActionListener;
import com.zefe.kmeans.gui.event.PlaneComponentListener;
import com.zefe.kmeans.input.impl.Canva;
import com.zefe.kmeans.model.IModel;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

public class Context {
    private DataFormInput dataFormInput;
    private JFrame planeFrame;
    private Canva planeDraw;
    private IModel iModel;

    public Context(IModel iModel){
        this.iModel = iModel;
    }

    public void init(){


        this.dataFormInput = this.getFormWindow();
        this.planeFrame = this.getPlaneWindow();
        this.planeDraw = this.getPlaneCanva();
        this.planeFrame.getContentPane().add(this.planeDraw);

        this.dataFormInput.btnAddData.addActionListener(new AddDataActionListener(this.dataFormInput,this.planeDraw, this.iModel));
        this.dataFormInput.btnCalculateAndRun.addActionListener(new ExecuteAndAnimationActionListener(this.planeDraw, this.iModel));
        this.planeDraw.addComponentListener(new PlaneComponentListener(this.planeDraw,this.iModel));

        this.dataFormInput.setVisible(true);
        this.planeFrame.setVisible(true);
    }

    public DataFormInput getFormWindow(){
        DataFormInput dataFormInput = new DataFormInput();
        dataFormInput.setTitle("Formulario");
        dataFormInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return dataFormInput;
    }

    public JFrame getPlaneWindow(){
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Plano");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setMinimumSize(new Dimension(250, 250));
        jFrame.setBackground(new Color(255,255,255));
        return jFrame;
    }

    public Canva getPlaneCanva(){
        Canva planeCanva = new Canva();
        PlaneDesigner planeDesigner = new PlaneDesigner();
        PointsDesigner pointsDesigner = new PointsDesigner(new ColorManager(new ColorRandomProvider()));
        CentroidsDesigner centroidsDesigner = new CentroidsDesigner();

        planeCanva.addIDesigner(planeDesigner);
        planeDesigner.setIDesigner(pointsDesigner);
        pointsDesigner.setIDesigner(centroidsDesigner);

        return planeCanva;
    }
}
