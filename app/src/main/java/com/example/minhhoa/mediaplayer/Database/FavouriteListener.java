package com.example.minhhoa.mediaplayer.Database;

import com.example.minhhoa.mediaplayer.Model.Server.SongOnline;

import java.util.ArrayList;



public interface FavouriteListener {
    void Insert_Delete_Fav(ArrayList<SongOnline> songOnlines);
}
