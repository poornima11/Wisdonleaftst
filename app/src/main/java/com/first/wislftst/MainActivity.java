package com.first.wislftst;

import android.os.AsyncTask;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.first.wislftst.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {


    SwipeRefreshLayout swipeRefreshLayout;
    ListView listView;
    String url = "https://picsum.photos/v2/list?page=2&limit=20";
    ArrayList<ListModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        listView = (ListView) findViewById(R.id.listView);
        getData();

        // Implementing setOnRefreshListener on SwipeRefreshLayout*/
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                shuffleListItems();
            }
        });
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject responseObj = response.getJSONObject(i);

                        String id = responseObj.getString("id");
                        String author = responseObj.getString("author");
                        String url = responseObj.getString("download_url");
                        arrayList.add(new ListModel(id,author,url));

                        settingAdapter(arrayList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void settingAdapter(ArrayList<ListModel> arrayList) {
        MylistAdapter arrayAdapter = new MylistAdapter(arrayList,MainActivity.this);
        listView.setAdapter(arrayAdapter);

    }


    public void shuffleListItems() {
        // Shuffling the arraylist items on the basis of system time
        Collections.shuffle(arrayList, new Random(System.currentTimeMillis()));
       settingAdapter(arrayList);
    }
}

