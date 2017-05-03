package in.yellowsoft.souqat;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class RecyclerProductAdapter extends RecyclerView.Adapter<RecyclerProductAdapter.MyViewHolder> {


    private List<Group> grouplist;
    AddProductActivity addProduct2Activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre,add_options_tv;
        public EditText g_title,g_title_ar,min,max;
        public MyViewHolder(View view) {
            super(view);
            add_options_tv = (TextView) view.findViewById(R.id.add_options_tv);
            g_title = (EditText) view.findViewById(R.id.et_g_title);
            g_title_ar = (EditText) view.findViewById(R.id.et_g_title_ar);
            min = (EditText) view.findViewById(R.id.et_g_min);
            max = (EditText) view.findViewById(R.id.et_g_max);
            int h=itemView.getHeight();
//            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public RecyclerProductAdapter(List<Group> grouplist, AddProductActivity addProduct2Activity) {
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if(grouplist.size()!=0){
            holder.g_title.setText(grouplist.get(position).title);
            holder.g_title_ar.setText(grouplist.get(position).title_ar);
            holder.min.setText(grouplist.get(position).min);
            holder.max.setText(grouplist.get(position).max);
        }
        holder.g_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                grouplist.get(position).title=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.g_title_ar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                grouplist.get(position).title_ar = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.min.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                grouplist.get(position).min= s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.max.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                grouplist.get(position).max=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       holder.add_options_tv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               addProduct2Activity.to_options(position);
           }
       });

        }

    @Override
    public int getItemCount() {
        return grouplist.size();
    }
}
