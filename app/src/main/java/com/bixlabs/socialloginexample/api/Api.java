package com.bixlabs.socialloginexample.api;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api {

    public static Rest create() {
        Retrofit fit = new Retrofit.Builder()
                .baseUrl("http://0.0.0.0:3000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return fit.create(Rest.class);
    }

    public static void googleLogin(GoogleSignInAccount account) {
        LoginGoogleRequest request = new LoginGoogleRequest(account.getIdToken(), account.getServerAuthCode());

        try {
            Api.create().loginGoogle(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
