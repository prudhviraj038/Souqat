package in.yellowsoft.souqat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GroupDisAdapter extends BaseAdapter{
    Context context;
    Products restaurants;
    OptionDisAdapter optionDisAdapter;
    private static LayoutInflater inflater=null;
    public GroupDisAdapter(Context mainActivity, Products restaurants) {
        context=mainActivity;
        this.restaurants = restaurants;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return restaurants.groups.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView com_name,com_items,price,stock;
        ImageView com_logo;
        ListView lv;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.group_dis_item, null);
        holder.com_name=(TextView) rowView.findViewById(R.id.g_title_adp);
        holder.com_name.setText(restaurants.groups.get(position).get_group(context));
        holder.lv=(ListView)rowView.findViewById(R.id.p_lv);
        optionDisAdapter=new OptionDisAdapter(context,restaurants,position);
        holder.lv.setAdapter(optionDisAdapter);
        return rowView;
    }

}