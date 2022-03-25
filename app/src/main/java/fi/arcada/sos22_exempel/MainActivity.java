package fi.arcada.sos22_exempel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // deklarera variabler
    TextView textHello, textMean, textDataOut;
    EditText editTextName;
    // Vi skapar en arraylist för vår datamängd

    ArrayList<Double> dataset = new ArrayList<>();
    ArrayList<DataItem> dataItems = new ArrayList<>();


    // vi skapar en array med lite data att testa med
    double[] testdata = { 3.0, 5.2, 6.0, 4.0, 2.0, 4.3, 5.0, 7.0, 8.0, 7.3 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera variabler
        textHello = findViewById(R.id.helloText);
        textMean = findViewById(R.id.textViewMean);
        textDataOut = findViewById(R.id.datasetOut);
        editTextName = findViewById(R.id.editTextName);

        dataItems = Statistics.getSampleDataset();
        dataset = Statistics.getDataValues(dataItems);


        // Vi skriver tillfälligt ut vår datamängd
        String dataOut = "";
        for (double number: dataset) {
            dataOut += number + " ";
        }
        textDataOut.setText(dataOut);
        // Vi skriver ut DataItem-datamängden
        dataOut += "\n----\n";
        for (DataItem item: dataItems) {
            dataOut += item.getName() + ":" + item.getValue() + " ";
        }
        textDataOut.setText(dataOut);

        DataItem kalle = new DataItem("Kalle", 12);
        DataItem lisa = new DataItem("Lisa", 13);

    }

    public void btnClick(View view) {
        // Med String.format() kan vi kombinena text med värden av olika datatyp (%s = str, %d = digit)
        String helloName = String.format("Hello %s the number is %d",
            editTextName.getText(),
            5
        );

        textHello.setText(helloName);
    }

    public void calculate(View view) {

        // %.2f i String.format() avrundar till två decimaler
        String meanStr = String.format("Medelvärde: %.2f\n Median: %.2f\nStd.avvikelse: %.2f",
                Statistics.calcMean(dataset),
                Statistics.calcMedian(dataset),
                Statistics.calcSD(dataset)
        );

        textMean.setText(meanStr);
    }
}