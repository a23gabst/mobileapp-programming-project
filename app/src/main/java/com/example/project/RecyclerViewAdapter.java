package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<MusikInstrument> items;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    RecyclerViewAdapter(Context context, List<MusikInstrument> items, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.onClickListener = onClickListener;
    }

    public void updateData(List<MusikInstrument> updateList) {
        items.clear();
        items.addAll(updateList);
        this.notifyDataSetChanged();
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.activity_foremal, parent, false));
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameText.setText(items.get(position).getName());
        holder.genreText.setText(items.get(position).getGenre());
        holder.costText.setText(String.valueOf(items.get(position).getCost()) + " kr");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameText;
        TextView genreText;
        TextView costText;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nameText=itemView.findViewById(R.id.textView6);
            genreText=itemView.findViewById(R.id.textView5);
            costText=itemView.findViewById(R.id.textView4);

        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(items.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(MusikInstrument item);
    }
}
