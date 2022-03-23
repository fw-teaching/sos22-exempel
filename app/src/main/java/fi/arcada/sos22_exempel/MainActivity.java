package fi.arcada.sos22_exempel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // deklarera variabler
    TextView textHello, textMean;
    EditText editTextName;

    double[] testdata = { 3.0, 5.2, 6.0, 4.0, 2.0, 4.3, 5.0, 7.0, 8.0 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera variabler
        textHello = findViewById(R.id.helloText);
        textMean = findViewById(R.id.textViewMean);
        editTextName = findViewById(R.id.editTextName);

        //textHello.setText("Hello from MainActivity!");
    }

    public void btnClick(View view) {
        //String name = editTextName.getText().toString();
        textHello.setText(
            String.format("Hello %s the number is %d",
                editTextName.getText(),
                5
            )
        );
    }

    public void calculate(View view) {

        String meanStr = String.format("mean: %.2f",
                Statistics.calcMean(testdata)
        );

        textMean.setText(meanStr);
    }
}