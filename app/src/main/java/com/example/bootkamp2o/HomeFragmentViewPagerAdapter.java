package com.example.bootkamp2o;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomeFragmentViewPagerAdapter extends FragmentPagerAdapter {
    public HomeFragmentViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new FirstYearFragment();
        }
        else {
            return new SecondYearFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    // Set Name of the TabLayout

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "2K23";
        }
       else {
           return "2K22";
        }
    }
}
