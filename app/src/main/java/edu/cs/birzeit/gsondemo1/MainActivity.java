package edu.cs.birzeit.gsondemo1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    public static final String DATA = "DATA";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private TextView txtResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupSharedPrefs();
        txtResults = findViewById(R.id.txtResults);
    }

    private void setupSharedPrefs() {
         prefs = PreferenceManager.getDefaultSharedPreferences(this);
         editor = prefs.edit();

    }

    public void btnSaveOnClick(View view) {
        Book[] books = new Book[3];
        books[0] = new Book("Java Core", "John");
        books[1] = new Book("C# in a Nutshell", "Mark");
        books[2] = new Book("Professional Android", "Yang");


        Gson gson = new Gson();
        String booksString = gson.toJson(books);

        editor.putString(DATA, booksString);
        editor.commit();

        txtResults.setText(booksString);

    }

    public void btnLoadOnClick(View view) {

        Gson gson = new Gson();
        String str = prefs.getString(DATA, "");
        if(!str.equals("")) {
            Book[] books = gson.fromJson(str, Book[].class);

            txtResults.setText("number of books:  " + books.length);
        }
        else{
            txtResults.setText("No data found");
        }
    }
}
