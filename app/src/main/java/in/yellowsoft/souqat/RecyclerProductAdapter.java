package in.yellowsoft.souqat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.MyViewHolder> {


    private List<Group> grouplist;
    AddProduct2Activity addProduct2Activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre,add_options_tv;

        public MyViewHolder(View view) {
            super(view);
            add_options_tv = (TextView) view.findViewById(R.id.add_options_tv);
//            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public RecyclerProductAdapter(List<Group> grouplist, AddProduct2Activity addProduct2Activity) {
        this.grouplist = grouplist;
        this.addProduct2Activity = addProduct2Activity;
    }
//    @Override
//    public  int getItemViewType(int position){
//        if(grouplist.get(position).group!=null){
//            return 0;
//        }else{
//            return 1;
//        }
//    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
                return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       holder.add_options_tv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               addProduct2Activity.to_options();
           }
       });
//        Group group = grouplist.get(position);
//        holder.title.setText(group.getTitle());
//        holder.genre.setText(group.getGenre());
//        holder.year.setText(group.getYear());
    }

    @Override
    public int getItemCount() {
        return grouplist.size();
    }
}
