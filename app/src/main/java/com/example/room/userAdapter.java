package com.example.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewUserHolder> {
    List<user> list;
    private clickItem clickItem;

    public interface clickItem{
        void updateUser(user user);
        void deleteUser(user user);
    }

    public userAdapter(userAdapter.clickItem clickItem) {
        this.clickItem = clickItem;
    }

    public void setDaTa(List<user> list){
        this.list = list;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new ViewUserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewUserHolder holder, int position) {
      final  user user = list.get(position);
        holder.tvUsername.setText(user.getUsername());
        holder.tvAddress.setText(user.getAddress());
        holder.btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.updateUser(user);
            }
        });
        holder.btnDELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.deleteUser(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewUserHolder extends RecyclerView.ViewHolder{
        private TextView tvUsername;
        private TextView tvAddress;
        private Button btn_update,btnDELETE;



        public ViewUserHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = (TextView) itemView.findViewById(R.id.tv_username);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            btn_update = itemView.findViewById(R.id.btn_update);
            btnDELETE = itemView.findViewById(R.id.btn_delete);
        }
    }
}
