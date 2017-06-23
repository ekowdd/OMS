package com.example.odisys.oms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.odisys.oms.Api.ApiUrl;
import com.example.odisys.oms.R;
import com.example.odisys.oms.fragment.DashboardFragment;
import com.example.odisys.oms.fragment.DetailProductFragmet;
import com.example.odisys.oms.fragment.ProductFragment;
import com.example.odisys.oms.fragment.StoreFragment;
import com.example.odisys.oms.model.ProductModel;
import com.example.odisys.oms.model.Users;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    HttpURLConnection con;
    URL mUrl;
    List<Users> list;
    static int CAMERA_REQEST = 0;
    Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Fragment fragment = new DashboardFragment();
        setTitle("Home");

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = LayoutInflater.from(this)
                .inflate(R.layout.nav_header_main, navigationView, false);
        navigationView.addHeaderView(header);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
           Intent i = new Intent(this, ScannerActivity.class);i.putExtra("type", "scanConfirm");
            startActivityForResult(i, CAMERA_REQEST);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment;
        FragmentManager fragmentManager;
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fragment = new DashboardFragment();
            setTitle("Home");
            fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame, fragment)
                    .commit();
            this.invalidateOptionsMenu();
        } else if(id==R.id.store){
            setTitle("Product");
            fragment = new StoreFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame, fragment)
                    .commit();
        }else if(id == R.id.report){
            setTitle("Report");
            Intent intent = new Intent(this,SOActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_send) {

            Toast.makeText(getApplicationContext(),"Cyeee Masih Mahasiswa", Toast.LENGTH_SHORT).show();
            fragment = new DetailProductFragmet();
            fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame, fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private class UserByNik extends AsyncTask<Void,Void,String>{
        JSONObject obj;
        JSONArray arr;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String url = "";
            try {

                mUrl = new URL(ApiUrl.URL_USER);
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                con = (HttpURLConnection) mUrl.openConnection();
                url = mUrl.getQuery();
                url.replaceAll("","%20");
                con.setConnectTimeout(15000);
                con.setReadTimeout(10000);
                con.setRequestMethod("GET");
            } catch (IOException ex) {
                ex.printStackTrace();
                return ex.toString();
            }
            try {
                int response_code = con.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream is = con.getInputStream();
                    BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bf.readLine()) != null) {
                        sb.append(line);
                    }
                    return (sb.toString());
                } else {
                    return "unsuccessfull";
                }
            } catch (IOException exx) {
                exx.printStackTrace();
                return exx.toString();
            } finally {
                con.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String sb) {
            super.onPostExecute(sb);
            Log.d("Response", sb);
            list = new ArrayList<Users>();
            try {
                obj = new JSONObject(sb);
                
            } catch (Exception ec) {
                ec.printStackTrace();
            }
        }
    }
}
