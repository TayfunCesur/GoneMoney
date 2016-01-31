package xionces.com.gonemoney;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by TayfunCESUR on 31.01.16.
 */
public class ReportDetails extends AppCompatActivity {

    List<Expense> expenseList;
    Detail detail = new Detail();
    TextView totalincome,totalexpense,total_food_exp,total_cig_exp,total_alch_exp,total_trans_exp,total_homerent_exp,total_invoice_exp,total_other_exp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_detail);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        int a = b.getInt("pos");
        setTitle(Report.months[a] + " Report Details");

        init();

        for (int i = 0;i<MainActivity.records.size();i++)
        {
            if (Integer.parseInt(MainActivity.records.get(i).date.split("/")[1])-1 == a)
            {
                expenseList.add(MainActivity.records.get(i));
            }
        }

        for (int i = 0;i<expenseList.size();i++)
        {
            if (expenseList.get(i).isMinus)
            {
                switch (expenseList.get(i).category)
                {
                    case "Food":
                        detail.food_expense += Integer.parseInt(expenseList.get(i).amount);
                        break;
                    case "Cigarette":
                        detail.cigarette_expense  += Integer.parseInt(expenseList.get(i).amount);
                        break;
                    case "Alcohol":
                        detail.alcohol_expense  += Integer.parseInt(expenseList.get(i).amount);
                        break;
                    case "Transportation":
                        detail.transportation_expense  += Integer.parseInt(expenseList.get(i).amount.trim());
                        break;
                    case "Invoice":
                        detail.invoice_expense  += Integer.parseInt(expenseList.get(i).amount);
                        break;
                    case "Home Rent":
                        detail.homerent_expense  += Integer.parseInt(expenseList.get(i).amount);
                        break;
                    case "Other":
                        detail.other_expense  += Integer.parseInt(expenseList.get(i).amount);
                        break;
                }

            }
            else
            {
                detail.totalincome+=Integer.parseInt(expenseList.get(i).amount);
            }

            detail.totalexpense = detail.food_expense+detail.cigarette_expense+detail.alcohol_expense+detail.transportation_expense+detail.invoice_expense+detail.homerent_expense+detail.other_expense;
        }


        totalincome.setText("Total Income  : " +detail.totalincome+" TL");
        totalexpense.setText("Total Expense : "+detail.totalexpense+" TL");
        total_food_exp.setText("Food : "+ detail.food_expense+" TL");
        total_food_exp.setTextColor(getResources().getColor(R.color.food));

        total_cig_exp.setText("Cigarette : " + detail.cigarette_expense + " TL");
        total_cig_exp.setTextColor(getResources().getColor(R.color.cigarette));


        total_alch_exp.setText("Alcohol : " + detail.alcohol_expense + " TL");
        total_alch_exp.setTextColor(getResources().getColor(R.color.alcohol));

        total_trans_exp.setText("Transportation : " + detail.transportation_expense + " TL");
        total_trans_exp.setTextColor(getResources().getColor(R.color.transport));

        total_homerent_exp.setText("Home Rent : " + detail.homerent_expense + " TL");
        total_homerent_exp.setTextColor(getResources().getColor(R.color.homerent));

        total_invoice_exp.setText("Invoices : " + detail.invoice_expense + " TL");
        total_invoice_exp.setTextColor(getResources().getColor(R.color.invoice));

        total_other_exp.setText("Other : " + detail.other_expense + " TL");
        total_other_exp.setTextColor(getResources().getColor(R.color.other));
        generategraph();

    }

    private void init()
    {
        totalincome = (TextView) findViewById(R.id.totalincome);
        totalexpense = (TextView) findViewById(R.id.totalexpense);
        total_food_exp = (TextView) findViewById(R.id.textView2);
        total_cig_exp = (TextView) findViewById(R.id.textView3);
        total_alch_exp = (TextView) findViewById(R.id.textView4);
        total_trans_exp = (TextView) findViewById(R.id.textView5);
        total_homerent_exp = (TextView) findViewById(R.id.textView6);
        total_invoice_exp = (TextView) findViewById(R.id.textView7);
        total_other_exp = (TextView) findViewById(R.id.textView8);
        expenseList = new ArrayList<>();
    }

    private void generategraph()
    {
        PieGraph pg = (PieGraph)findViewById(R.id.graph);
        PieSlice slice = new PieSlice();
        slice.setColor(getResources().getColor(R.color.food));
        slice.setValue(detail.food_expense);
        if (detail.food_expense != 0)
        {
            slice.setIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.mipmap.food));
        }
        pg.addSlice(slice);


        slice = new PieSlice();
        slice.setColor(getResources().getColor(R.color.cigarette));
        slice.setValue(detail.cigarette_expense);
        if (detail.cigarette_expense != 0)
        {
            slice.setIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.mipmap.cigarette));
        }
        pg.addSlice(slice);


        slice = new PieSlice();
        slice.setColor(getResources().getColor(R.color.alcohol));
        slice.setValue(detail.alcohol_expense);
        if (detail.alcohol_expense != 0)
        {
            slice.setIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.mipmap.alcohol));
        }
        pg.addSlice(slice);

        slice = new PieSlice();
        slice.setColor(getResources().getColor(R.color.transport));
        slice.setValue(detail.transportation_expense);
        if (detail.transportation_expense != 0)
        {
            slice.setIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.mipmap.transport));
        }
        pg.addSlice(slice);

        slice = new PieSlice();
        slice.setColor(getResources().getColor(R.color.homerent));
        slice.setValue(detail.homerent_expense);
        if (detail.homerent_expense != 0)
        {
            slice.setIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.mipmap.homerent));
        }
        pg.addSlice(slice);

        slice = new PieSlice();
        slice.setColor(getResources().getColor(R.color.invoice));
        slice.setValue(detail.invoice_expense);
        if (detail.invoice_expense != 0)
        {
            slice.setIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.mipmap.invoice));
        }
        pg.addSlice(slice);

        slice = new PieSlice();
        slice.setColor(getResources().getColor(R.color.other));
        slice.setValue(detail.other_expense);
        if (detail.other_expense != 0)
        {
            slice.setIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),
                    R.mipmap.other));
        }
        pg.addSlice(slice);
        pg.setThickness(100);
    }

}
