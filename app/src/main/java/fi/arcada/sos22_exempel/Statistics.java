package fi.arcada.sos22_exempel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Statistics {


    // Metod för att skapa skild ArrayList med endast värdena från DataItems
    public static ArrayList<Double> getDataValues() {

        double[] temps = { -4.7, -4.8, -1.8, 0.7, 0.1, -6, -7.8, -7, -3.8, -10.6, -10.3, -0.3, 4.8, 2.6, 0.1, 1.2, -1.5, -2.7, 1.8, 0.2, -2, -5.5, -1.3, 2.1, -0.6, -0.9, 1, -0.5, -1.4, -1.6, -5.3, -7.7, -8.2, -9.5, -3.9, -0.4, 1, 0.8, -0.4, 0.6, 1, -1.5, -0.5, 1.4, 1.5, 1.8, 2, 1.1, -0.1, 0.1, -0.7, -0.4, -3, -6.8, 2, 1.5 };

        // Skapa ny arraylist för Double-värden
        ArrayList<Double> dataValues = new ArrayList<>();
        // Loopa igenom dataItems och spara endast värdena i den nya arrayListen
        for (double temp: temps) {
            dataValues.add(temp);
        }
        return dataValues;
    }

    // Sorteringsmetod att användas i andra metoder
    public static ArrayList<Double> getSorted(ArrayList<Double> dataset) {
        // Vi måste skapa en kopia av vår datamängd så vi inte sorterar den ursprungliga
        ArrayList<Double> sorted = new ArrayList<>(dataset);
        Collections.sort(sorted);
        return sorted;
    }

    // min
    public static double getMin(ArrayList<Double> dataLista) {
        // Första värdet i vår sorterade arrayList
        return getSorted(dataLista).get(0);
    }

    // max
    public static double getMax(ArrayList<Double> dataset) {
        // sista värdet i vår sorterade arrayList
        return getSorted(dataset).get(dataset.size()-1);
    }

    // Medelvärde
    public static double calcMean(ArrayList<Double> dataset) {
        double sum = 0;
        for (int i = 0; i < dataset.size(); i++) {
            sum += dataset.get(i);
        }
        return sum / dataset.size();
    }

    // Median
    public static double calcMedian(ArrayList<Double> dataset) {
        ArrayList<Double> sorted = getSorted(dataset);
        int mid = sorted.size() / 2;
        double median;
        if (sorted.size() % 2 == 0) {
            // Om antalet är jämnt, ta medelvärdet av de två mittersta
            median = (sorted.get(mid-1) + sorted.get(mid)) / 2;
        } else {
            // Om antalet är udda, ta det mittersta värdet
            median = sorted.get(mid);
        }
        return median;
    }

    // Standardavvikelse (Standard Deviation
    public static double calcSD(ArrayList<Double> dataset) {
        double sumDiff = 0;
        double avg = calcMean(dataset);

        // Loopa igenom datamängden
        for (double dataVal: dataset) {
            // Summan av de enskilda skillnaderna i kvadrat
            sumDiff += Math.pow(dataVal-avg,2);
        }
        // Dela summan med antalet värden (räkna ut variansen)
        double variance = sumDiff / dataset.size();
        // Till sist, ta roten av variansen och returnera
        return Math.sqrt(variance);

    }

    // Typvärde (eng. mode)
    public static double calcMode(ArrayList<Double> dataset) {
        HashMap<Double, Integer> valueCount = new HashMap<>();

        for (double dataValue: dataset) {
            Integer count = valueCount.get(dataValue);

            // Om vi inte tidigare räknat detta värde, sätt det till 0
            if (count == null)  count = 0;
            // Öka counten för detta värde med 1
            valueCount.put(dataValue, count+1);
            //valueCount.put(dataValue, (count == null ? 0 : count) + 1);

            /* Om vi tänker JSON skulle det se ut så här (fast vi ju inte får ha double som nycklar...)
                {   268.0: 1,  316.0, 3 ...
             */
        }
        int maxCount = 0;
        double modeValue = 0.0;

        // Enklast att loopa HashMap med keyset()
        for (Double dataValue: valueCount.keySet()) {
            Integer curCount = valueCount.get(dataValue);

            // Om det nuvarande värdet är högre än det senast funna högsta värdet
            if (curCount > maxCount) {
                maxCount = curCount;
                modeValue = dataValue;
            }
        }
        return modeValue;

    }
}
