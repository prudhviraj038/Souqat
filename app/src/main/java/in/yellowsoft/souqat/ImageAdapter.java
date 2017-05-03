package in.yellowsoft.souqat;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> img;
    private static LayoutInflater inflater=null;
    public ImageAdapter(Context mainActivity, ArrayList<String> img) {
        context=mainActivity;
        this.img = img;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return img.size();
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
        ImageView im;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.image_grid_item, null);
        holder.im=(ImageView)rowView.findViewById(R.id.img_add_img);
        holder.im.setImageBitmap(BitmapFactory.decodeFile(img.get(position)));
//        Picasso.with(context).load(img.get(position)).fit().into(holder.im);
        return rowView;
    }

}