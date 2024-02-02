package com.emranbdx.healthcare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emranbdx.healthcare.Model.Cart;
import com.emranbdx.healthcare.Model.Item;
import com.emranbdx.healthcare.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterClass>{
    private Context context;
    private List<Cart> itemList;

    private EmranClick emranClick;

    public void setEmranClick(EmranClick emranClick) {
        this.emranClick = emranClick;
    }

    public CartAdapter(Context context, List<Cart> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CartAdapterClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new CartAdapterClass(view,emranClick);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapterClass holder, int position) {
        Item item=itemList.get(position).getItem();
        holder.priceTV.setText("Total Cost- Tk"+item.getPrice());
        if (item.getCategoryName().contentEquals("Doctor")){
            holder.nameTV.setText(item.getDoctorName()+"("+item.getDoctorCategory()+")");
        }else {
            holder.nameTV.setText("Package "+(position+1)+":"+item.getName());
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface EmranClick{
        public void onClick(int position);
    };

    public class CartAdapterClass extends RecyclerView.ViewHolder {
        private TextView nameTV,priceTV;

        public CartAdapterClass(@NonNull View itemView, EmranClick emranClick) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.nameId);
            priceTV=itemView.findViewById(R.id.priceId);
            /*itemView.setOnClickListener(v -> {
                emranClick.onClick(getAdapterPosition());
            });*/
        }
    }
}
