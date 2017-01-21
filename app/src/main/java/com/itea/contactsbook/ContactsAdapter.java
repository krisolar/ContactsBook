package com.itea.contactsbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 12.01.2017.
 */
public class ContactsAdapter extends ArrayAdapter{

    private List<ContactEnitity> list;
    private Context context;
    private LayoutInflater inflater;

    public ContactsAdapter(Context context, int resource, List list) {
        super(context, resource);
        this.list = list;
        this.context = context;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateList(List<ContactEnitity>  list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public List<ContactEnitity> getList() {
        return list;
    }

    @Nullable
    @Override
    public ContactEnitity getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.contact_layout, parent, false);
        }

        ImageView ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        tvName.setText(list.get(position).getName());

        TextView tvPhoneNumber = (TextView) view.findViewById(R.id.tvPhoneNumber);
        tvPhoneNumber.setText(list.get(position).getPnoneNumber1());

        return view;
    }
}
