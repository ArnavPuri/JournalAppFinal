package in.teachcoder.journalappfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import in.teachcoder.journalappfinal.data.DBMethods;

public class MainActivity extends AppCompatActivity {
    ListView entriesList;
    DBMethods dbMethods; //MyDB mydb
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entriesList = (ListView) findViewById(R.id.entries_list);
        dbMethods = new DBMethods(this);

    }
}
