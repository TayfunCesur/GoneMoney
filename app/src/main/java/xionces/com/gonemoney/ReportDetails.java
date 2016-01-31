package xionces.com.gonemoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by TayfunCESUR on 31.01.16.
 */
public class ReportDetails extends AppCompatActivity {

    List<Expense> expenseList;
    Detail detail = new Detail();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_detail);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        int a = b.getInt("pos");
        setTitle(Report.months[a] +" Report Details");

        expenseList = MainActivity.records;

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


        String x = ";";

    }
}
