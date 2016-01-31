package xionces.com.gonemoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by TayfunCESUR on 31.01.16.
 */
public class ReportListAdapter extends BaseAdapter {


    Context context;
    LayoutInflater inflater;
    List<String> ds;

    public ReportListAdapter(Context context,List<String> dataSource) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.ds = dataSource;
    }

    public class ViewHolder {
        TextView date;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int position) {
        return ds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.report_list_item, null);
            holder.date = (TextView) view.findViewById(R.id.reportdate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.date.setText(ds.get(position));
        return view;
    }

}