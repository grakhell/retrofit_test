package anull.retrofit_test.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import anull.retrofit_test.R;
import anull.retrofit_test.RetApp;
import anull.retrofit_test.Adapter.PostAdapter;
import anull.retrofit_test.Model.PostModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends Activity {

    RecyclerView rvMain;
    private List<PostModel> mPosts;
    private final CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPosts = new ArrayList<>();

        rvMain = findViewById(R.id.posts_recycle_view);
        LinearLayoutManager llManager = new LinearLayoutManager(this);
        rvMain.setLayoutManager(llManager);

        PostAdapter adapter = new PostAdapter(mPosts);
        rvMain.setAdapter(adapter);

        mDisposables.add( RetApp.getUmApi().getData("bash",100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( response -> {mPosts.addAll(response);
                    rvMain.getAdapter().notifyDataSetChanged();},
                        e -> { Log.e("Retrofit_test", e.getStackTrace().toString());
                        Toast.makeText(MainActivity.this , e.getLocalizedMessage(),Toast.LENGTH_LONG).show();}
                    ));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposables.dispose();
    }
}
