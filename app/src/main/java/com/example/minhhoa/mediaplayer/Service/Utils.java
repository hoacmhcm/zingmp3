package com.example.minhhoa.mediaplayer.Service;



public class Utils {
//    public static final String BASE_URL = "https://hoa-14110071-mediaplayer.herokuapp.com/";
   public static String BASE_URL = "http://192.168.0.105:8000/";
    // đường dẫn tới webservice
    public static Service getService() {   // Hàm get đương dẫn của service
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
