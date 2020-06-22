package com.example.nestedrecyclersearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Designed and Developed by Mohammad suhail ahmed on 24/02/2020
 */
public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder> {
    private List<CacheMenuRes> cacheMenuRes;
    private Context context;

    public class MainMenuViewHolder extends RecyclerView.ViewHolder
    {
        private RecyclerView subMenuRecycler;
        private TextView subcat;

        public MainMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            subMenuRecycler = itemView.findViewById(R.id.submenurecycler);
            subcat = itemView.findViewById(R.id.subcategoryname);
        }
    }

    public MainMenuAdapter(Context context,List<CacheMenuRes> cacheMenuRes)
    {
        this.context = context;
        this.cacheMenuRes = cacheMenuRes;
    }

    @NonNull
    @Override
    public MainMenuAdapter.MainMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_item,parent,false);
        return new MainMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuAdapter.MainMenuViewHolder holder, int position) {
        CacheMenuRes md = cacheMenuRes.get(position);
        holder.subcat.setText(md.getSubCategoryName());

        SubMenuAdapter subMenuAdapter = new SubMenuAdapter(context,md.getMenus());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.subMenuRecycler.setLayoutManager(layoutManager);
        holder.subMenuRecycler.setItemViewCacheSize(20);

        holder.subMenuRecycler.setAdapter(subMenuAdapter);


    }

    @Override
    public int getItemCount() {
        return cacheMenuRes.size();
    }
    public void setCacheMenuRes(List<CacheMenuRes> cacheMenuRes)
    {
        this.cacheMenuRes = cacheMenuRes;
        notifyDataSetChanged();
    }
}
