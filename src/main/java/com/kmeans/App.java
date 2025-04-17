package com.kmeans;

import java.awt.geom.Point2D;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import com.kmeans.algoritm.Kmeans;
import com.kmeans.algoritm.KMeansHistory;
import com.kmeans.algoritm.KMeansData;
import com.kmeans.input.impl.RandomKMeans;
import com.kmeans.input.impl.Canva;
import com.kmeans.input.impl.DataFormInput;
import com.kmeans.draw.ColorManager;
import com.kmeans.draw.impl.PlaneDesigner;
import com.kmeans.draw.impl.PointsDesigner;
import com.kmeans.draw.impl.CentroidsDesigner;
import com.kmeans.draw.impl.LinesDesigner;
import com.kmeans.draw.impl.ColorRandomProvider;
import com.kmeans.event.impl.ExecuteActionListener;
import com.kmeans.event.impl.BackActionListener;
import com.kmeans.event.impl.NextActionListener;
import com.kmeans.event.impl.AddDataActionListener;
import com.kmeans.event.impl.ClearActionListener;
import com.kmeans.event.impl.DeleteHistoryActionListener;
import com.kmeans.event.impl.AnimationActionListener;
import com.kmeans.event.impl.CalculateAndRunActionListener;

public class App{
    public static void main( String[] args ){
        final ColorManager colorManager = new ColorManager(new ColorRandomProvider());
        final Kmeans km = new Kmeans();
        final KMeansHistory historyKM = new KMeansHistory();
        
        final DataFormInput dataFormInput = new DataFormInput();
        final JFrame planeFrame = new JFrame();
        final Canva planePane = new Canva();
        final PlaneDesigner planeDesigner = new PlaneDesigner();
        
        final PointsDesigner pointsDesigner = new PointsDesigner(historyKM,colorManager);
        final CentroidsDesigner centroidsDesigner= new CentroidsDesigner(historyKM,colorManager);        
        final LinesDesigner linesDesigner= new LinesDesigner(historyKM,colorManager);        

        final CalculateAndRunActionListener calculateAndRunActionListener = new CalculateAndRunActionListener();
        final ExecuteActionListener executeActionListener = new ExecuteActionListener(dataFormInput,historyKM,km);
        final AnimationActionListener animationActionListener = new AnimationActionListener(planePane,historyKM);

        calculateAndRunActionListener.addListeners(executeActionListener);
        calculateAndRunActionListener.addListeners(animationActionListener);

        dataFormInput.btnCalculate.addActionListener(executeActionListener);
        dataFormInput.btnBack.addActionListener(new BackActionListener(planePane,historyKM));
        dataFormInput.btnNext.addActionListener(new NextActionListener(planePane,historyKM));
        dataFormInput.btnAddData.addActionListener(new AddDataActionListener(dataFormInput,planePane,historyKM));
        dataFormInput.btnClear.addActionListener(new ClearActionListener(planePane,historyKM));
        dataFormInput.btnDeleteHistory.addActionListener(new DeleteHistoryActionListener(planePane,historyKM));
        dataFormInput.btnRunAnimation.addActionListener(animationActionListener);
        dataFormInput.btnCalculateAndRun.addActionListener(calculateAndRunActionListener);

        planeFrame.addComponentListener(planeDesigner);
        planeFrame.addComponentListener(planePane);        
        
        planeFrame.setTitle("Plano");
        planeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        planeFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        planeFrame.setMinimumSize(new Dimension(250, 250));
        planeFrame.setBackground(new Color(255,255,255));
        planeFrame.getContentPane().add(planePane);
        
        planePane.addIDesigner(planeDesigner);
        planePane.addIDesigner(centroidsDesigner);
        planePane.addIDesigner(pointsDesigner);
        planePane.addIDesigner(linesDesigner);

        historyKM.save(new KMeansData());

        planeFrame.setVisible(true);
        dataFormInput.setVisible(true);
    }
}
