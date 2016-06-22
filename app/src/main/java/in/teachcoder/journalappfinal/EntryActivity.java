package in.teachcoder.journalappfinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import in.teachcoder.journalappfinal.data.Constants;
import in.teachcoder.journalappfinal.data.DBMethods;

public class EntryActivity extends AppCompatActivity {
    EditText title, mood, content;
    Button submitButton;
    DBMethods myDB;
    int id;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        title = (EditText) findViewById(R.id.entry_title);
        mood = (EditText) findViewById(R.id.entry_mood);
        content = (EditText) findViewById(R.id.entry_content);
        submitButton = (Button) findViewById(R.id.entry_submit);
        myDB = new DBMethods(this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });

        String comingFrom = getIntent().getStringExtra("coming_from");
        if (comingFrom == null) {
            comingFrom = " ";
        }

        if (comingFrom == "DetailActivity") {
            position = getIntent().getIntExtra("clicked_item", 0);
            SimpleCursorAdapter adapter = (SimpleCursorAdapter) MainActivity.entriesList.getAdapter();
            Cursor myCursor = adapter.getCursor();
            myCursor.moveToPosition(position);
            id = myCursor.getInt(myCursor.getColumnIndexOrThrow(Constants.KEY_ID));
            title.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.HIGHLIGHT_NAME)));
            content.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.CONTENT_NAME)));
            mood.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.MOOD_NAME)));
            submitButton.setText("Update Entry");
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    updateDB();
                }
            });
        }
    }

    private void saveToDB() {
        myDB.open();
        String entryTitle = title.getText().toString();
        String entryHighlight = mood.getText().toString();
        String entryContent = content.getText().toString();
        String category = "Fun";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        myDB.insertEntry(entryTitle, entryHighlight, entryContent, category, dateFormat.format(date));
        myDB.close();


        title.setText("");
        content.setText("");
        mood.setText("");
        Log.d("Database Updated", entryTitle + " added to db");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
