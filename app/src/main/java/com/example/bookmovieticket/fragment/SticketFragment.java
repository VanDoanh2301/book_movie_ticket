package com.example.bookmovieticket.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.activity.MainActivity;
import com.example.bookmovieticket.adapter.TicketAdapter;
import com.example.bookmovieticket.api.RetrofitManager;
import com.example.bookmovieticket.model.Movie;
import com.example.bookmovieticket.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SticketFragment extends Fragment {

   private TicketAdapter ticketAdapter;
   private RecyclerView rc;
   private List<Ticket> tickets;
   private String userName;
    private AlertDialog dialog;
    private Ticket ticket;
    private Button btnYes, btnNo;

    public SticketFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sticket, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc = view.findViewById(R.id.rc_ticket);
        ticketAdapter = new TicketAdapter(getContext());
        userName = ((MainActivity) getActivity()).getUserName();

        configMovieView();
        showDialog();
    }


    @SuppressLint("MissingInflatedId")
    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Lựa chọn");
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_delete, null);
        btnNo = view.findViewById(R.id.no_delete);
        btnYes = view.findViewById(R.id.yes_delete);
        btnNo.setOnClickListener(v -> dialog.dismiss());
        builder.setView(view);
        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        ticketAdapter.setOnItemClickListener(new TicketAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ticket = tickets.get(position);
                dialog.show();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RetrofitManager.getRetrofit().deleteTicket(ticket.getTicketID()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(getContext(), "Delete", Toast.LENGTH_SHORT).show();
                        tickets.remove(ticket);
                        ticketAdapter.setData(tickets);
                        ticketAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                dialog.dismiss();

            }
        });


    }


    private void configMovieView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rc.setLayoutManager(linearLayoutManager);

       RetrofitManager.getRetrofit().getAllTicket(userName).enqueue(new Callback<List<Ticket>>() {
           @Override
           public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
               tickets = response.body();
               ticketAdapter.setData(tickets);
               rc.setAdapter(ticketAdapter);
           }

           @Override
           public void onFailure(Call<List<Ticket>> call, Throwable t) {

           }
       });

    }
}