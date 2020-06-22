package com.example.nestedrecyclersearch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Designed and Developed by Mohammad suhail ahmed on 24/02/2020
 */
public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.SubMenuViewHolder> {
    private List<Menu> menus;
    private Context context;
    private MainActivity menuActivity;
    private Date date;
    private DateFormat dateFormat;
    private int orderid;

    public class SubMenuViewHolder extends  RecyclerView.ViewHolder
    {
        private TextView name,price,count;
        private ImageView type,add,minus;
        private Button buttonadd;
        private int itemcount=0;
        private RelativeLayout counterLayout;


        public SubMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.menuname);
            price = itemView.findViewById(R.id.price);
            count = itemView.findViewById(R.id.counter);
            type = itemView.findViewById(R.id.menutype);
            add = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            buttonadd = itemView.findViewById(R.id.add);
            counterLayout = itemView.findViewById(R.id.counterlayout);
        }
    }
    public SubMenuAdapter(Context context,List<Menu> menus)
    {
        this.context = context;
        this.menus = menus;
        this.menuActivity = (MainActivity) context;
        this.date = new Date();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.orderid = (int) (date.getTime()/1000);
    }
    @NonNull
    @Override
    public SubMenuAdapter.SubMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_menu_item,parent,false);
        return new SubMenuAdapter.SubMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubMenuAdapter.SubMenuViewHolder holder, int position) {
        Menu menu = menus.get(position);
        holder.name.setText(menu.getName());
        holder.price.setText(String.valueOf(menu.getPrice()));
        if (menu.getType().equals("veg"))
        {

            holder.type.setImageResource(R.drawable.veg);
        }
        else
        {
            holder.type.setImageResource(R.drawable.non_veg);
        }
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }
}
