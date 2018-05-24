package com.example.minhhoa.mediaplayer.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.minhhoa.mediaplayer.View.Fragment.Fragment_HomePage;
import com.example.minhhoa.mediaplayer.View.Fragment.Fragment_Personal;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new Fragment_HomePage();
                break;
            case 1:
                frag = new Fragment_Personal();
                break;
            case 2:
                frag = new Fragment_Personal();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Trang chủ";
                break;
            case 1:
                title = "Cá nhân";
                break;
            case 2:
                title = "Thêm";
                break;
        }
        return title;
    }
}
