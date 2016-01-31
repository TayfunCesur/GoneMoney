package xionces.com.gonemoney;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    public static String[] months = new String[]{"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};
    private List<String> different_months = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports);

        setTitle("Pick a Month");

        list = (ListView) findViewById(R.id.listView2);
        recordlist = MainActivity.records;
        int z = 0;
        for (int i = 0; i<recordlist.size()-1;i++)
        {

            if (!recordlist.get(i).date.split("/")[1].equals(recordlist.get(i + 1).date.split("/")[1]))
            {
                if (!different_months.contains(months[Integer.parseInt(recordlist.get(i).date.split("/")[1])-1]))
                {
                    different_months.add(months[Integer.parseInt(recordlist.get(i).date.split("/")[1])-1]);
                    different_months.add(months[Integer.parseInt(recordlist.get(i+1).date.split("/")[1])-1]);
                }
            }
        }

        ReportListAdapter adapter = new ReportListAdapter(getApplicationContext(),different_months);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Report.this,ReportDetails.class);
                Bundle bundle = new Bundle();
                bundle.putInt("pos",position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
