package com.example.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Spiner_User_adapter extends BaseAdapter {
    List<user> list;
    Context context;
    int layout;

    public Spiner_User_adapter(List<user> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewUser viewUser;
        if (convertView == null) {
            viewUser = new ViewUser();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_spin_user, null);
            viewUser.tv_id = convertView.findViewById(R.id.tv_id);
            viewUser.tv_name = convertView.findViewById(R.id.tv_user);
            convertView.setTag(viewUser);
        } else {
            viewUser = (ViewUser) convertView.getTag();
        }
        viewUser.tv_id.setText(list.get(position).getId() + "");
        viewUser.tv_name.setText(list.get(position).getUsername());


        return convertView;
    }

    public class ViewUser {
        private TextView tv_id, tv_name;

    }
}
