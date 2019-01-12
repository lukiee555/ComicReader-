package com.lokeshsoni.comicreader.Common;

import com.lokeshsoni.comicreader.Retrofit.IComicAPI;
import com.lokeshsoni.comicreader.Retrofit.RetrofitClient;

import retrofit2.Retrofit;

public class Common {
    public static IComicAPI getAPI(){
        return  RetrofitClient.getInstance().create(IComicAPI.class);


    }
}
