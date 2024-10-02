package vn.edu.usth.weather;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.tabs.TabLayout;

import java.io.InputStream;


public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "FunctionTracing";
    private  MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i(TAG,"my app create");
        Toolbar toolbar = findViewById(R.id.xml_toolbar);

        setSupportActionBar(toolbar);


        EdgeToEdge.enable(this);

        getSupportActionBar().setTitle("USTH weather");
        // Set the title text color
        int titleColor = Color.BLACK; // Change this to the color you want
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View child = toolbar.getChildAt(i);
            if (child instanceof TextView) {
                TextView titleView = (TextView) child;
                titleView.setTextColor(titleColor);
                break;
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout =(TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Release resources after playback is complete
                mediaPlayer.release();
            }
        });

        mediaPlayer.start();







    }




    @Override
    protected void onStart(){
        Log.i(TAG,"my app start");
        super.onStart();
    }

    @Override
    protected void onResume(){
        Log.i(TAG,"my app resume");
        super.onResume();
    }

    @Override
    protected void onPause(){
        Log.i(TAG,"my app pause");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.i(TAG,"my app stop");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.i(TAG,"my app destroy");
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.refresh_button){
            Toast.makeText(getApplicationContext(), "Refresh button is pressed ", Toast.LENGTH_SHORT).show();

            final Handler handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg){
                    //Thismethodisexecutedinmainthread
                    String content=msg.getData().getString("server_response");
                    Toast.makeText(WeatherActivity.this, content,Toast.LENGTH_SHORT).show();
                }
            };
            Thread t= new Thread(new Runnable() {
                @Override
                public void run(){
                    //this method is run in a worker thread
                    try {
                        //wait for 5seconds to simulate a long network access
                        Thread.sleep(3000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Assumethatwegotourdatafromserver
                    Bundle bundle= new Bundle();
                    bundle.putString("server_response","connection require");
                    //notifymainthread
                    Message msg= new Message();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            });
            t.start();
            return true;
        }
        if(id == R.id.menu_button){
            Intent intent = new Intent(this, PrefActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}