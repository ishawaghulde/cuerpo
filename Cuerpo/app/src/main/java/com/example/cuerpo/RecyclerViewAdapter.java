package com.example.cuerpo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter  {
    private List<String> items = new ArrayList<>();


    private static View.OnClickListener onItemClickListener;

    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    public void setItems(List<String> itemlist) {
        items = itemlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((TextViewHolder) holder).bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    private static class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;



        private TextViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.desc);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }

        private void bind(String text) {
            textView.setText(text);
        }
    }

    public void filterList(ArrayList<String> filteredList){
        items = filteredList;
        notifyDataSetChanged();
    }
}
