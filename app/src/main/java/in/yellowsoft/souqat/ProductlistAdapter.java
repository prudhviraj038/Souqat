package in.yellowsoft.souqat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductlistAdapter extends BaseAdapter{
    Context context;
    ArrayList<Products> restaurants;
    private static LayoutInflater inflater=null;
    public ProductlistAdapter(Context mainActivity, ArrayList<Products> restaurants) {
        context=mainActivity;
        this.restaurants = restaurants;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return restaurants.size();
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
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.company_list_item_screen, null);
        holder.com_name=(TextView) rowView.findViewById(R.id.company_list_name);
//        holder.com_items=(TextView) rowView.findViewById(R.id.company_list_items);
        holder.price=(TextView) rowView.findViewById(R.id.p_price_tv);
        holder.stock=(TextView) rowView.findViewById(R.id.p_stock_tv);
        holder.com_logo=(ImageView) rowView.findViewById(R.id.com_list_logo);
        holder.com_name.setText(restaurants.get(position).getTitle(context));
//        holder.com_items.setText(restaurants.get(position).getdescription(context));
        holder.price.setText(restaurants.get(position).price+" KD ");
        holder.stock.setText(restaurants.get(position).stock);
        Picasso.with(context).load(restaurants.get(position).images.get(0).img).fit().into(holder.com_logo);
        return rowView;
    }

}