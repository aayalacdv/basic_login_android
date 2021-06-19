package com.example.loginpage;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;

public class ReciclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView objeto, descripcion;
        ImageView fotoobjeto;
        objeto=(TextView)itemView.findViewById(R.id.tvidobjeto);
        descripcion=(TextView)itemView.findViewById(R.id.tvdescripcion);
        fotoobjeto=(ImageView)itemView.findViewById(R.id.imgobjeto);
    }

    public List<objectmodel> objectList;

    public ReciclerViewAdaptador(List<objectmodel> objectList) {
        this.objectList = objectList;
    }

    @Override
    public RecyclerViewAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutobjetos, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.objeto.setText(objectList.get(position).getObjeto());
        holder.descripcion.setText(objectList.get(position).getDesripcion());
        holder.fotoobjeto.setImageResource(objectList.get(position).getImgObjeto());
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
