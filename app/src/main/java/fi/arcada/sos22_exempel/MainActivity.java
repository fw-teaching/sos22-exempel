package fi.arcada.sos22_exempel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // deklarera variabler
    TextView textHello;
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera variabler
        textHello = findViewById(R.id.helloText);
        editTextName = findViewById(R.id.editTextName);

        //textHello.setText("Hello from MainActivity!");
    }

    public void btnClick(View view) {
        //String name = editTextName.getText().toString();
        textHello.setText("Hello " + editTextName.getText());
    }

}