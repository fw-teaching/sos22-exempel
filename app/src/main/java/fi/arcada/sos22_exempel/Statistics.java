package fi.arcada.sos22_exempel;

import java.util.ArrayList;
import java.util.Collections;

public class Statistics {

    // Sorteringsmetod att användas i andra metoder
    public static ArrayList<Double> getSorted(ArrayList<Double> dataset) {
        // Vi måste skapa en kopia av vår datamängd så vi inte sorterar den ursprungliga
        ArrayList<Double> sorted = new ArrayList<>(dataset);
        Collections.sort(sorted);
        return sorted;
    }

    // Statisk metod som kan köras utan att skapa ett klassobjekt
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
            median = sorted.get(mid-1) + sorted.get(mid) / 2;
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

        return Math.sqrt(sumDiff / dataset.size());

    }
}
