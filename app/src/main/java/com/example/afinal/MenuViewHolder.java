package com.example.afinal;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtMenuName;
    public TextView txtMenuDate;
    public TextView txtMenuPrice;
    public TextView txtMenuDescription;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuName = (TextView) itemView.findViewById(R.id.menu_name);
        txtMenuDate = (TextView) itemView.findViewById(R.id.menu_date);
        //txtMenuPrice = (TextView) itemView.findViewById(R.id.menu_price);
        txtMenuDescription = (TextView) itemView.findViewById(R.id.menu_description);
        itemView.setOnClickListener(this);

    }

    private void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getBindingAdapterPosition(), false);
        view.getContext().startActivity(new
                Intent(view.getContext(), FoodDetail.class));
    }
}

