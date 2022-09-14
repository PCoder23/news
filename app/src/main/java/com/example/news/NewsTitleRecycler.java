package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsTitleRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;
    ArrayList<NewsData> data = new ArrayList<>();
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_title_recycler);
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(NewsTitleRecycler.this);

        RequestQueue req= Volley.newRequestQueue(this);
        String url="https://newsapi.org/v2/top-headlines?sources=google-news-in&apiKey=98ff46b12d584a0abf812d41fdf42d50";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("status").equals("ok")) {
                        JSONArray jsonArray=response.getJSONArray("articles");
                        for (int i =0;i<jsonArray.length();i++){
                            JSONObject item = jsonArray.getJSONObject(i);
                            JSONObject source= item.getJSONObject("source");
                            String[] arrOfStr = item.getString("publishedAt").split("T", 2);
                            for (String a : arrOfStr) {
                                date=a;
                                break;
                            }

                            data.add(new NewsData(item.getString("urlToImage").toString(),source.getString("name"),item.getString("title"),date,item.getString("url") ));

                        }
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Toast.makeText(NewsTitleRecycler.this,"error in json Object reource"+e,Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewsTitleRecycler.this, "Error in" + error,Toast.LENGTH_SHORT).show();
                Log.d("errrr11",error.toString());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");
                return headers;
            }
        };

        req.add(jsonObjectRequest);

        adapter = new RecyclerAdapter(NewsTitleRecycler.this,data);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}