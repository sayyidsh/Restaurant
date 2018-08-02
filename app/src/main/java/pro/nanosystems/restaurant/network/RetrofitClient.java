package pro.nanosystems.restaurant.network;



import java.util.List;

import pro.nanosystems.restaurant.model.Menu;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitClient {
    @GET("fooddelivery_menu.php")
    Call<List<Menu>> getMenus();
}
