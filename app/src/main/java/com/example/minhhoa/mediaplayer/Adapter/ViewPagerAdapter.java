package com.example.minhhoa.mediaplayer.Adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.minhhoa.mediaplayer.Model.Server.SongOnline;
import com.example.minhhoa.mediaplayer.View.Fragment.Fragment_HomePage;
import com.example.minhhoa.mediaplayer.View.Fragment.Fragment_Personal;
import com.example.minhhoa.mediaplayer.View.Fragment.Fragment_Search;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ArrayList<SongOnline> song_search;
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
                frag = new Fragment_Search();
                Bundle args = new Bundle();
                args.putParcelableArrayList("listSearch", song_search);
                frag.setArguments(args);
//                Log.e("listSongSearchItem : ",String.valueOf(song_search.size()));
                break;
            case 3:
                frag = new Fragment_Search();

                break;
        }
        return frag;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 4;
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
                title = "Tìm";
                break;
            case 3:
                title = "Thêm";
                break;
        }
        return title;
    }
}
