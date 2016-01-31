package xionces.com.gonemoney;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import java.util.Calendar;

/**
 * Created by TayfunCESUR on 30.01.16.
 */
public class Datainput extends AppCompatActivity {

    private int year, month, day;
    EditText desc,amount;
    Button datepicker;
    Switch isMinus;
    CircularProgressButton accept;
    int gelirgider;
    Spinner category;
    boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input);

        desc = (EditText) findViewById(R.id.description);
        amount = (EditText) findViewById(R.id.amount);
        datepicker = (Button) findViewById(R.id.datepicker);
        isMinus = (Switch) findViewById(R.id.switch1);
        accept = (CircularProgressButton) findViewById(R.id.circularButton1);
        accept.setIndeterminateProgressMode(true);
        category = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,
                new String[]{"Food","Cigarette","Alcohol","Transportation","Invoice","Home Rent","Other"});
        category.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        datepicker.setText(new StringBuilder().append(day).append("/")
                .append(month+1).append("/").append(year));

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMinus.isChecked()) gelirgider = 0;
                else    gelirgider = 1;
                accept.setProgress(50);
                if (!check)
                {
                    if (insertData(desc.getText().toString(),amount.getText().toString(),datepicker.getText().toString(),gelirgider,category.getSelectedItem().toString()))
                    {
                        accept.setProgress(100);
                        Toast.makeText(Datainput.this, "Input successful!", Toast.LENGTH_SHORT).show();
                        check = true;
                    }
                    else
                    {
                        accept.setProgress(-1);
                        Toast.makeText(getApplicationContext(),"Something went wrong :(",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    startActivity(new Intent(Datainput.this,MainActivity.class));
                }
            }
        });

    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            year = arg1;
            month = arg2+1;
            day = arg3;
            datepicker.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year));
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


    private boolean insertData(String desc,String amount,String date,int isMinus,String category)
    {
        try {
            SQLiteDatabase db = MainActivity.dbHelper.getWritableDatabase();
            ContentValues veriler = new ContentValues();
            veriler.put("Description",desc);
            veriler.put("Datetime",date);
            veriler.put("isMinus",isMinus);
            veriler.put("Amount", amount);
            veriler.put("Category",category);
            db.insertOrThrow("tblStatus", null, veriler);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

}
