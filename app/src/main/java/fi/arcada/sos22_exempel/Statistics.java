package fi.arcada.sos22_exempel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Statistics {

    // Metod för att generera en datamängd att testa med
    public static ArrayList<DataItem> getSampleDataset() {
        Random rnd = new Random();

        ArrayList<DataItem> sampleData = new ArrayList<>();
        String[] names = { "Fili", "Kili", "Balin", "Dwalin", "Ori", "Nori", "Dori", "Gloin", "Oin", "Bifur", "Bofur", "Bombur", "Thorin" };
        double[] ages = { 268.0, 194.0, 364.0, 316.0, 328.0, 194.0, 316.0, 193.0, 298.0, 316.0, 161.0, 276.0, 230.0 };

        for (int i = 0; i < names.length; i++) {
            //sampleData.add(new DataItem(name, rnd.nextInt(300)+100));
            sampleData.add(new DataItem(names[i], ages[i]));
        }
        return sampleData;
    }



    // Metod för att skapa skild ArrayList med endast värdena från DataItems
    public static ArrayList<Double> getDataValues(ArrayList<DataItem> dataItems) {
        // Skapa ny arraylist för Double-värden
        ArrayList<Double> dataValues = new ArrayList<>();
        // Loopa igenom dataItems och spara endast värdena i den nya arrayListen
        for (DataItem item: dataItems) {
            dataValues.add(item.getValue());
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
