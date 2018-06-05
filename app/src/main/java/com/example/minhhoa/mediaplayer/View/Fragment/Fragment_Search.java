package com.example.minhhoa.mediaplayer.View.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.minhhoa.mediaplayer.Adapter.Song_Online_Adapter;
import com.example.minhhoa.mediaplayer.Database.GetSongListener;
import com.example.minhhoa.mediaplayer.Model.Server.SongOnline;
import com.example.minhhoa.mediaplayer.R;
import com.example.minhhoa.mediaplayer.View.Screen_PlayMusic;

import java.util.ArrayList;

import static com.example.minhhoa.mediaplayer.View.Music_Online.songOnlineArrayList_online;


public class Fragment_Search extends Fragment implements AdapterView.OnItemClickListener ,GetSongListener {


    private ListView lvSearch;
    public Song_Online_Adapter song_online_adapter_search;


    public Fragment_Search() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        Log.e("error","CHAY DEN DAY");
        if ((bundle != null)) {
            songOnlineArrayList_online = getArguments().getParcelableArrayList("listSearch");

//            Log.e("listSongSearchFragment:",String.valueOf(songs.size()));

            // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
            // adapter knows how to create list items for each item in the list.
            song_online_adapter_search  = new Song_Online_Adapter(getContext(),songOnlineArrayList_online);

            // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
            // There should be a {@link ListView} with the view ID called list, which is declared in the
            // word_list.xml layout file.
            lvSearch = (ListView) view.findViewById(R.id.lvSearch);

            if(songOnlineArrayList_online != null)
            {
                lvSearch.setAdapter(song_online_adapter_search);
                lvSearch.setOnItemClickListener(this);
            }


            // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
            // {@link ListView} will display list items for each {@link Word} in the list.
        }
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() == song_online_adapter_search) {
            onItemSongClick(position);
        }
    }

    // Hàm xử lý khi click vào item danh sách bài hát online
    public void onItemSongClick(int pos) {
        try {
            if (Screen_PlayMusic.mediaPlayer != null && Screen_PlayMusic.mediaPlayer.isPlaying()) {
                Screen_PlayMusic.mediaPlayer.stop();
                Screen_PlayMusic.mediaPlayer.release();
                Screen_PlayMusic.mediaPlayer = null;
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        song_online_adapter_search.setPosition(pos);
        Intent intent = new Intent(getContext(), Screen_PlayMusic.class).putExtra("position", pos)
                .putExtra("time", 0).putExtra("play", "play").putExtra("type", "online");
        startActivity(intent);
    }


    @Override
    public void getListSong(ArrayList<SongOnline> songOnlines) {
//        songOnlineArrayList_online = songOnlines;

    }
}
