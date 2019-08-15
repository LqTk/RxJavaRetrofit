package com.example.weathernet.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.weathernet.network.utils.GsonUtil;
import com.example.weathernet.network.utils.NetworkStatusManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    private Context context;

    public BaseObserver(){
    }

    public BaseObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (context == null){
            //没有网络的时候调用onFailed方法，并且切断与上游的联系
            if (NetworkStatusManager.getInstance().detectNetwork(context) == NetworkStatusManager.NETWORK_CLASS_UNKNOWN){
                Toast.makeText(context.getApplicationContext(), "网络出现问题", Toast.LENGTH_SHORT).show();
                d.dispose();
                return;
            }
        }
    }

    @Override
    public void onNext(T t) {
        Log.d("result", t.toString());
//        String json = GsonUtil.objectToJson(t);
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailed(e.toString());
        Log.d("result", e.toString());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);
    public abstract void onFailed(String t);
}
