package com.example.minhhoa.mediaplayer.Model.Server;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class SongOnline implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("source")
    @Expose
    private String DATA;
    @SerializedName("thumbnail")
    @Expose
    private String IMG;
    @SerializedName("title")
    @Expose
    private String TITLE;
    @SerializedName("id_artist")
    @Expose
    private int id_artist;
    @SerializedName("id_country")
    @Expose
    private int id_country;
    @SerializedName("name_artist")
    @Expose
    private String ARTIST;

    public SongOnline() {

    }

    public SongOnline(int id, String DATA, String IMG, String TITLE, int id_artist, int id_country, String ARTIST) {
        this.id = id;
        this.DATA = DATA;
        this.IMG = IMG;
        this.TITLE = TITLE;
        this.id_artist = id_artist;
        this.id_country = id_country;
        this.ARTIST = ARTIST;
    }

    protected SongOnline(Parcel in) {
        id = in.readInt();
        DATA = in.readString();
        IMG = in.readString();
        TITLE = in.readString();
        id_artist = in.readInt();
        id_country = in.readInt();
        ARTIST = in.readString();
    }

    public static final Creator<SongOnline> CREATOR = new Creator<SongOnline>() {
        @Override
        public SongOnline createFromParcel(Parcel in) {
            return new SongOnline(in);
        }

        @Override
        public SongOnline[] newArray(int size) {
            return new SongOnline[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getIMG() {
        return IMG;
    }

    public void setIMG(String IMG) {
        this.IMG = IMG;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getId_artist() {
        return id_artist;
    }

    public void setId_artist(int id_artist) {
        this.id_artist = id_artist;
    }

    public int getId_country() {
        return id_country;
    }

    public void setId_country(int id_country) {
        this.id_country = id_country;
    }

    public String getARTIST() {
        return ARTIST;
    }

    public void setARTIST(String ARTIST) {
        this.ARTIST = ARTIST;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(DATA);
        dest.writeString(IMG);
        dest.writeString(TITLE);
        dest.writeInt(id_artist);
        dest.writeInt(id_country);
        dest.writeString(ARTIST);
    }
}
