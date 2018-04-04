package anull.retrofit_test.Service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    public static <T> T createRetrofitService(final Class<T> patternClass, final String URI)
    {
        Retrofit rtNetWorker = new Retrofit.Builder()
                .baseUrl(URI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return rtNetWorker.create(patternClass);
    }
}
