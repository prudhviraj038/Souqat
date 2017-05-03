package in.yellowsoft.souqat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.body.FilePart;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AddProductActivity extends Activity {
    RecyclerView recyclerView,recyclerView_options;
    RecyclerProductAdapter mAdapter;
    RecyclerProductoptionAdapter optionAdapter;
    ViewFlipper vf;
    TextView add_group,add_options,cat_tv,stock_tv,in_stock,low_stock,out_stock,add_img_tv;
    ArrayList<Group> grouplist;
    ArrayList<Options> optionslist;
    int previous_selected_group;
    int pos;
    ImageView add_img,back;
    ArrayList<String> cat_id;
    ArrayList<String> cat_title;
    String select_cat_id;
    LinearLayout cat_ll,stock_ll,stock_pop,next1_ll,next2_ll,add_btn,save,p1,s1,o1;
    EditText p_title,p_title_ar,p_price,p_sd,p_sd_ar,p_d,p_d_ar;
    String stock;
    JSONObject jsonObject;
    Products products;
    String add_new="";
    ArrayList<String> im1;
    ArrayList<String> img_path;
    GridView gv;
    ImageAdapter imageAdapter;
    String i_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_add_activity);
        cat_id = new ArrayList<String>();
        cat_title = new ArrayList<String>();
        im1 = new ArrayList<String>();
        img_path = new ArrayList<String>();
        gv=(GridView)findViewById(R.id.gv_img);
        add_new=getIntent().getStringExtra("new");
        if(add_new.equals("1")){
            products=(Products)getIntent().getSerializableExtra("products");
        }
        back=(ImageView)findViewById(R.id.ap_back);

        o1=(LinearLayout)findViewById(R.id.o4);
        s1=(LinearLayout)findViewById(R.id.s4);
        p1=(LinearLayout)findViewById(R.id.p4);

        vf=(ViewFlipper)findViewById(R.id.viewFlipper);
        p_title = (EditText) findViewById(R.id.et_p_title);
        p_title_ar = (EditText) findViewById(R.id.et_p_title_ar);
        p_price = (EditText) findViewById(R.id.et_p_price);
        p_sd = (EditText) findViewById(R.id.et_p_sd);
        p_sd_ar = (EditText) findViewById(R.id.et_p_sd_ar);
        p_d = (EditText) findViewById(R.id.et_p_d);
        p_d_ar = (EditText) findViewById(R.id.et_p_d_ar);

        add_img = (ImageView) findViewById(R.id.add_image_img);

        add_img_tv = (TextView) findViewById(R.id.add_image_tv);
        in_stock = (TextView) findViewById(R.id.in_stock_tv);
        low_stock = (TextView) findViewById(R.id.low_stock_tv);
        out_stock = (TextView) findViewById(R.id.out_stock_tv);
        stock_tv = (TextView) findViewById(R.id.p_stock_tv);
        cat_tv = (TextView) findViewById(R.id.cat_tv_ap);
        add_btn = (LinearLayout) findViewById(R.id.save_nbtn_ll);
        stock_ll = (LinearLayout) findViewById(R.id.p_stock_ll);
        stock_pop = (LinearLayout) findViewById(R.id.stock_pop);
        next1_ll = (LinearLayout) findViewById(R.id.next1_ll);
        next2_ll = (LinearLayout) findViewById(R.id.next2_ll);
        save = (LinearLayout) findViewById(R.id.save_final_ll);
        cat_ll = (LinearLayout) findViewById(R.id.cat_ll_ap);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductActivity.this, OrdersActivity.class);
                startActivity(intent);
            }
        });
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        cat_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddProductActivity.this);
//                builder.setTitle(Settings.getword(AddProductActivity.this,"choose_levels"));
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddProductActivity.this, android.R.layout.simple_dropdown_item_1line, cat_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, cat_title.get(which), Toast.LENGTH_SHORT).show();
                        select_cat_id = cat_id.get(which);
                        cat_tv.setText(cat_title.get(which));
                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                dialog.show();
            }
        });
        add_img_tv.setVisibility(View.VISIBLE);
        add_img_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        get_level();
        stock_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stock_pop.getVisibility()==View.VISIBLE){
                    stock_pop.setVisibility(View.GONE);
                }else {
                    stock_pop.setVisibility(View.VISIBLE);
                }
            }
        });
        in_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stock_pop.setVisibility(View.GONE);
                stock_tv.setText("In Stock");
                stock = "1";
            }
        });
        low_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stock_pop.setVisibility(View.GONE);
                stock_tv.setText("Low Stock");
                stock="2";
            }
        });
        out_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stock_pop.setVisibility(View.GONE);
                stock_tv.setText("Out of Stock");
                stock="3";
            }
        });
        next1_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(1);
                jsonObject = new JSONObject();
                try {
                    jsonObject.put("title",p_title.getText().toString());
                    jsonObject.put("title_ar",p_title_ar.getText().toString());
                    jsonObject.put("category", select_cat_id);
                    jsonObject.put("price", p_price.getText().toString());
                    jsonObject.put("stock", stock);
                    jsonObject.put("sdescription",p_sd.getText().toString());
                    jsonObject.put("sdescription_ar",p_sd_ar.getText().toString());
                    jsonObject.put("description",p_d.getText().toString());
                    jsonObject.put("description_ar",p_d_ar.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
      add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(1);
            }
        });
        JSONArray groups_array;
        next2_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(3);
                JSONArray groups_array = new JSONArray();
                try {
                    for (int i = 0; i < grouplist.size(); i++) {
                        JSONObject group_jsonObject = new JSONObject();
                        Group group = grouplist.get(i);
                        group_jsonObject.put("title", group.title);
                        group_jsonObject.put("title_ar", group.title_ar);
                        group_jsonObject.put("minimum", group.min);
                        group_jsonObject.put("maximum", group.max);

                        JSONArray options_array = new JSONArray();
                        for(int j=0;j< group.options.size();j++){
                            JSONObject option_object = new JSONObject();
                            Options options = group.options.get(j);
                            option_object.put("option",options.title);
                            option_object.put("option_ar",options.title_ar);
                            option_object.put("price",options.price);
                            options_array.put(option_object);

                        }

                        group_jsonObject.put("option", options_array);

                        groups_array.put(group_jsonObject);

                        jsonObject.put("group",groups_array);
                    }
                    Log.e("data_to_send", groups_array.toString());
                }catch (Exception ex){
                    ex.printStackTrace();
                }



            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upload_data();

            }
        });
        grouplist=new ArrayList<>();
