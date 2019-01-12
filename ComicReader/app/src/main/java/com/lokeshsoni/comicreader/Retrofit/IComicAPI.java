package com.lokeshsoni.comicreader.Retrofit;

import com.lokeshsoni.comicreader.Model.Banner;
import com.lokeshsoni.comicreader.Model.Comic;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IComicAPI {

    @GET("banner")
    Observable<List<Banner>> getBannerList();

    @GET("comic")
    Observable<List<Comic>> getComicList();
}
