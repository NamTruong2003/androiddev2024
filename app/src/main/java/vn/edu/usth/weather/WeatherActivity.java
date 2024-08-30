package vn.edu.usth.weather;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "FunctionTracing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i(TAG,"my app create");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ForecastFragment firstFragment = new ForecastFragment();
        // transfer to Forecast fragment
        getSupportFragmentManager().beginTransaction().add(R.id.main, firstFragment).commit();
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
    }

}