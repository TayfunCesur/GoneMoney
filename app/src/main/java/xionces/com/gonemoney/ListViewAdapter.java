package xionces.com.gonemoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tayfun CESUR on 24.01.2016.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<Expense> ds;

    public ListViewAdapter(Context context,List<Expense> dataSource) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.ds = dataSource;
    }

    public class ViewHolder {
        TextView date;
        TextView desc;
        TextView amount;
        TextView category;
        ImageView face;
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
            view = inflater.inflate(R.layout.list_row, null);
            holder.desc = (TextView) view.findViewById(R.id.description);
            holder.date = (TextView) view.findViewById(R.id.date);
            holder.amount = (TextView) view.findViewById(R.id.amount);
            holder.category = (TextView) view.findViewById(R.id.category);
            holder.face = (ImageView) view.findViewById(R.id.imageView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        if (ds.get(position).isMinus)
        {
            holder.amount.setText("-"+ds.get(position).amount+" TL ");
            holder.amount.setTextColor(context.getResources().getColor(R.color.list_minus_color));
            holder.desc.setTextColor(context.getResources().getColor(R.color.list_minus_color));
            holder.category.setTextColor(context.getResources().getColor(R.color.list_minus_color));
            holder.face.setImageResource(R.mipmap.sad);
        }
        else
        {
            holder.amount.setText("+"+ds.get(position).amount+" TL ");
            holder.amount.setTextColor(context.getResources().getColor(R.color.list_plus_color));
            holder.desc.setTextColor(context.getResources().getColor(R.color.list_plus_color));
            holder.category.setTextColor(context.getResources().getColor(R.color.list_plus_color));
            holder.face.setImageResource(R.mipmap.smile);
        }
        holder.desc.setText("Açıklama : " +ds.get(position).description);
        holder.date.setText("Tarih : "+ds.get(position).date);
        holder.category.setText("Kategori : "+ds.get(position).category);
        return view;
    }
}