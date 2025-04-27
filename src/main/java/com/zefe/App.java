package com.zefe;


import com.zefe.input.impl.RandomKMeans;
import com.zefe.kmeans.impl.Centroid;
import com.zefe.kmeans.impl.KMeans;
import com.zefe.kmeans.impl.KMeansHistory;
import com.zefe.kmeans.impl.DataKMeans;
import com.zefe.kmeans.impl.EuclideanDistance;

public class App {
    public static void main( String[] args ){
        KMeansHistory history = KMeansHistory.getInstance();
        KMeans km = KMeans.getInstance();
        RandomKMeans randomKMeans = RandomKMeans.getInstance();
        DataKMeans dataBefore, dataAfter, dataTmp, dataOther;
        Centroid pointBefore, pointAfter;
        boolean isEqualsCentroids;
        double error,distanceTmp;
        error = 10;
        km.setCalculatorDistance(EuclideanDistance.getInstance());

        randomKMeans.setRangeX(100,1000);
        randomKMeans.setRangeY(100,1000);
        randomKMeans.setNumberItems(100000000,4);

        dataTmp = randomKMeans.loadData();
        history.save(dataTmp);

        long init = System.nanoTime();
        isEqualsCentroids = false;
        System.out.println(init);
        while(!isEqualsCentroids){
            dataAfter = new DataKMeans();
            dataAfter.setPoints(history.getCurrentStatus().getPoints());
            km.setData(history.getCurrentStatus());
            km.execute();
            dataAfter.setCentroids(km.getCentroidsUpdate());
            dataAfter.setRelationsPointsWithCentroid(km.getIndexCentroidsForEachPoint());
            history.save(dataAfter);
            km.clear();
            isEqualsCentroids = true;
            for(int i=0; i<history.getCurrentStatus().getCentroids().length; i++) {
                pointBefore = history.getBack().getCentroids()[i];
                pointAfter = history.getCurrentStatus().getCentroids()[i];
                distanceTmp = Math.abs(pointBefore.getX() - pointAfter.getX());
                isEqualsCentroids = isEqualsCentroids && (distanceTmp < error);
                distanceTmp = Math.abs(pointBefore.getY() - pointAfter.getY());
                isEqualsCentroids = isEqualsCentroids && (distanceTmp < error);
                System.out.println(distanceTmp);
            }
            System.out.println("-----------------------------------");
        }
        long fin = System.nanoTime();
        System.out.println(fin);
        System.out.println((fin-init)/1000000 + " ms");

    }
}
