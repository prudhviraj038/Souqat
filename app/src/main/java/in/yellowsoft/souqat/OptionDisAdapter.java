package in.yellowsoft.souqat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OptionDisAdapter extends BaseAdapter{
    Context context;
    Products restaurants;
    int pos;
    private static LayoutInflater inflater=null;
    public OptionDisAdapter(Context mainActivity, Products restaurants,int pos) {
        context=mainActivity;
        this.restaurants = restaurants;
        this.pos=pos;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return restaurants.groups.get(pos).addons.size();
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
        TextView com_name,price;
        ImageView com_logo;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.option_dis_item, null);
        holder.com_name=(TextView) rowView.findViewById(R.id.o_title_adp);
        holder.price=(TextView) rowView.findViewById(R.id.o_price_adp);
        holder.com_name.setText(restaurants.groups.get(pos).addons.get(position).option);
        holder.price.setText(restaurants.groups.get(pos).addons.get(position).price);
        return rowView;
    }

}