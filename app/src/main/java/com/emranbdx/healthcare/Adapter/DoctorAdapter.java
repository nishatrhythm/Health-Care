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

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorAdapterClass>{
    private Context context;
    private List<Item> itemList;

    private EmranClick emranClick;

    public void setEmranClick(EmranClick emranClick) {
        this.emranClick = emranClick;
    }

    public DoctorAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public DoctorAdapterClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.doctor_layout,parent,false);
        return new DoctorAdapterClass(view,emranClick);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapterClass holder, int position) {
        Item item=itemList.get(position);
        holder.priceTV.setText("Tk"+item.getPrice());
        holder.nameTV.setText(item.getDoctorName());
        holder.addressTV.setText("Hospital Address: "+item.getDoctorAddress());
        holder.mobileNumberTV.setText("Mob : "+item.getDoctorMobileNumber());
        holder.expertiesTV.setText("Exp : "+item.getDoctorExperties());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface EmranClick{
        public void onClick(int position);
    };

    public class DoctorAdapterClass extends RecyclerView.ViewHolder {
        private TextView nameTV,priceTV,addressTV,expertiesTV,mobileNumberTV;

        public DoctorAdapterClass(@NonNull View itemView, EmranClick emranClick) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.nameId);
            priceTV=itemView.findViewById(R.id.priceId);
            expertiesTV=itemView.findViewById(R.id.expertiesId);
            mobileNumberTV=itemView.findViewById(R.id.mobileNumberId);
            addressTV=itemView.findViewById(R.id.addressId);

            itemView.setOnClickListener(v -> {
                emranClick.onClick(getAdapterPosition());
            });
        }
    }
}
