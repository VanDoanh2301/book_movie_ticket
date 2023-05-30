package com.example.bookmovieticket.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.model.Chair;
import com.example.bookmovieticket.model.Content;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private Context context;

    private List<Chair> chairs;

    public RoomAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Chair> chairs) {
        this.chairs = chairs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_room, parent, false);
        return new RoomViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Chair chair = chairs.get(position);
        if (chair == null) {
            return;
        }

        holder.txtChair.setText(chair.getLocation());

        if (position >= 0 && position <= 15) {
            holder.cardView.setBackgroundColor(Color.RED);
            holder.txtChair.setTextColor(Color.WHITE);
        } else {
            holder.cardView.setBackgroundColor(R.color.custom_select_navigationbt);
            holder.txtChair.setTextColor(Color.WHITE);
        }
        if(position == 24) {
            holder.cardView.setBackgroundResource(R.drawable.custom_close_chair);
        }
        if(position == 35) {
            holder.cardView.setBackgroundResource(R.drawable.custom_close_chair);
        }
        if(position == 55) {
            holder.cardView.setBackgroundResource(R.drawable.custom_close_chair);
        }
        if(position == 44) {
            holder.cardView.setBackgroundResource(R.drawable.custom_close_chair);
        }

    }

    @Override
    public int getItemCount() {
        return chairs.size();
    }

    public  class  RoomViewHolder extends RecyclerView.ViewHolder {
        public TextView txtChair;
        public CardView cardView;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            txtChair = itemView.findViewById(R.id.txt_chair);
            cardView = itemView.findViewById(R.id.card_view_chair);
        }
    }
}
