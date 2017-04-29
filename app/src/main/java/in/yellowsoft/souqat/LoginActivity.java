package in.yellowsoft.souqat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class LoginActivity extends Activity {
    EditText email,password;
    LinearLayout login;
    TextView fp,sta_email,sta_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        email=(EditText)findViewById(R.id.et_email);
        password=(EditText)findViewById(R.id.et_password);
        fp=(TextView)findViewById(R.id.fp_tv);
        sta_email=(TextView)findViewById(R.id.sta_email_tv);
        sta_password=(TextView)findViewById(R.id.sta_password_tv);
        login=(LinearLayout)findViewById(R.id.login_ll);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.equals(""))
                    Toast.makeText(LoginActivity.this, "please_enter_email", Toast.LENGTH_SHORT).show();
//                else if (!email.matches(emailPattern))
//                    alert.showAlertDialog(getActivity(), "Info", Settings.getword(getActivity(), "enter_valid_email"), false);
////                    Toast.makeText(getActivity(), Settings.getword(getActivity(),"enter_valid_email"), Toast.LENGTH_SHORT).show();
                else if (password.equals(""))
                    Toast.makeText(LoginActivity.this, "please_enter_password", Toast.LENGTH_SHORT).show();
                else
                    login();
            }
        });

    }
    public void login(){
        String url = Settings.SERVERURL+"login.php?";
        try {
            url = url + "username="+ URLEncoder.encode(email.getText().toString(), "utf-8")+
                    "&password="+URLEncoder.encode(password.getText().toString(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please_wait");
        progressDialog.show();
        progressDialog.setCancelable(false);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonObject.toString());
                try {
                    String reply=jsonObject.getString("status");
                    if(reply.equals("Failure")) {
                        String msg = jsonObject.getString("message");
                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String mem_id=jsonObject.getString("shop_id");
                        String name=jsonObject.getString("name");
                        Settings.setUserid(LoginActivity.this, mem_id, name);
                        Toast.makeText(LoginActivity.this, name, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,ProductActivity.class);
                        startActivity(intent);
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
                Toast.makeText(LoginActivity.this,"server_not_connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        });

// Access the RequestQueue through your singleton class.
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
