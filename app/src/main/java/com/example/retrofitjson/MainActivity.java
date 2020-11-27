package com.example.retrofitjson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.example.retrofitjson.model.MyModel;
import com.example.retrofitjson.model.entry.Entry1;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://itunes.apple.com/";
    // the base URL of the JSON file
    RecyclerView recyclerViewApps;
    List<Entry1> apps_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewApps = findViewById(R.id.recyclerViewApps);
        apps_list = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ITunesAPI iTunesAPI = retrofit.create(ITunesAPI.class);
        Call<MyModel> call = iTunesAPI.getData();

       call.enqueue(new Callback<MyModel>() {
           @Override
           public void onResponse(Call<MyModel> call, Response<MyModel> response) {
               ArrayList<Entry1> entries = response.body().getFeed().getEntries();

               //Log.d("TAG", entries.toString());

               for (Entry1 entry : entries)
               {
                   apps_list.add(entry);

               }
               putDataIntoRecyclerView(apps_list);
           }

           @Override
           public void onFailure(Call<MyModel> call, Throwable t) {
               Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
           }
       });


    }

    private void putDataIntoRecyclerView(List<Entry1> apps_list) {
        AppsAdapter appsAdapter = new AppsAdapter(this, apps_list);
        recyclerViewApps.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewApps.setAdapter(appsAdapter);
    }

}