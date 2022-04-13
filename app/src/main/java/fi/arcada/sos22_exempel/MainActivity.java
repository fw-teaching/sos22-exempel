package fi.arcada.sos22_exempel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Double[] dataOne = { -4.7, -4.8, -1.8,  0.7,  0.1, -6.0, -7.8, -7.0, -3.8, -10.6, -10.3, -0.3, 4.8, 2.6,  0.1, 1.2, -1.5, -2.7,  1.8,  0.2, -2.0, -5.5, -1.3,  2.1, -0.6, -0.9,  1.0, -0.5, -1.4, -1.6, -5.3 };
    Double[] dataTwo = {                   -2.0, -0.3, -1.7, -4.6, -6.9, -6.2, -7.1, -8.2, -7.1, -1.9,  2.4,  2.5, 1.3, -0.1, -1.0, -0.8, -0.2,  0.0, -2.4, -2.9, -1.6,  0.1,  0.2, -0.2, -0.1, -0.3, -1.2, -2.8 };

    // deklarera variabler
    LineChart chart;
    EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera view-variabler
        chart = (LineChart) findViewById(R.id.chart);

        ArrayList<Double> temperatures = Statistics.getDataValues();
        ArrayList<Double> tempsSma = Statistics.sma(temperatures, 3);

        System.out.println(temperatures);
        System.out.println(tempsSma);

        createSimpleGraph(temperatures);

    }


    public void createSimpleGraph(ArrayList<Double> dataSet) {
        List<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < dataSet.size(); i++) {
            entries.add(new Entry(i, dataSet.get(i).floatValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Temperatur");
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        LineData lineData = new LineData(lineDataSet);

        chart.setData(lineData);
        chart.invalidate(); // refresh

    }

    public void doubler(View view) {
        try {
            double inputNumber = Double.parseDouble(textInput.getText().toString());
            textInput.setText(String.format("%.2f", inputNumber*2));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Skriv ett tal!", Toast.LENGTH_LONG).show();
            // Kolla också in Snackbar
        } catch (Exception e) { // Exception tar emot alla slags errors
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}