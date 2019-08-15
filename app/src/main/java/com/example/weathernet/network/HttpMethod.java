package com.example.weathernet.network;

import com.example.weathernet.network.utils.SSLSocketFactoryUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpMethod {
    private static volatile OkHttpClient client;
    private static volatile Retrofit retrofit;
    public static OkHttpClient getClient(){
        if (client == null){
            //log日志
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置https协议访问
            client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("Accept-Encoding","gzip, deflate")
                                    .addHeader("Accept-Language","zh-CN,zh;q=0.9")
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .addInterceptor(loggingInterceptor)
                    .sslSocketFactory(SSLSocketFactoryUtils.createSSLSocketFactory(),SSLSocketFactoryUtils.createTrustAllManager())
                    .hostnameVerifier(new SSLSocketFactoryUtils.TrustAllHostnameVerifier())
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5,TimeUnit.MINUTES)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }

    public static Retrofit getInstance(){
        if (client == null){
            synchronized (HttpMethod.class){
                getClient();
            }
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(NetWorkService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
}
