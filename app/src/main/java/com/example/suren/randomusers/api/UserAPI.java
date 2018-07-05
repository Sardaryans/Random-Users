package com.example.suren.randomusers.api;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserAPI {
    String SERVICE_ENDPOINT = "https://randomuser.me/";

    @GET("api/")
    rx.Observable<UserResponse> getRandomUsers(@Query("results") Integer results);

//    class Factory {
//        public static UserAPI create() {
//            return ServiceFactory.createRetrofitService(UserAPI.class, SERVICE_ENDPOINT);
//        }
//    }
}
