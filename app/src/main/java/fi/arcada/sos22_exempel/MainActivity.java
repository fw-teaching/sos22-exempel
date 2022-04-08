package fi.arcada.sos22_exempel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // deklarera variabler
    TextView textOut;
    LineChart chart;

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
        LineData lineData = new LineData(lineDataSet);

        chart.setData(lineData);
        chart.invalidate(); // refresh

    }

}