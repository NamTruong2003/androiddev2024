package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT =3;
    private String titles[]= new String[] {"Hanoi", "Paris","Toulouse"};
    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount(){
        return PAGE_COUNT; //numberofpagesforaViewPager
    }
    @Override
    public Fragment getItem(int page){
        //returnsaninstanceofFragmentcorrespondingtothespecifiedpage
        switch (page) {
            case 0: return new WeatherAndForecastFragment();
            case 1: return new WeatherAndForecastFragment();
            case 2: return new WeatherAndForecastFragment();
        }
        return new EmptyFragment(); //failsafe
    }
    @Override
    public CharSequence getPageTitle(int page){
        //returnsatabtitlecorrespondingtothespecifiedpage
        return titles[page];
    }
}