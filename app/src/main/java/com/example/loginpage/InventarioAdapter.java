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
import com.example.loginpage.models.ShopProduct;

import java.util.ArrayList;
import java.util.List;

public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.MyViewHolder>{
    private List<ShopProduct> values = new ArrayList<>();
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView name;
        public TextView cant;
        ImageView imageView;

        public MyViewHolder(View v) {
            super(v);
            name = (TextView)v.findViewById(R.id.nomTxt);
            cant = (TextView)v.findViewById(R.id.cantTxt);
            imageView = (ImageView)itemView.findViewById(R.id.objectPictureInv);
        }
    }


    public InventarioAdapter(List<ShopProduct> myDataset, Context context) {
        values = myDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public InventarioAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.objetos, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull InventarioAdapter.MyViewHolder holder, int position) {
        final ShopProduct product = values.get(position);
        holder.name.setText(product.getIdProduct());
        holder.cant.setText("Cantidad: " + Integer.toString(product.getAmount()));

    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
