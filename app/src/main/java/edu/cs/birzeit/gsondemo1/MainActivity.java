package edu.cs.birzeit.gsondemo1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnSaveOnClick(View view) {
        Book[] books = new Book[2];
        books[0] = new Book("Java Core", "John");
        books[1] = new Book("C# in a Nutshell", "Mark");

       // Book book = new Book("Professional C#", "Samer");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String booksString = gson.toJson(books);

        editor.putString("123", booksString);
        editor.commit();
        Toast.makeText(this, "Data Saved:\n" + booksString,
                Toast.LENGTH_SHORT).show();


    }

    public void btnLoadOnClick(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String str = prefs.getString("123", "");
        Book[] books = gson.fromJson(str, Book[].class);

        Toast.makeText(this, "number of books" + books.length
                , Toast.LENGTH_SHORT).show();
    }
}
