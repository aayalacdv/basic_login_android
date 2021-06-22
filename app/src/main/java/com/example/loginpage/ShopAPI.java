package com.example.loginpage;

import com.example.loginpage.models.Producto;
import com.example.loginpage.models.ShopProduct;
import com.example.loginpage.models.Usuario;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ShopAPI {
    String ENDPOINT = "http://10.0.2.2:8080/myapp/shop/";

    @GET("productList")
    Call<List<Producto>> getProductList();

    @GET("productsUser/{name}")
    Call<List<Producto>> getProductsByUser(@Path("name") String owner);

    @POST("buyProduct")
    Call<ResponseBody> buyProduct(@Body ShopProduct compra);
}
