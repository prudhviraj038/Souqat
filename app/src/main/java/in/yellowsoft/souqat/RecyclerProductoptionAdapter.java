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


public class RecyclerProductoptionAdapter extends RecyclerView.Adapter<RecyclerProductoptionAdapter.MyViewHolder> {


    private List<Options> grouplist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
        public EditText o_title,o_title_ar,price;
        public MyViewHolder(View view) {
            super(view);
            o_title = (EditText) view.findViewById(R.id.et_o_title);
            o_title_ar = (EditText) view.findViewById(R.id.et_o_title_ar);
            price = (EditText) view.findViewById(R.id.et_o_price);
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if(grouplist.size()!=0){
            holder.o_title.setText(grouplist.get(position).title);
            holder.o_title_ar.setText(grouplist.get(position).title_ar);
            holder.price.setText(grouplist.get(position).price);
        }
        holder.o_title.addTextChangedListener(new TextWatcher() {
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
        holder.o_title_ar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                grouplist.get(position).title_ar=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                grouplist.get(position).price=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return grouplist.size();
    }
}
