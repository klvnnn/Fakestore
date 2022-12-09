package com.example.fakestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fakestore.Adapter.FakeStoreAdapter;
import com.example.fakestore.Model.FakeStore;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements FakeStoreAdapter.onClickInterface {

    FakeStoreAdapter mAdapter;
    List<FakeStore> fakeStoreList;
    RecyclerView recyclerView;
    private SearchView searchView;

    String vtitle, vimage, vdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fileterList(newText);
                return true;
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        mAdapter = new FakeStoreAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         fakeStoreList = new ArrayList<>();

        ApiCall();
    }

    private void fileterList(String newText) {
        List<FakeStore> filteredList = new ArrayList<>();
        for (FakeStore fakeStore : fakeStoreList){
            if (fakeStore.getTitle()){

            }
        }
    }

    private void ApiCall() {

       String URL = "https://fakestoreapi.com/products";

       StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {


           @Override
           public void onResponse(String response) {


               try {

                   JSONArray jsonObject = new JSONArray(response);

                   for (int i = 0; i < jsonObject.length(); i++) {


                       JSONObject getData = jsonObject.getJSONObject(i);

                        vtitle = getData.getString("title");
                        vdesc = getData.getString("description");
                        vimage = getData.getString("image");


                       FakeStore fakeStore = new FakeStore(vtitle, vdesc, vimage);
                       fakeStoreList.add(fakeStore);


                   }


                   mAdapter.setFakeStoreList(fakeStoreList);
                   recyclerView.setAdapter(mAdapter);

               } catch (JSONException e) {
                   e.printStackTrace();
               }


           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    @Override
    public void ClickItem(List<FakeStore> fakeStores, int position) {

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("image", fakeStores.get(position).getImage());
        intent.putExtra("title", fakeStores.get(position).getTitle());
        intent.putExtra("desc", fakeStores.get(position).getDescription());
        startActivity(intent);
    }
}