package com.example.minhhoa.mediaplayer.View;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhhoa.mediaplayer.Adapter.ViewPagerAdapter;
import com.example.minhhoa.mediaplayer.Database.DBSongOnline;
import com.example.minhhoa.mediaplayer.Database.GetSongListener;
import com.example.minhhoa.mediaplayer.Model.Server.SongOnline;
import com.example.minhhoa.mediaplayer.R;
import com.example.minhhoa.mediaplayer.View.Fragment.Fragment_Search;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener, GetSongListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static FrameLayout rootLayout;
    private MaterialSearchBar searchBar;

    public static String searchString = "";
    private DBSongOnline dbSongOnline;
    public ArrayList<SongOnline> songOnlineArrayList_search = new ArrayList<>();
    FragmentManager manager;
    ViewPagerAdapter adapter;


    final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);

        searchBar.setOnSearchActionListener(this);
        //tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        manager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(manager);


        // Cài đặt pageview cho mainactivity
        if (adapter != null) {
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setTabsFromPagerAdapter(adapter);
            createTabIcons();
        }
        // ẩn music bottom
        rootLayout = (FrameLayout) findViewById(R.id.music_bottom);
        View.inflate(this, R.layout.music_bottom, rootLayout);
        try {
            if (Screen_PlayMusic.mediaPlayer != null && Screen_PlayMusic.type.equals("offline")) {
                Music_Bottom pb = new Music_Bottom();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.music_bottom, pb);
                transaction.commit();
            } else
                rootLayout.setVisibility(View.GONE);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        // Yêu cầu quền truy cập vào file trong sdcard
        int res = this.checkCallingOrSelfPermission(permission);
        if (res != PackageManager.PERMISSION_GRANTED) {
            checkPermissionREAD_EXTERNAL_STORAGE(MainActivity.this);
        } else {
        }

        String permission2 = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        // Yêu cầu quền truy cập vào file trong sdcard
        int res2 = this.checkCallingOrSelfPermission(permission);
        if (res2 != PackageManager.PERMISSION_GRANTED) {
            haveStoragePermission();
        } else {
        }


        dbSongOnline = new DBSongOnline(this);


    }

    private void createTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Trang chủ");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_home, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Cá nhân");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_account, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Tìm");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_search, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);


        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabFour.setText("Thêm");
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_menu, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }


    // Gửi yêu cầu truy cập vào file trong sdcard
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {
                    Toast.makeText(MainActivity.this, "Load File is denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }

    // Hiện thị dialog yêu cầu sử dụng quyền
    public void showDialog(final String msg, final Context context, final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{permission},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    // Kiểm tra đã có quyền truy cập
    public boolean checkPermissionREAD_EXTERNAL_STORAGE(final Context context) {

        int currentAPIVersion = Build.VERSION.SDK_INT;

        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", this, Manifest.permission.READ_EXTERNAL_STORAGE);
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }


    public  boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                return true;
            } else {

                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
        String s = enabled ? "enabled" : "disabled";
        Toast.makeText(MainActivity.this, "Search " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        searchString = text.toString();
        Log.e("String search :", searchString);
        dbSongOnline.getSongBySearch(searchString);
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        Toast t = Toast.makeText(getBaseContext(), "ButtonClick", Toast.LENGTH_LONG);
        t.show();
    }


    @Override
    public void getListSong(ArrayList<SongOnline> songOnlines) {
        if (!searchString.equals("")) {
            if (songOnlineArrayList_search != null) {
                songOnlineArrayList_search.clear();
            }
            songOnlineArrayList_search = songOnlines;
            Log.e("listSongSearch : ",String.valueOf(songOnlineArrayList_search.size()));
            adapter.song_search = songOnlineArrayList_search;
            adapter.notifyDataSetChanged();
            tabLayout.getTabAt(2).select();


////            Log.e("Song length: ",String.valueOf(songOnlineArrayList_search.size()));
        }

    }


}
