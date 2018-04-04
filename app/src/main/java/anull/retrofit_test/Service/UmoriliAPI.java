package anull.retrofit_test.Service;

import java.util.List;

import anull.retrofit_test.Model.PostModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UmoriliAPI {
    @GET("/api/get")
    Observable<List<PostModel>> getData(@Query("name") String resName, @Query("num") int count);
}
