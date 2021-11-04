package com.example.kepengku.other;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.kepengku.MainActivity;
import com.example.kepengku.R;
import com.example.kepengku.entity.ListKepeng;
import com.example.kepengku.helper.SqliteHelper;

import java.util.ArrayList;

public class KasAdapter extends RecyclerView.Adapter<KasViewHolder> implements Filterable {

    private Context context;
    private ArrayList<ListKepeng> listKas;
    private ArrayList<ListKepeng> mArrayList;
    private SqliteHelper sqlitehelper;
    private OnItemClickCallback onItemClickCallback;
    private CustomFilter mFilter;

    public KasAdapter(Context context, ArrayList<ListKepeng> listKas) {
        this.context    = context;
        this.listKas    = listKas;
        this.mArrayList = listKas;
        sqlitehelper    = new SqliteHelper(context);
    }

    @NonNull
    @Override
    public KasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kas_in_out, parent, false);
        return new KasViewHolder(view);
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setOnItemCustomFilter(CustomFilter CustomFilter) {
        this.mFilter = CustomFilter;
    }

    @Override
    public void onBindViewHolder(@NonNull final KasViewHolder holder, int position) {
        final ListKepeng listKepeng = listKas.get(position);

        holder.textId.setText(listKepeng.getKas_id());
        holder.textType.setText(listKepeng.getKas_type());
        holder.textInfo.setText(listKepeng.getKas_info());
        holder.textTotal.setText(listKepeng.getKas_total());
        holder.textDate.setText(listKepeng.getKas_date());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(v);
            }
        });

        if(!MainActivity.navigation.equals("")){
            holder.textType.setTextColor(MainActivity.navigation.equals("pengeluaran") ?
                    Color.parseColor("#E92539") : Color.parseColor("#2CA748"));
        }
    }

    @Override
    public int getItemCount() {
        return listKas.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public interface OnItemClickCallback {
        void onItemClicked(View v);
    }

    public abstract static class CustomFilter extends Filter{
        protected abstract void CustomFilter(KasAdapter kasadapter);
        @Override
        protected abstract FilterResults performFiltering(CharSequence constraint);
        @Override
        protected abstract void publishResults(CharSequence constraint, FilterResults results);
    }

}
