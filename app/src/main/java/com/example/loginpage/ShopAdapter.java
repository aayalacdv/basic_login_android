package com.example.loginpage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpage.models.Producto;
import com.example.loginpage.models.ShopProduct;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder>{
private List<Producto> values = new ArrayList<>();
private Context context;
private String userId;
private String idProduct;
private String urlProduct;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView name;
    public TextView price;
    ImageView imageView;
    Button comprarBtn;
    public TextView producto;

    public MyViewHolder(View v) {
        super(v);
        name = (TextView)v.findViewById(R.id.tvidobjeto);
        price = (TextView)v.findViewById(R.id.tvdescripcion);
        imageView = (ImageView)itemView.findViewById(R.id.objectPicture);
        comprarBtn = (Button) itemView.findViewById(R.id.comprarBtn);
        producto = (TextView) itemView.findViewById(R.id.tvidobjeto);
    }

    public void setOnClickListener(){
        comprarBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, CompraActivity.class);
        intent.putExtra("producto", producto.getText().toString() );
        intent.putExtra("name", userId);
        intent.putExtra("url", urlProduct);
        context.startActivity(intent);
    }
}

    public ShopAdapter(List<Producto> myDataset, Context context, String user) {
        values = myDataset;
        this.context = context;
        userId = user;
    }

    @NonNull
    @Override
    public ShopAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.layoutobjetos, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Producto product = values.get(position);
        holder.name.setText(product.getId());
        holder.price.setText(product.getPrice());
        holder.setOnClickListener();
        Glide.with(this.context).load(values.get(position).getUrl()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return values.size();
    }

}

