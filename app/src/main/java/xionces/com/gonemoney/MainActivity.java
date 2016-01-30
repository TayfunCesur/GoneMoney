package xionces.com.gonemoney;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static DbHelper dbHelper;
    private List<Expense> records;
    ListView listView;
    public String[] Columns = {"_id", "Description", "Datetime", "isMinus", "Amount" , "Category"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DbHelper(this);
        records = getRecords();
        listView = (ListView) findViewById(R.id.listView);

        ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(),records);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, Datainput.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private List<Expense> getRecords()
    {
        List<Expense> list = new ArrayList<>();

        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("tblStatus", Columns, null, null, null, null, null);
            startManagingCursor(cursor);

            while (cursor.moveToNext()) {

                Expense expense = new Expense();

                expense.description = cursor.getString(cursor.getColumnIndex("Description"));
                expense.date = cursor.getString(cursor.getColumnIndex("Datetime"));
                if (cursor.getInt(cursor.getColumnIndex("isMinus")) == 0)
                {
                    expense.isMinus = false;
                }
                else
                {
                    expense.isMinus = true;
                }
                expense.amount = cursor.getString(cursor.getColumnIndex("Amount"));
                expense.category = cursor.getString(cursor.getColumnIndex("Category"));
                list.add(expense);
            }
        }
        catch (Exception e){

        }
        return list;
    }

}
