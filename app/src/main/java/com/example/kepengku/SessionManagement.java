package com.example.kepengku;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManagement {


    private SharedPreferences mSharedPreference;
    //Editor for Shared preference
    private SharedPreferences.Editor mEditor;
    //context
    private Context mContext;
    //Shared pref mode
    int PRIVATE_MODE;
    //Shared pref name
    private static final String PREF_NAME = "SharedPrefLatihan";
    //Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ID_USER = "id_user";
    public static final String KEY_USERNAME= "username";
    public SessionManagement(Context mContext) {
        this.mContext = mContext;
        mSharedPreference = this.mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mSharedPreference.edit();
    }

    public void createLoginSession(String id, String email, String username, String password){
        mEditor.putBoolean(IS_LOGIN, true);
        mEditor.putString(KEY_EMAIL, email);
        mEditor.putString(KEY_PASSWORD, password);
        mEditor.putString(KEY_ID_USER, id);
        mEditor.putString(KEY_USERNAME, username);
        mEditor.commit();
    }
    public HashMap<String, String> getUserInformation(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_EMAIL, mSharedPreference.getString(KEY_EMAIL, null));
        user.put(KEY_PASSWORD, mSharedPreference.getString(KEY_PASSWORD, null));
        user.put(KEY_ID_USER, mSharedPreference.getString(KEY_ID_USER, null));
        user.put(KEY_USERNAME, mSharedPreference.getString(KEY_USERNAME, null));
        return user;
    }public boolean isLoggedIn(){
        return mSharedPreference.getBoolean(IS_LOGIN, false);
    }
    public void checkIsLogin() {

        if (!isLoggedIn()) {
            Intent i = new Intent(mContext, Login.class);// Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        }
    }
    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();

        Intent i = new Intent(mContext, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }
    public  void delete(){
        mEditor.clear();
        mEditor.commit();
    }

}
