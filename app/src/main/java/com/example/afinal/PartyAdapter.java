package com.example.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PartyAdapter extends RecyclerView.Adapter<PartyAdapter.MyViewHolder>{
    Context context;

    ArrayList<Parties> list;

    public PartyAdapter(Context context, ArrayList<Parties> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PartyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.party_item, parent, false);
        return new PartyAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyAdapter.MyViewHolder holder, int position) {

        Parties parties = list.get(position);
        holder.PartyName.setText(parties.getPartyName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView PartyName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            PartyName = itemView.findViewById(R.id.party_name);
        }
    }
}
