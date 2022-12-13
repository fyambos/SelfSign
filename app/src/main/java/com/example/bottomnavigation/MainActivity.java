package com.example.bottomnavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.bottomnavigation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.schedule:
                    replaceFragment(new ScheduleFragment());
                    break;

                case R.id.attendance:
                    // TODO : Fix AttendanceFragment
                    //replaceFragment(new AttendanceFragment());
                    //AttendanceFragment is a fragment nesting a ViewPager2 (ViewPager2 allows tab-switching between fragments)
                    //Has a debug error: E/RecyclerView: No adapter attached; skipping layout

                    replaceFragment(new AbsenceFragment());
                    break;

                case R.id.manage:
                    replaceFragment(new ManageFragment());
                    break;
            }

            return true;

        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}