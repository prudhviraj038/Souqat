package in.yellowsoft.souqat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class SettingsActivity extends Activity {
    LinearLayout p1,o1,logout;
    ImageView back;
    TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        temp=(TextView)findViewById(R.id.dis_temp);
        logout=(LinearLayout)findViewById(R.id.logout_ll);
        back=(ImageView)findViewById(R.id.sett_back);
        p1=(LinearLayout)findViewById(R.id.p3);
        o1=(LinearLayout)findViewById(R.id.o3);
//        set_refresh_timer();
        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, OrdersActivity.class);
                startActivity(intent);
            }
        });
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Settings.setUserid(SettingsActivity.this, "-1", "");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

//    final Handler h = new Handler();
//    final int delay = 1000*1; //milliseconds
//    long millis = 3600000;
//    final Runnable r = new Runnable() {
//        @Override
//        public void run() {
//            Log.e("time", "ticked");
//            h.postDelayed(this, delay);
//            temp.setText(String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
//                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
//                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
//            millis=millis-1000;
//
//            // tabclicked(selected,true);
//        }
//    };
//    private void set_refresh_timer(){
//
//        h.postDelayed(r, delay);
//    }

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
