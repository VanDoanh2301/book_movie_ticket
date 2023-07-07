package com.example.bookmovieticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookmovieticket.R;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.Content;
import com.example.bookmovieticket.model.ShowTime;
import com.example.bookmovieticket.model.Ticket;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private Context context;
    private List<Ticket> tickets;

    private OnItemClickListener listener;

    private int selectedPosition = RecyclerView.NO_POSITION;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public TicketAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Ticket> tickets) {
        this.tickets = tickets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.txtNameMovie.setText(ticket.getMovie());
        holder.txtLocation.setText(ticket.getLocation());
        holder.txtTime.setText(ticket.getShowTimeID());

        holder.cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return (tickets == null) ? 0: tickets.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public class TicketViewHolder extends RecyclerView.ViewHolder {
        private ShapeableImageView imgMovie;
        private TextView txtNameMovie, txtTime, txtLocation;
        private CardView cardView;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameMovie = itemView.findViewById(R.id.txt_name_movie);
            txtTime = itemView.findViewById(R.id.txt_time_ticket);
            txtLocation = itemView.findViewById(R.id.txt_location);
            cardView = itemView.findViewById(R.id.card_ticket);
        }
    }
}
