package kyf.loveapplication.data.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import kyf.loveapplication.BuildConfig;
import kyf.loveapplication.data.constant.ServerUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by a55 on 2017/11/2.
 */

public class RetrofitFactory {
    private static final     OkHttpClient           okHttpClient;
    private static final HttpLoggingInterceptor loggingIntercepter;

    static {
        loggingIntercepter = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            loggingIntercepter.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(loggingIntercepter)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    public static <T> T createService(Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerUrl.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(clazz);
    }
}
