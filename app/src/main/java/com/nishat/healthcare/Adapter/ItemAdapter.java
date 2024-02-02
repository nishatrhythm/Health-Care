package com.nishat.healthcare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishat.healthcare.Model.Item;
import com.nishat.healthcare.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemAdapterClass>{
    private Context context;
    private List<Item> itemList;
    private EmranClick emranClick;
    private String from;
    public void setEmranClick(EmranClick emranClick) {
        this.emranClick = emranClick;
    }
    public ItemAdapter(Context context, List<Item> itemList, String from) {
        this.context = context;
        this.itemList = itemList;
        this.from=from;
    }
    @NonNull
    @Override
    public ItemAdapterClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ItemAdapterClass(view,emranClick);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemAdapterClass holder, int position) {
        Item item=itemList.get(position);
        holder.priceTV.setText("Total Cost- Tk"+item.getPrice());
        if (from.contentEquals("Lab Test")){
            holder.nameTV.setText("Package "+(position+1)+":"+item.getName());
        }else {
            holder.nameTV.setText(item.getName());
        }

    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public interface EmranClick{
        public void onClick(int position);
    };
    public class ItemAdapterClass extends RecyclerView.ViewHolder {
        private TextView nameTV,priceTV;

        public ItemAdapterClass(@NonNull View itemView, EmranClick emranClick) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.nameId);
            priceTV=itemView.findViewById(R.id.priceId);
            itemView.setOnClickListener(v -> {
                emranClick.onClick(getAdapterPosition());
            });
        }
    }
}
