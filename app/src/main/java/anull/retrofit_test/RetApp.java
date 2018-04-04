package anull.retrofit_test;

import android.app.Application;

import anull.retrofit_test.Service.ApiFactory;
import anull.retrofit_test.Service.UmoriliAPI;

public class RetApp extends Application {

    private static UmoriliAPI umApi;

    @Override
    public void onCreate() {
        super.onCreate();
        umApi = ApiFactory.createRetrofitService(UmoriliAPI.class,"https://umorili.herokuapp.com");
    }

    public static UmoriliAPI getUmApi() {
        return umApi;
    }
}
