package fi.arcada.sos22_exempel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

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
        ArrayList<Double> tempsSma2 = Statistics.sma(temperatures, 10);

        // Arraylist som innehåller arraylists
        ArrayList<ArrayList<Double>> dataSets = new ArrayList<>();

        dataSets.add(temperatures);
        dataSets.add(tempsSma);
        dataSets.add(tempsSma2);


        createMultilineGraph(dataSets);

    }

    public void createMultilineGraph(ArrayList<ArrayList<Double>> dataSets) {
        List<ILineDataSet> dataSeries = new ArrayList<>();
        List<Entry> entries;
        //int entryOffset =  0; // var på X ska linjen börja

        String[] labels = { "Temperatur", "SMA", "SMA2", "SMA3" };
        int[] colors = { Color.BLUE, Color.RED, Color.GREEN, Color.BLACK };

        LineDataSet lineDataSet;

        for (int i = 0; i < dataSets.size(); i++) {
            // Det dataset vi nu loopar
            ArrayList<Double> currentDataSet = dataSets.get(i);
            int entryOffset = dataSets.get(0).size() - currentDataSet.size();

            entries = new ArrayList<Entry>();
            for (int j = 0; j < currentDataSet.size(); j++) {
                // OBS i = yttre loopen, j = inre loopen
                entries.add(new Entry(j+entryOffset, currentDataSet.get(j).floatValue()));
            }

            lineDataSet = new LineDataSet(entries, labels[i]);
            lineDataSet.setColor(colors[i]);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            dataSeries.add(lineDataSet);
        }

        LineData lineData = new LineData(dataSeries);
        chart.setData(lineData);
        chart.invalidate(); // refresh
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