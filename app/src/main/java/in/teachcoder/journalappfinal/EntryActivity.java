package in.teachcoder.journalappfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import in.teachcoder.journalappfinal.data.DBMethods;

public class EntryActivity extends AppCompatActivity {
    EditText highlight, mood, content;
    CheckBox funCb, expensiveCb;
    Button save;
    DBMethods dbMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        initializeViews();
        dbMethods = new DBMethods(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });
    }

    private void initializeViews() {
        highlight = (EditText) findViewById(R.id.entry_title);
        mood = (EditText) findViewById(R.id.entry_mood);
        content = (EditText) findViewById(R.id.entry_content);
        funCb = (CheckBox) findViewById(R.id.entry_fun_cb);
        expensiveCb = (CheckBox) findViewById(R.id.entry_expensive_cb);
        save = (Button) findViewById(R.id.entry_submit);
    }

    public void saveToDB() {
        dbMethods.open();
        String entryHighlight = highlight.getText().toString();
        String entryMood = mood.getText().toString();
        String entryContent = content.getText().toString();
        String entryCategory = " ";

        if (funCb.isChecked() && !expensiveCb.isChecked()){
             entryCategory = funCb.getText().toString();
        }else if (!funCb.isChecked() && expensiveCb.isChecked()){
             entryCategory = expensiveCb.getText().toString();
        }else {
             entryCategory = funCb.getText().toString()+ ", " + expensiveCb.getText().toString();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        dbMethods.insertEntry(entryHighlight,entryMood,entryContent,entryCategory, sdf.format(date));
        dbMethods.close();
        Log.d("EntryActivity", "Entry added to DB " + entryHighlight);


    }

}
