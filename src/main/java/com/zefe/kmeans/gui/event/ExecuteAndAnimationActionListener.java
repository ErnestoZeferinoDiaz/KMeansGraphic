package com.zefe.kmeans.gui.event;

import com.zefe.kmeans.algoritm.event.IExecutedIterationObserver;
import com.zefe.kmeans.algoritm.impl.DataKMeans;
import com.zefe.kmeans.algoritm.impl.KMeans;
import com.zefe.kmeans.input.impl.Canva;
import com.zefe.kmeans.model.IModel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExecuteAndAnimationActionListener implements ActionListener, IExecutedIterationObserver {
    private final IModel iModel;
    private final Canva planeCanva;
    private Timer timer;
    private DataKMeans tmpDat;

    public ExecuteAndAnimationActionListener(Canva planeCanva, IModel iModel){
        this.iModel = iModel;
        this.planeCanva = planeCanva;
        KMeans.getInstance().addExecutedIterationObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iModel.runKmeans(tmpDat);
                planeCanva.setDataKMeans(tmpDat);
                planeCanva.repaint();
            }
        });
        this.timer.start();
    }

    @Override
    public void onIterationExecuted(DataKMeans dataBefore, DataKMeans dataAfter, int numberIteration) {
        this.tmpDat = dataAfter;
        System.out.println("Iteracion:"+numberIteration);
        if(dataBefore!=null){
            System.out.println(dataBefore.getCentroids()[0]);
        }
        if(dataAfter!=null){
            System.out.println(dataAfter.getCentroids()[0]);
        }

        System.out.println();
    }

    @Override
    public void onRelatedPoints(DataKMeans dataRelated, int numberIteration) {

    }

    @Override
    public void onIterationCompleted() {
        this.timer.stop();
        System.out.println("Stop pppppppppppppppppp");
    }
}
