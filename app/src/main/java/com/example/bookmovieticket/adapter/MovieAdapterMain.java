package com.example.bookmovieticket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookmovieticket.R;
import com.example.bookmovieticket.model.Movie;

import java.util.List;

public class MovieAdapterMain extends RecyclerView.Adapter<MovieAdapterMain.MovieViewHolder> {

    private Context context;
    private List<Movie> movies;

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MovieAdapterMain(Context context) {
        this.context = context;
    }
    public void setData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_movie_main, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        if (movie == null) {
            return;
        }
       Glide.with(context).load(movie.getBannerUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return (movies == null) ? 0: movies.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private CardView cardView;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_movie_main);
            cardView = itemView.findViewById(R.id.card_view_detail);

            cardView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
