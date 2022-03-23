package fi.arcada.sos22_exempel;

public class Statistics {

    // Statisk metod som kan köras utan att skapa ett klassobjekt
    public static double calcMean(double[] dataset) {
        double sum = 0;
        for (int i = 0; i < dataset.length; i++) {
            sum += dataset[i];
        }

        return sum / dataset.length;
    }
}
