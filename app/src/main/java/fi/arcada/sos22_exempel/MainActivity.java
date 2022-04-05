package fi.arcada.sos22_exempel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // deklarera variabler
    TextView textOut, textView2;
    int launchCount;

    // Deklarera Objekt för preferences
    SharedPreferences sharedPref;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera view-variabler
        textOut = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        // Initialisera sharedPref med this-context (så den vet vilken view den hör till)
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // Hämta ut det gamla värdet, addera 1, och spara igen
        prefEditor = sharedPref.edit();
        prefEditor.putInt("launchCount", sharedPref.getInt("launchCount", 0)+1);
        prefEditor.apply();
        // Motsvarande metoder för andra datatyper: getString()/putString() getBoolean()/putBoolean()

        // Hämta ut värdet
        launchCount = sharedPref.getInt("launchCount", 0);

        textOut.setText(String.format("Appen startad %d gånger.", launchCount));


        textView2.setText(String.format("Välkommen tillbaka %s", "Stranger"));

    }


}