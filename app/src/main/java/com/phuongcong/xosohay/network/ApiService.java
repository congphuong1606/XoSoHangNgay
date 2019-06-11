package com.phuongcong.xosohay.network;


import com.phuongcong.xosohay.model.Story;
import com.phuongcong.xosohay.model.dto.Result;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Ominext on 9/28/2017.
 */

public interface ApiService {


    @POST("exec")
    Observable<Result> callApi(@Query("action") String action, @Body Story jsonBody);
/*
    @GET("exec")
    Observable<Story> findUser(@Query("action") String action,
                              @Query("email") String name);

    //    @GET("exec")
//    Observable<ArrayList<MyLocation>> readLocation(@Query("action") String readLocation, @Query("userId") String userId);
    @POST("exec")
    Observable<Result> insertCurentLocation(@Query("action") String insertLocation,
                                            @Query("email") String email,
                                            @Query("lat") String lat,
                                            @Query("lng") String lng);

    @POST("exec")
    Observable<Result> creatUser(@Query("action") String insert,
                                 @Query("email") String accName,
                                 @Query("pass") String accPass,
                                 @Query("name") String name,
                                 @Query("avatar") String avatar);

    @POST("exec")
    Observable<Result> addMemberToCricle(@Query("action") String insert,
                                         @Query("uEmail") String uemail,
                                         @Query("fEmail") String email);

    @POST("exec")
    Observable<Result> updatePass(@Query("action") String insert,
                                  @Query("email") String email,
                                  @Query("oldpass") String oldpass,
                                  @Query("newpass") String newpass);


    @POST("exec")
    Observable<Result> updateName(@Query("action") String insert,
                                  @Query("email") String email,
                                  @Query("name") String name);
    @POST("exec")
    Observable<Result> updateAvatar(@Query("action") String insert,
                                    @Query("email") String email,
                                    @Query("avatar") String avatarUrl);

*/

}
