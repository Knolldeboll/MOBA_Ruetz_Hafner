package com.example.eisapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.eisapp.R;

import java.util.List;

public class SaleEisAdapter extends RecyclerView.Adapter<SaleEisAdapter.ChildViewHolder> {

    private List<Eis> datalist;
    public TextView accessTextView;
    public View.OnClickListener childocl;


    public class ChildViewHolder extends RecyclerView.ViewHolder {

        public TextView et1;
        public FrameLayout frameLayout;

        public ChildViewHolder(View itemView) {

            super(itemView);
            et1 = (TextView) itemView.findViewById(R.id.textVieweis);
            accessTextView = et1;
            frameLayout = (FrameLayout) itemView.findViewById(R.id.eisframe);

        }

    }


    public SaleEisAdapter(List<Eis> eisList, View.OnClickListener onClickListener) {
        datalist = eisList;
        childocl = onClickListener;

    }

    @Override
    public SaleEisAdapter.ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View eisView = inflater.inflate(R.layout.eisitemlayout, parent, false);
        ChildViewHolder viewHolder = new ChildViewHolder(eisView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChildViewHolder holder, int position) {


        Eis eis = datalist.get(position);
        TextView eist1 = holder.et1;
        holder.frameLayout.setBackgroundColor(eis.getBackGroundColor());
        eist1.setOnClickListener(childocl);
        eist1.setText(eis.name);
        eist1.setTextColor(eis.textColor);
    }

    @Override
    public int getItemCount() {


        return datalist.size();
    }
}
