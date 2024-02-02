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
import com.emranbdx.healthcare.Model.Order;
import com.emranbdx.healthcare.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderAdapterClass>{
    private Context context;
    private List<Order> itemList;

    private EmranClick emranClick;

    public void setEmranClick(EmranClick emranClick) {
        this.emranClick = emranClick;
    }

    public OrderAdapter(Context context, List<Order> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public OrderAdapterClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_layout,parent,false);
        return new OrderAdapterClass(view,emranClick);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapterClass holder, int position) {
        Order item=itemList.get(position);
        int amount=0;
        for (Cart cart:item.getCartList()){
            amount=amount+Integer.parseInt(cart.getItem().getPrice());
        }
        holder.priceTV.setText("Tk"+amount);
        holder.nameTV.setText(item.getName());
        holder.addressTV.setText(item.getAddress());
        holder.dateTV.setText(new SimpleDateFormat("dd-MMM-yy").format(item.getCreatedDate()));
        holder.typeTV.setText(item.getType());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface EmranClick{
        public void onClick(int position);
    };

    public class OrderAdapterClass extends RecyclerView.ViewHolder {
        private TextView nameTV,priceTV,addressTV,dateTV,typeTV;

        public OrderAdapterClass(@NonNull View itemView, EmranClick emranClick) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.nameId);
            priceTV=itemView.findViewById(R.id.priceId);

            dateTV=itemView.findViewById(R.id.dateId);
            typeTV=itemView.findViewById(R.id.typeId);
            addressTV=itemView.findViewById(R.id.addressId);

            /*itemView.setOnClickListener(v -> {
                emranClick.onClick(getAdapterPosition());
            });*/
        }
    }
}
