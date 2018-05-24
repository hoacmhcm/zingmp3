package com.example.minhhoa.mediaplayer.Database;

import com.example.minhhoa.mediaplayer.Model.Server.SongOnline;

import java.util.ArrayList;


public interface GetSongListener {
    void  getListSong(ArrayList<SongOnline> songOnlines);
}
