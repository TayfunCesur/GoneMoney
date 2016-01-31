package xionces.com.gonemoney;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static DbHelper dbHelper;
    public static List<Expense> records;
    ListView listView;
    public String[] Columns = {"_id", "Description", "Datetime", "isMinus", "Amount" , "Category"};

    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DbHelper(this);
        records = getRecords();
        listView = (ListView) findViewById(R.id.listView);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


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

            showDialog(999);
        }
        if(id == R.id.reports)
        {
            startActivity(new Intent(MainActivity.this,Report.class));
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        records.clear();
        records = getRecords();
        ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(),records);
        listView.setAdapter(adapter);
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


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            year = arg1;
            month = arg2+1;
            day = arg3;
            String date = day+"/"+month+"/"+year;
            List<Expense> expenses = new ArrayList<>();
            for (int i = 0 ; i< records.size();i++)
            {
             if (records.get(i).date.equals(date))
             {
                 expenses.add(records.get(i));
             }

            }

            ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(),expenses);
            listView.setAdapter(adapter);

        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

}
