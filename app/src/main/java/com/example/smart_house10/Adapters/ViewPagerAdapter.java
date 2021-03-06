package com.example.smart_house10.Adapters;

import android.graphics.drawable.Icon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;
//Un adaptateur pour les fragments que nous allons assigner au Tablayout
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> FragmentListTitles = new ArrayList<>();
//Une liste serait plus pratique pour créer autant de tab qu'on souhaite plus tard

    public ViewPagerAdapter (FragmentManager fm)
    {
        super(fm);

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitles.get(position);
    }
    public void AddFragment(Fragment fragment, String Title){
        fragmentList.add(fragment);
        FragmentListTitles.add(Title);

    }

    @Override
    public int getCount() {
        return FragmentListTitles.size();
    }
}