//        optionslist=new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView_options = (RecyclerView) findViewById(R.id.options_rlv);
        add_group = (TextView) findViewById(R.id.add_group_tv);
        add_options = (TextView) findViewById(R.id.add_option_tv);

        mAdapter = new RecyclerProductAdapter(grouplist,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        add_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grouplist.add((new Group("", "", "", "")));
                mAdapter.notifyDataSetChanged();
            }
        });
        add_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                optionslist.add((new Options("", "", "")));
                grouplist.get(pos).options.add(new Options("","",""));
                optionAdapter.notifyDataSetChanged();
            }
        });
        if(add_new.equals("1")){
            p_title.setText(products.title);
            p_title_ar.setText(products.title_ar);
            p_price.setText(products.price);
//            cat_tv.setText(products.title);
            stock_tv.setText(products.stock);
            p_sd.setText(products.about);
            p_sd_ar.setText(products.about_ar);
            p_d.setText(products.description);
            p_d_ar.setText(products.description_ar);
                for (int i = 0; i < products.groups.size(); i++) {
                    grouplist.add(new Group(products.groups.get(i).group, products.groups.get(i).group_ar, products.groups.get(i).min, products.groups.get(i).max));
                    for (int j = 0; j < products.groups.get(i).addons.size(); j++) {
                        if(products.groups.get(i).addons.size()!=0) {
                            grouplist.get(i).options.add(new Options(products.groups.get(i).addons.get(j).option, products.groups.get(i).addons.get(j).option_ar, products.groups.get(i).addons.get(j).price));
                        }
                    }
                }
            mAdapter = new RecyclerProductAdapter(grouplist,this);
            RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager1);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
            for (int i = 0; i < products.images.size(); i++) {
                im1.add(products.images.get(i).img);
            }
            imageAdapter=new ImageAdapter(this,im1);
            gv.setAdapter(imageAdapter);
            imageAdapter.notifyDataSetChanged();

        }
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(products.images.get(position).img.startsWith("http")){
                        delete_images(products.images.get(position).i_id);
                }else{
                    im1.remove(position);
                    img_path.remove(position);
                    imageAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    boolean isimgchoosen = false;
    final int RESULT_LOAD_IMAGE = 1;
    String imgDecodableString;
    String encodedString;
    Bitmap bitmap;

    private Uri mImageCaptureUri;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(this, "image selected", Toast.LENGTH_LONG)
//                .show();
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == this.RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = this.getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
//                ImageView imgView = (ImageView) v.findViewById(R.id.ic_upload_logo);
                // Set the Image in ImageView after decoding the String
                im1.add(imgDecodableString);
                imageAdapter=new ImageAdapter(this,im1);
                gv.setAdapter(imageAdapter);
                imageAdapter.notifyDataSetChanged();
                add_img.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));
