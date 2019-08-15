package com.example.weathernet;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weathernet.network.BaseObserver;
import com.example.weathernet.network.HttpMethod;
import com.example.weathernet.network.NetWorkService;
import com.example.weathernet.network.RxSchedulers;

public class MainActivity extends AppCompatActivity {
    NetWorkService service;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        service = HttpMethod.getInstance().create(NetWorkService.class);
        service.getNowDayWeather()
                .compose(RxSchedulers.<WeatherResult>compose(context))
                .subscribe(new BaseObserver<WeatherResult>(context) {
                    @Override
                    public void onSuccess(WeatherResult weatherResult) {
                        Toast.makeText(context,weatherResult.toString(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailed(String t) {
                        Toast.makeText(context,t,Toast.LENGTH_LONG).show();
                    }
                });
    }
}
