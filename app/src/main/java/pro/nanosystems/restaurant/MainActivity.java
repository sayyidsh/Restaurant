package pro.nanosystems.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import pro.nanosystems.restaurant.adapter.MenuAdapter;
import pro.nanosystems.restaurant.model.Menu;
import pro.nanosystems.restaurant.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView meuList;
    private final String URL = "http://192.168.1.130/resturant/pull/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meuList = findViewById(R.id.menuList);
        Retrofit.Builder builder =new Retrofit.Builder()
                //this is the base URL. URL we created in interface will be added to it
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RetrofitClient client = retrofit.create(RetrofitClient.class);
        Call<List<Menu>> call =client.getMenus();
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                List<Menu>menus= response.body();
                meuList.setAdapter(new MenuAdapter(MainActivity.this, menus));
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.getMessage().toString());
            }
        });

    }
}
