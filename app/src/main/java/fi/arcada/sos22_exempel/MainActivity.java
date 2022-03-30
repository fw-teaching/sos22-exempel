package fi.arcada.sos22_exempel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // deklarera variabler
    TextView textMean, textDataOut;
    EditText editTextName, editValue;
    RecyclerView recyclerView;

    // Vi skapar en arraylist för vår datamängd
    ArrayList<Double> dataset = new ArrayList<>();
    ArrayList<DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera variabler
        textMean = findViewById(R.id.textViewMean);
        textDataOut = findViewById(R.id.datasetOut);
        editTextName = findViewById(R.id.editTextName);
        editValue = findViewById(R.id.editValue);
        recyclerView = findViewById(R.id.datasetRecyclerView);

        // Un-commenta denna rad om du behöver ett test-dataset
        // dataItems = Statistics.getSampleDataset(); // ArrayList med testdata (flera DataItem-objekt)

        DatasetViewAdapter adapter = new DatasetViewAdapter(dataItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataset = Statistics.getDataValues(dataItems); // ArrayList med DataItem-objektens värden

        // Vi skriver tillfälligt ut vår datamängd
        String dataOut = "";
        // Vi skriver ut DataItem-datamängden
        for (DataItem item: dataItems) {
            dataOut += item.getName() + ":" + item.getValue() + " ";
        }
        textDataOut.setText(dataOut);

    }

    public void btnClick(View view) {
        // För att få den inmatade texten till en double måste vi konvertera med parseDouble()
        double value = Double.parseDouble(editValue.getText().toString());
        // Sedan skapar vi ett nytt DataIte-objekt och lägger till vår ArrayList
        dataItems.add(new DataItem(editTextName.getText().toString(), value));
    }

    public void calculate(View view) {

        dataset = Statistics.getDataValues(dataItems);

        // %.2f i String.format() avrundar till två decimaler
        String meanStr = String.format("Medelvärde: %.2f\n Median: %.2f\nStd.avvikelse: %.2f\nTypvärde: %.2f",
                Statistics.calcMean(dataset),
                Statistics.calcMedian(dataset),
                Statistics.calcSD(dataset),
                Statistics.calcMode(dataset)
        );

        textMean.setText(meanStr);
    }
}