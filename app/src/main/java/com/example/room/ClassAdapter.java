package com.example.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {
    List<ClassUser> list;
    Context mContext;

    public void setData(List<ClassUser> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassUser classUser = list.get(position);
        holder.tvNameClass.setText(classUser.getNameClass());

        List<user> listUser = userDatabase.getInstance(mContext).dao().getIdUser(classUser.getId() + "");
        holder.tvNameUser.setText(listUser.get(position).getUsername());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNameClass;
        private TextView tvNameUser;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameClass = (TextView) itemView.findViewById(R.id.tv_nameClass);
            tvNameUser = (TextView) itemView.findViewById(R.id.tv_nameUser);
        }
    }
}
