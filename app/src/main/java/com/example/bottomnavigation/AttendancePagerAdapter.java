package com.example.bottomnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class AttendancePagerAdapter extends FragmentStateAdapter {

    private final Fragment[] mFragments = new Fragment[] {//Initialize fragments views
            //Fragment views are initialized like any other fragment (Extending Fragment)
            new AbsenceFragment(),//First fragment to be displayed within the pager tab number 1
            new DelayFragment(),
    };
    public final String[] mFragmentNames = new String[] {//Tabs names array
            "Absence",
            "Delay"
    };

    public AttendancePagerAdapter(FragmentActivity fa){//Pager constructor receives Activity instance
        super(fa);
    }

    @Override
    public int getItemCount() {
        return mFragments.length;//Number of fragments displayed
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments[position];
    }
}