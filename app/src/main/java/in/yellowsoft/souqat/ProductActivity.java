package in.yellowsoft.souqat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ProductActivity extends Activity {
    ListView lv,g_lv;
    ProductlistAdapter productlistAdapter;
    GroupDisAdapter groupDisAdapter;
    ArrayList<Products> productses;
    ViewFlipper vf;
    private SliderLayout mDemoSlider;
    int pos;
    TextView p_title,price,des,stock;
    LinearLayout edit,add_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        productses=new ArrayList<>();
        get_products();
        vf=(ViewFlipper)findViewById(R.id.viewFlipper2);
        lv=(ListView)findViewById(R.id.p_lv);
        productlistAdapter=new ProductlistAdapter(this,productses);
        edit=(LinearLayout)findViewById(R.id.edit_p_ll);
        add_new=(LinearLayout)findViewById(R.id.add_new_ll);
        p_title=(TextView)findViewById(R.id.p_dis_title);
        stock=(TextView)findViewById(R.id.p_dis_stock);
        price=(TextView)findViewById(R.id.p_dis_price);
        des=(TextView)findViewById(R.id.p_dis_des);
        lv.setAdapter(productlistAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vf.setDisplayedChild(1);
                pos = position;
                g_lv = (ListView) findViewById(R.id.p_dis_g_lv);
                groupDisAdapter = new GroupDisAdapter(ProductActivity.this, productses.get(position));
                g_lv.setAdapter(groupDisAdapter);
                p_title.setText(productses.get(position).title);
                price.setText(productses.get(position).price);
                des.setText(productses.get(position).description);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, AddProduct2Activity.class);
                intent.putExtra("new","1");
                intent.putExtra("products", productses.get(pos));
                startActivity(intent);
            }
        });
        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProductActivity.this,AddProduct2Activity.class);
                intent.putExtra("new","0");
//                intent.putExtra("products",productses.get(pos));
                startActivity(intent);
            }
        });
        mDemoSlider = (SliderLayout)findViewById(R.id.view);
            for (int i = 0; i < productses.get(pos).images.size(); i++) {
                DefaultSliderView defaultSliderView = new DefaultSliderView(this);
                defaultSliderView.image(productses.get(pos).images.get(i).img).setScaleType(BaseSliderView.ScaleType.CenterCrop);
                mDemoSlider.addSlider(defaultSliderView);
            }
    }
    private void get_products() {
        String url;
        url = Settings.SERVERURL+"products.php?shop_id="+Settings.getUserid(this);
        Log.e("url--->", url);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonObject) {
                progressDialog.dismiss();
                productses.clear();
                Log.e("response is: ", jsonObject.toString());
                try {
                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject sub = jsonObject.getJSONObject(i);
                        Products prod=new Products(sub);
                        productses.add(prod);
                    }
                    lv.setAdapter(productlistAdapter);
                    productlistAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(ProductActivity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);

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
