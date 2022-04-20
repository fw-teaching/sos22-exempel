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

        ArrayList<ChartLine> chartLines = new ArrayList<>();
        chartLines.add(new ChartLine(temperatures, "Temperatur", Color.BLUE, 0));
        chartLines.add(new ChartLine(tempsSma, "SMA3", Color.GREEN, 3));
        chartLines.add(new ChartLine(tempsSma2, "SMA10", Color.RED, 10));

        createBetterMultilineGraph(chartLines);

    }

    /**
     * Bättre graf med riktiga klassobjekt som linjer!
     *
     * @param chartLines
     */
    public void createBetterMultilineGraph(ArrayList<ChartLine> chartLines) {
        List<ILineDataSet> dataSeries = new ArrayList<>();

        for (ChartLine chartLine: chartLines) {
            LineDataSet lineDataSet = new LineDataSet(chartLine.getEntries(), chartLine.getLabel());

            lineDataSet.setColor(chartLine.getColor());
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);
            dataSeries.add(lineDataSet);
        }

        LineData lineData = new LineData(dataSeries);
        chart.setData(lineData);
        chart.invalidate(); // refresh

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


}