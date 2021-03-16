package com.e.basevisual.utils;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.basevisual.Objects.Usuari;
import com.e.basevisual.R;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.BindViews;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyMainViewHolder>  {
    private List<Usuari> mainInfo;
    public RecyclerAdapter(List<Usuari> mainInfo) {
        this.mainInfo = mainInfo;
    }
    public static class MyMainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nick)
        TextView tvUsuari;
        @BindView(R.id.posicio)
        TextView posicio;
        @BindView(R.id.puntuacio)
        TextView puntuacio;
        private MyMainViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
    @NonNull
    @Override
    public MyMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new MyMainViewHolder(v2);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull final MyMainViewHolder holder2, int position) {
       // final Usuari pista2=mainInfo.get(position);
       // final Usuari pista3=mainInfo.get(mainInfo.lastIndexOf(pista2));
        holder2.posicio.setText(Integer.toString(position+1));
        holder2.tvUsuari.setText(mainInfo.get(position).getNick());
        String puntuacioMitjanaV=String.format("%.2f",mainInfo.get(position).getPuntuacio_mitjana());
        holder2.puntuacio.setText(puntuacioMitjanaV);
    }
    @Override
    public int getItemCount() {
        return mainInfo.size();
    }
}
