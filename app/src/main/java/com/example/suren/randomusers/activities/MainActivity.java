package com.example.suren.randomusers.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.suren.randomusers.R;
import com.example.suren.randomusers.adapter.PeopleAdapter;
import com.example.suren.randomusers.api.ServiceFactory;
import com.example.suren.randomusers.api.UserAPI;
import com.example.suren.randomusers.api.UserResponse;
import com.example.suren.randomusers.databinding.ActivityMainBinding;
import com.example.suren.randomusers.listeners.EndlessRecyclerOnScrollListener;
import com.example.suren.randomusers.model.User;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private PeopleAdapter mPeopleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        final UserAPI randomUserAPI = ServiceFactory.createRetrofitService(UserAPI.class,UserAPI.SERVICE_ENDPOINT);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        activityMainBinding.rvPeopleList.setLayoutManager(layoutManager);
        activityMainBinding.rvPeopleList.setHasFixedSize(true);
        List<User> mUsers = new ArrayList<>();
        mPeopleAdapter = new PeopleAdapter(MainActivity.this, mUsers);
        activityMainBinding.rvPeopleList.setAdapter(mPeopleAdapter);

        getUsers(randomUserAPI);

        activityMainBinding.rvPeopleList.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                getUsers(randomUserAPI);
            }
        });

    }

    private void getUsers(UserAPI randomUserAPI){
        randomUserAPI.getRandomUsers(20)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("People", e.getMessage());
                    }

                    @Override
                    public void onNext(UserResponse response) {
                        for (User user : response.getResults()){
                            if (!mPeopleAdapter.mUsers.contains(user)){
                                mPeopleAdapter.mUsers.add(user);
                            }
                        }
                        mPeopleAdapter.notifyDataSetChanged();
                    }
                });

    }
}
