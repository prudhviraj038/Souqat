package in.yellowsoft.souqat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecyclerProductoptionAdapter extends RecyclerView.Adapter<RecyclerProductoptionAdapter.MyViewHolder> {


    private List<Options> grouplist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
//            title = (TextView) view.findViewById(R.id.title);
//            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public RecyclerProductoptionAdapter(List<Options> grouplist) {
        this.grouplist = grouplist;
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
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.option_item, parent, false);
                return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
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
