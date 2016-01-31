package xionces.com.gonemoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
                        detail.totalexpense += detail.food_expense;
                        break;
                    case "Cigarette":
                        detail.cigarette_expense  += Integer.parseInt(expenseList.get(i).amount);
                        detail.totalexpense += detail.cigarette_expense;
                        break;
                    case "Alcohol":
                        detail.alcohol_expense  += Integer.parseInt(expenseList.get(i).amount);
                        detail.totalexpense += detail.alcohol_expense;
                        break;
                    case "Transportation":
                        detail.transportation_expense  += Integer.parseInt(expenseList.get(i).amount.trim());
                        detail.totalexpense += detail.transportation_expense;
                        break;
                    case "Invoice":
                        detail.invoice_expense  += Integer.parseInt(expenseList.get(i).amount);
                        detail.totalexpense += detail.invoice_expense;
                        break;
                    case "Home Rent":
                        detail.homerent_expense  += Integer.parseInt(expenseList.get(i).amount);
                        detail.totalexpense += detail.homerent_expense;
                        break;
                    case "Other":
                        detail.other_expense  += Integer.parseInt(expenseList.get(i).amount);
                        detail.totalexpense += detail.other_expense;
                        break;
                }

            }
            else
            {
                detail.totalincome+=Integer.parseInt(expenseList.get(i).amount);
            }
        }


        totalincome.setText("Toplam Gelen Para : " +detail.totalincome+" TL");
        totalexpense.setText("Toplam Harcama : "+detail.totalexpense+" TL");
        total_food_exp.setText("Yiyecek : "+ detail.food_expense+" TL");
        total_cig_exp.setText("Sigara : "+ detail.cigarette_expense+" TL");
        total_alch_exp.setText("Alkol : "+ detail.alcohol_expense+" TL");
        total_trans_exp.setText("Ulaşım : "+ detail.transportation_expense+" TL");
        total_homerent_exp.setText("Ev Kirası : "+ detail.homerent_expense+" TL");
        total_invoice_exp.setText("Faturalar : "+ detail.invoice_expense+" TL");
        total_other_exp.setText("Diğer : "+ detail.other_expense+" TL");

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




}
