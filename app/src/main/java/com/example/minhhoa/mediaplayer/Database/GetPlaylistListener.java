package com.example.minhhoa.mediaplayer.Database;

import com.example.minhhoa.mediaplayer.Model.Server.Playlist;

import java.util.ArrayList;



public interface GetPlaylistListener {
    void getPlaylist(ArrayList<Playlist> playlistArrayList);
}
