package com.zefe.kmeans;

import com.zefe.kmeans.gui.Context;
import com.zefe.kmeans.model.impl.KMeansModel;


public class App {
    public static void main( String[] args ){
        KMeansModel kMeansModel = new KMeansModel();
        Context context = new Context(kMeansModel);

        context.init();








    }
}
