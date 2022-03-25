package fi.arcada.sos22_exempel;

// POJO - Plain Old Java Object
public class DataItem {
    // Vi deklarerar attribut (instansvariablerna)
    double value;
    String name;

    public DataItem(String name, double value) {
        this.name = name;
        this.value = value;
    }

    // Getters
    public double getValue() {
        return value;
    }
    public String getName() {
        return name;
    }

    // Setter
    public void setValue(double value) {
        this.value = value;
    }
}
