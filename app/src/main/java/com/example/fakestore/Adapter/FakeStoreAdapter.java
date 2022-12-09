package com.example.fakestore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.fakestore.Model.FakeStore;
import com.example.fakestore.R;

import java.util.List;

public class FakeStoreAdapter extends RecyclerView.Adapter<FakeStoreAdapter.ProductHolder>{

    List<FakeStore> fakeStoreList;
    public FakeStoreAdapter(List<FakeStore> fakeStoreList) {this.fakeStoreList = fakeStoreList;}

    public void setFilteredList(List<FakeStore> filteredList){
        this.fakeStoreList = filteredList;
        notifyDataSetChanged();
    }

    onClickInterface interfaceseeee;

    public FakeStoreAdapter(onClickInterface interfaceseeee) {
        this.interfaceseeee = interfaceseeee;
    }

    public void setFakeStoreList(List<FakeStore> fakeStoreList) {

        this.fakeStoreList = fakeStoreList;


    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemstyle, parent, false);
        return new ProductHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {


        holder.title.setText(fakeStoreList.get(position).getTitle());
        holder.desc.setText(fakeStoreList.get(position).getDescription());

        Glide.with(holder.itemView.getContext()).load(fakeStoreList.get(position).getImage()).centerCrop().into(holder.view);

    }

    @Override
    public int getItemCount() {
        return fakeStoreList.size();
    }

    class ProductHolder extends ViewHolder implements View.OnClickListener{


        TextView title, desc;
        ImageView view;


        public ProductHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            view = itemView.findViewById(R.id.imageView);
            title.setOnClickListener(this);
            desc.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            interfaceseeee.ClickItem(fakeStoreList, getAdapterPosition());

        }
    }

    public interface onClickInterface{
        void ClickItem(List<FakeStore> fakeStores, int position);


    }
}
