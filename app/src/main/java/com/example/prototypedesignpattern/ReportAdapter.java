package com.example.prototypedesignpattern;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.prototypedesignpattern.db.Member;

import java.util.List;

/**
 * Created by sarah on 4/9/2018.
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyViewHolder> {

    private List<Member> list;

    public ReportAdapter(List<Member> list) {
        this.list = list;
    }

    @Override
    public ReportAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lst_items_report, parent, false);

        return new ReportAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReportAdapter.MyViewHolder holder, int position) {

        holder.name.setText(list.get(position).name);
        holder.family.setText(list.get(position).family);
        holder.tell.setText(list.get(position).phone);
        holder.address.setText(list.get(position).address);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,family,tell,address;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            family = (TextView) view.findViewById(R.id.family);
            tell = (TextView) view.findViewById(R.id.tell);
            address = (TextView) view.findViewById(R.id.address);
        }
    }
}