//                add_img_tv.setVisibility(View.GONE);
                isimgchoosen=true;
                encodeImagetoString();
            }else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }
    public void encodeImagetoString() {
        new AsyncTask<Void, Void, String>() {
            final ProgressDialog progressDialog = new ProgressDialog(AddProductActivity.this);

            protected void onPreExecute() {
                progressDialog.setMessage("please wait.. encoding image");
                progressDialog.show();
                progressDialog.setCancelable(false);

            };

            @Override
            protected String doInBackground(Void... params) {
                BitmapFactory.Options options = null;
                options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                encodedString = "";

                    bitmap = BitmapFactory.decodeFile(imgDecodableString, options);
                 ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Must compress the Image to reduce image size to make upload easy
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                byte[] byte_arr = stream.toByteArray();
                // Encode Image to String
                encodedString = Base64.encodeToString(byte_arr, Base64.NO_WRAP);
                img_path.add(encodedString);
                Log.e("image",encodedString);

                return "";
            }

            @Override
            protected void onPostExecute(String msg) {

                // Put converted Image string into Async Http Post param
                // Trigger Image upload
                if(progressDialog!=null)
                    progressDialog.dismiss();


            }
        }.execute(null, null, null);
    }
    public void to_options(int pos){
        vf.setDisplayedChild(2);
        this.pos=pos;
        optionAdapter = new RecyclerProductoptionAdapter(grouplist.get(pos).options);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView_options.setLayoutManager(mLayoutManager1);
        recyclerView_options.setItemAnimator(new DefaultItemAnimator());
        recyclerView_options.setAdapter(optionAdapter);
        previous_selected_group=pos;
    }
    @Override
    public void onBackPressed() {
        if(vf.getDisplayedChild()==1)
            vf.setDisplayedChild(0);
        else if(vf.getDisplayedChild()==2)
            vf.setDisplayedChild(1);
        else if(vf.getDisplayedChild()==3)
            vf.setDisplayedChild(1);
        else
            super.onBackPressed();
    }
    private void get_level() {
        String url = Settings.SERVERURL + "category.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonArray.toString());
                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String level_name = sub.getString("title");
                        String levels_id = sub.getString("id");
                        cat_id.add(levels_id);
                        cat_title.add(level_name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(AddProductActivity.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });
// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }
    String p_id;
    public void upload_data(){
//        List<Part> files = new ArrayList();
//        for (int i = 0; i < img_path.size(); i++) {
//            files.add(new FilePart("image" + i, new File(img_path.get(i))));
//        }
        Log.e("jsonobjet_product", jsonObject.toString());
        final ProgressBar progressBar = new ProgressBar(this);
        final  ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait image is loading..");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Ion.with(this)
                .load(Settings.SERVERURL + "product.php")
                .uploadProgressBar(progressBar)
                .uploadProgressHandler(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
                        progressDialog.setMax((int) total);
                        progressDialog.setProgress((int) downloaded);
                    }
                })
                .setMultipartParameter("product", jsonObject.toString())
                .setMultipartParameter("shop_id", "1")
