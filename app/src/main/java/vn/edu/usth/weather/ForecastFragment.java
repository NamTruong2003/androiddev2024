package vn.edu.usth.weather;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class ForecastFragment extends Fragment {


    public ForecastFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout view =(FrameLayout)  inflater.inflate(R.layout.fragment_forecast, container, false);
        view.setBackgroundColor(Color.parseColor("#f7f7f7"));
//        LinearLayout linearLayout1 = new LinearLayout(view.getContext());
//        linearLayout1.setOrientation(LinearLayout.VERTICAL);
//        TextView textView = new TextView(view.getContext());
//        String text = "Thursday";
//        textView.setText(text);
//        ImageView imageView = new ImageView(view.getContext());
//        imageView.setImageResource(R.drawable.hurricane);
//        view.addView(linearLayout1);
//        linearLayout1.addView(textView);
//        linearLayout1.addView(imageView);

        return view;
    }

}