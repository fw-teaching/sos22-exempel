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

    // vi skapar en array med lite data att testa med
    double[] testdata = { 3.0, 5.2, 6.0, 4.0, 2.0, 4.3, 5.0, 7.0, 8.0 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera variabler
        textHello = findViewById(R.id.helloText);
        textMean = findViewById(R.id.textViewMean);
        editTextName = findViewById(R.id.editTextName);

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
        String meanStr = String.format("mean: %.2f",
                Statistics.calcMean(testdata)
        );

        textMean.setText(meanStr);
    }
}