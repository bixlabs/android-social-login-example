package com.bixlabs.socialloginexample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bixlabs.socialloginexample.api.Api;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.res.StringRes;

@EActivity
public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    @StringRes(R.string.client_web_id)
    String clientId;

    GoogleSignInOptions gso;
    GoogleApiClient gac;

    final int RC_SIGN_IN = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(Scopes.EMAIL))
                .requestServerAuthCode(clientId)
                .requestIdToken(clientId)
                .build();
        gac = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Click(R.id.google_sign_in_button)
    public void onClickSignIn(View v) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(gac);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Click(R.id.sign_out_button)
    public void onClickSignOut(View v) {
        doLogout();
    }

    @Background
    public void doLogin(GoogleSignInResult result) {
        Api.googleLogin(result.getSignInAccount());
    }

    @Background
    public void doLogout() {
        Auth.GoogleSignInApi.revokeAccess(gac).await().getStatus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                doLogin(result);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
