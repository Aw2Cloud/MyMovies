package com.example.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MenuActivity extends AppCompatActivity {


    private ListView lvMovie;
    private String[] idMovie = new String[20];
    //HashMap<String, String> idMovie = new HashMap<>();
    //ArrayList idMovie = new ArrayList();
    private String[] title = new String[20];
    //ArrayList<String[]> title = new ArrayList();
    private String[] view = new String[20];
    private String[] date = new String[20];
    private String[] pos = new String[20];

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String url = "https://api.jsonbin.io/b/618dd6084a56fb3dee0da690";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(),
                        "Tidak dapat terhubung server", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                Log.i("RESPONSE", responseData);
                try{
                    JSONObject objData = new JSONObject(responseData);
                    final JSONArray arrayMovie = objData.getJSONArray("results");

                    for (int i = 0; i < arrayMovie.length(); i++) {

                        JSONObject movieObj = arrayMovie.getJSONObject(i);

                        idMovie[i] = movieObj.getString("id");
                        title[i] = movieObj.getString("title");
                        view[i] = movieObj.getString("overview");
                        date [i] = movieObj.getString("release_date");
                        pos [i] = movieObj.getString("poster_path");

                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

        lvMovie = findViewById(R.id.lvMenu);
        MenuAdapter adapter = new MenuAdapter(this,title,view,date,pos);
        lvMovie.setAdapter(adapter);
    }
}