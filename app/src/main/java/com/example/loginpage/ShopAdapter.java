package com.example.loginpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpage.models.Producto;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder>{
private List<Producto> values = new ArrayList<>();
private Context context;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView price;
    ImageView imageView;

    public MyViewHolder(View v) {
        super(v);
        name = (TextView)v.findViewById(R.id.tvidobjeto);
        price = (TextView)v.findViewById(R.id.tvdescripcion);
        imageView = (ImageView)itemView.findViewById(R.id.imgobjeto);
    }
}

    public void add(int position, Producto item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public ShopAdapter(List<Producto> myDataset, Context context) {
        values = myDataset;
        this.context = context;
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
        Glide.with(this.context).load(values.get(position).getUrl()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return values.size();
    }
}

