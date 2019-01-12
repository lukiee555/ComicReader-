package com.lokeshsoni.comicreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lokeshsoni.comicreader.Adapter.MySliderAdapter;
import com.lokeshsoni.comicreader.Common.Common;
import com.lokeshsoni.comicreader.Model.Banner;
import com.lokeshsoni.comicreader.Retrofit.IComicAPI;
import com.lokeshsoni.comicreader.Service.PicassoImageLoadingService;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity {

    Slider slider;
    IComicAPI iComicAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init API
        iComicAPI = Common.getAPI();

        //View
        slider = findViewById(R.id.banner_slider);
        Slider.init(new PicassoImageLoadingService());

        fetchBanner();

    }

    private void fetchBanner() {

        compositeDisposable.add(iComicAPI.getBannerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Banner>>() {
                    @Override
                    public void accept(List<Banner> banners) throws Exception {
                        slider.setAdapter(new MySliderAdapter(banners));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this,"Error While Loading Banner",Toast.LENGTH_LONG).show();
                    }
                }));
    }
}
