package vn.edu.usth.weather;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


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
        if (id == R.id.refresh_button) {
            Toast.makeText(getApplicationContext(), "Refresh button is pressed", Toast.LENGTH_SHORT).show();

            AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String,Integer,Bitmap>() {

                @Override
                protected Bitmap doInBackground(String... strings) {
                    Bitmap bitmap = null;
                    try {
                        URL url = new URL(strings[0]);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream is =connection.getInputStream();
                        bitmap= BitmapFactory.decodeStream(is);
                        is.close();


                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    // This method is executed on the main thread after doInBackground is finished
                    if(bitmap != null){
                        ImageView imageView = findViewById(R.id.logo);
                        imageView.setImageBitmap(bitmap);


                        Toast.makeText(WeatherActivity.this, "image is set", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(WeatherActivity.this, "Failed to load image", Toast.LENGTH_SHORT).show();
                    }

                }
            };

            task.execute("https://cdn.haitrieu.com/wp-content/uploads/2022/11/Logo-Truong-Dai-hoc-Khoa-hoc-va-Cong-nghe-Ha-Noi.png");
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