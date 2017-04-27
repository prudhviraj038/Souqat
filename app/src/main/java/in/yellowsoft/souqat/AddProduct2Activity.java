package in.yellowsoft.souqat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewFlipper;

import java.util.ArrayList;


public class AddProduct2Activity extends Activity {
    RecyclerView recyclerView,recyclerView_options;
    RecyclerProductAdapter mAdapter;
    RecyclerProductoptionAdapter optionAdapter;
    ViewFlipper vf;
    ArrayList<Group> grouplist;
    ArrayList<Options> optionslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_add2_activity);
        grouplist=new ArrayList<>();
        optionslist=new ArrayList<>();
        vf=(ViewFlipper)findViewById(R.id.viewFlipper);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView_options = (RecyclerView) findViewById(R.id.options_rlv);

        mAdapter = new RecyclerProductAdapter(grouplist,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        grouplist.add((new Group("", "", "", "")));
        grouplist.add((new Group("", "", "", "")));
        mAdapter.notifyDataSetChanged();

    }
    public void to_options(){
        vf.setDisplayedChild(1);
        optionAdapter = new RecyclerProductoptionAdapter(optionslist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_options.setLayoutManager(mLayoutManager);
        recyclerView_options.setItemAnimator(new DefaultItemAnimator());
        recyclerView_options.setAdapter(optionAdapter);
        optionslist.add((new Options("", "", "")));
        optionslist.add((new Options("", "", "")));
        optionslist.add((new Options("", "", "")));
        optionAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
