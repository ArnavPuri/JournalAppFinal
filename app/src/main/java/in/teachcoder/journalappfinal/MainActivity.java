package in.teachcoder.journalappfinal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import in.teachcoder.journalappfinal.data.Constants;
import in.teachcoder.journalappfinal.data.DBMethods;

public class MainActivity extends AppCompatActivity {
    ListView entriesList;
    DBMethods myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entriesList = (ListView) findViewById(R.id.entries_list);
        myDB = new DBMethods(this);
        displayListView();

    }

    public void displayListView(){
        myDB.open();
        Cursor cursor = myDB.getEntries();
        String[] from = new String[]{
                Constants.HIGHLIGHT_NAME,
                Constants.DATE_NAME,
                Constants.CONTENT_NAME
        };

        int[] to = new int[]{
                R.id.entry_title,
                R.id.entry_date,
                R.id.entry_content
        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this,R.layout.row_layout,
                cursor, from,to,0);
        entriesList.setAdapter(adapter);
        myDB.close();
    }
}