//                .setMultipartFile("file","image/png",new File(encodedString))
//                .addMultipartParts(files)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (progressDialog != null)
                            progressDialog.dismiss();
//                        if (e != null) {
//                            e.printStackTrace();
//                        } else if (result.isJsonNull()) {
//                            Log.e("json_null", null);
//                        } else {
//                            Log.e("image_path_response", result.toString());
//                                finish();
                        if (result.get("status").getAsString().equals("Success")) {
                            p_id=result.get("product_id").getAsString();
                            if(isimgchoosen){
                                upload_images();
                            }else {
                                Toast.makeText(AddProductActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddProductActivity.this, ProductActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(AddProductActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
    int current=0;
    public void upload_images(){
        Log.e("image_path_size", String.valueOf(img_path.size()));
        final ProgressBar progressBar = new ProgressBar(this);
        final  ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait image is loading..");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Ion.with(this)
                .load(Settings.SERVERURL + "product-image.php")
                .uploadProgressBar(progressBar)
                .uploadProgressHandler(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
                        progressDialog.setMax((int) total);
                        progressDialog.setProgress((int) downloaded);
                    }
                })
                .setMultipartParameter("product_id", p_id)
                .setMultipartParameter("file", img_path.get(current))
                .setMultipartParameter("ext_str", "jpg")
//                .setMultipartFile("file","image/png",new File(encodedString))
//                .addMultipartParts(files)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (progressDialog != null)
                            progressDialog.dismiss();
//                        if (e != null) {
//                            e.printStackTrace();
//                        } else if (result.isJsonNull()) {
//                            Log.e("json_null", null);
//                        } else {
//                            Log.e("image_path_response", result.toString());
//                                finish();
                        if (result.get("status").getAsString().equals("Failed")) {
                            Toast.makeText(AddProductActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddProductActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                            current++;
                            if(current<img_path.size()){
                                    Log.e("image_path_size_test", String.valueOf(current));
                                    upload_images();

                            }else{
                                Intent intent = new Intent(AddProductActivity.this, ProductActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }


                    }
                });
    }

    public void delete_images(String id){
        final ProgressBar progressBar = new ProgressBar(this);
        final  ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait image is loading..");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Ion.with(this)
                .load(Settings.SERVERURL + "product-image-delete.php")
                .uploadProgressBar(progressBar)
                .uploadProgressHandler(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
                        progressDialog.setMax((int) total);
                        progressDialog.setProgress((int) downloaded);
                    }
                })
                .setMultipartParameter("product_id", p_id)
                .setMultipartParameter("image_id", id)
//                .setMultipartFile("file","image/png",new File(encodedString))
//                .addMultipartParts(files)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (progressDialog != null)
                            progressDialog.dismiss();
//                        if (e != null) {
//                            e.printStackTrace();
//                        } else if (result.isJsonNull()) {
//                            Log.e("json_null", null);
//                        } else {
//                            Log.e("image_path_response", result.toString());
//                                finish();
                        if (result.get("status").getAsString().equals("Failed")) {
                            Toast.makeText(AddProductActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddProductActivity.this, result.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                            imageAdapter.notifyDataSetChanged();
                        }


                    }
                });
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
