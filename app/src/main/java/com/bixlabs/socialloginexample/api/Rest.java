package com.bixlabs.socialloginexample.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Rest {

    @POST("Consumers/login/google")
    Call<ResponseBody> loginGoogle(@Body LoginGoogleRequest request);
}
