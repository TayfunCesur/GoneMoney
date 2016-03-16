package xionces.com.gonemoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TayfunCESUR on 31.01.16.
 */
public class Report extends AppCompatActivity{

    ListView list;
    List<Expense> recordlist;
    List<String> newList;
    public static String[] months = new String[]{"January","February","March","April","May","June","July","August","September","October","November","December"};
    private List<String> different_months = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports);

        setTitle("Pick a Month");

        list = (ListView) findViewById(R.id.listView2);
        recordlist = MainActivity.records;
        newList = new ArrayList<>();
        int z = 0;


        for (int i = 0 ; i<recordlist.size();i++)
        {
            newList.add(recordlist.get(i).date.split("/")[1]);
        }




        if (newList.size() > 1 ) {

            for (int i = 0;i<newList.size();i++)
            {
                int k = 0;
                for (int j = i+1 ;j<newList.size() -1; j++)
                {
                    if (newList.get(i).equals(newList.get(j)))
                    {
                        k++;
                    }
                }
                if (k == 0) different_months.add(months[Integer.parseInt(newList.get(i)) - 1]);
            }
        } else if (newList.size() != 0){
            different_months.add(months[Integer.parseInt(newList.get(0)) - 1]);
        }

        ReportListAdapter adapter = new ReportListAdapter(getApplicationContext(),different_months);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Report.this,ReportDetails.class);
                Bundle bundle = new Bundle();
                bundle.putString("pos",months[Integer.parseInt(recordlist.get(position).date.split("/")[1])-1]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
