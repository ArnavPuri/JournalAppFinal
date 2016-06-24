package in.teachcoder.journalappfinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.nio.channels.UnresolvedAddressException;

import in.teachcoder.journalappfinal.data.Constants;
import in.teachcoder.journalappfinal.data.DBMethods;

public class MainActivity extends AppCompatActivity {
    static ListView entriesList;
    DBMethods dbMethods; //MyDB mydb
    FloatingActionButton addEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entriesList = (ListView) findViewById(R.id.entries_list);
        addEntry = (FloatingActionButton) findViewById(R.id.add_entry_btn);
        dbMethods = new DBMethods(this);
        displayListView();

        entriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,DetailActivity.class);
                i.putExtra("clicked_item", position);
                startActivity(i);
            }
        });

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EntryActivity.class);
                startActivity(i);
            }
        });
    }

    public void displayListView() {
        Cursor cursor = dbMethods.getEntries();
        String[] from = new String[]{
                Constants.HIGHLIGHT_NAME,
                Constants.DATE_NAME,
                Constants.MOOD_NAME
        };
        int[] to = new int[]{
                R.id.entry_title,
                R.id.entry_date,
                R.id.entry_mood
        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this,
                R.layout.row_layout, cursor, from, to, 0);

        entriesList.setAdapter(adapter);
    }
}
