package com.example.odisys.oms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.odisys.oms.Api.ApiUrl;
import com.example.odisys.oms.R;
import com.example.odisys.oms.model.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private ImageView icon;
    private Animation ianimRotate;
    private EditText username, password;
    private ProgressDialog mDialog;
    private LinearLayout lay1,lay2;
    private JSONObject obj;
    private JSONArray arr;
    private LoginSubmit loginTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ianimRotate = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        ianimRotate.getStartOffset();
        lay1 = (LinearLayout)findViewById(R.id.lay_1);
        lay2 = (LinearLayout)findViewById(R.id.lay_2);
        icon = (ImageView)findViewById(R.id.icon);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.login){
//            icon.setAnimation(ianimRotate);
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            authuserLogin();
        }
    }

    private void clear_form(){
        username.setText("");
        password.setText("");
    }

    private void authuserLogin(){
        if (loginTask !=null){
            return;
        }else{
            username.setError(null);
            password.setError(null);

            String mUsername = username.getText().toString();
            String mPassword = password.getText().toString();
            boolean cancel = false;
            View mView = null;
            if (TextUtils.isEmpty(mPassword) && isPasswordValid(mPassword)){
                password.setError("This Password is Not short");
                mView = password;
                cancel = true;
            }
            if (TextUtils.isEmpty(mUsername)){
                username.setError("Username Fields is Require");
                mView = username;
                cancel = true;
            }else if(!isUsernameValid(mUsername)){
                username.setError("username Or user id");
                mView = username;
                cancel = true;
            }

            if (cancel){
                mView.requestFocus();
            }else{
                showProgressDialog();
//                loginTask = new LoginSubmit(mUsername,mPassword);
//                loginTask.execute((Void)null);
                loginAuthUser();
            }

        }
    }
    private boolean isUsernameValid(String mUser){
        return mUser.length() >0;
    }
    private boolean isPasswordValid(String mPass){
        return mPass.length() > 4;
    }
    private class LoginSubmit extends AsyncTask<Void,Void,String>{
        private final String mUser;
        private final String mPasswd;
        private String response = "";
        private String status = "";

        LoginSubmit(String user, String pass){
            mUser = user;
            mPasswd = pass;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
            lay1.setVisibility(View.GONE);
            lay2.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dismissProgressDialog();
        }
    }

    private void loginAuthUser(){
        String url = "";
        showProgressDialog();
        StringRequest request = new StringRequest(Request.Method.POST, ApiUrl.URL_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dismissProgressDialog();

                if (response.equalsIgnoreCase("Success")){
                    SharedPreferences pre = getSharedPreferences("isLoggedIn", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pre.edit();
                    editor.putString("nik",username.getText().toString());
                    editor.apply();

                    Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                    mIntent.putExtra("nik",username.getText().toString());
                    startActivity(mIntent);
                }else{
                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG)
                        .show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > doPostLoginTask = new HashMap<>();
                doPostLoginTask.put("nik",username.getText().toString());
                doPostLoginTask.put("password",password.getText().toString());
                return doPostLoginTask;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private void showProgressDialog(){
        if (mDialog==null){
            mDialog = new ProgressDialog(this);
            mDialog.setTitle(null);
            mDialog.setMessage("Waiting...");
            mDialog.setCancelable(false);
        }
        mDialog.show();
    }

    private void dismissProgressDialog(){
        if (mDialog !=null || mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

}
