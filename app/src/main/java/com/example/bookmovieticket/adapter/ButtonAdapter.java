package com.example.bookmovieticket.adapter;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.model.ShowTime;
import com.google.gson.Gson;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ButtonViewAdapter> {
    private Context context;

    private List<ShowTime> showTimes;

    public ButtonAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<ShowTime> showTimes) {
        this.showTimes = showTimes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ButtonViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_button_time, parent, false);
        return new ButtonViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewAdapter holder, int position) {
        ShowTime showTime = showTimes.get(position);
        if(showTime == null) {
            return;
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String startTimeString = timeFormat.format(showTime.getStartTime());
        String endTimeString = timeFormat.format(showTime.getEndTime());
        holder.btnBookTime.setText(startTimeString +" - "+endTimeString);

    }

    @Override
    public int getItemCount() {
        return showTimes.size();
    }

    public class ButtonViewAdapter extends RecyclerView.ViewHolder {
        public Button btnBookTime;
        private CardView cardView;
        public ButtonViewAdapter(@NonNull View itemView) {
            super(itemView);
            btnBookTime = itemView.findViewById(R.id.btn_time_sticker);
            cardView = itemView.findViewById(R.id.card_view_sticker);
        }
    }
}
