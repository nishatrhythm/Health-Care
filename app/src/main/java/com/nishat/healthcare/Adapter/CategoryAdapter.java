package com.nishat.healthcare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishat.healthcare.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryAdapterClass>{
    private Context context;
    private String[] categoryList;

    private EmranClick emranClick;

    public void setEmranClick(EmranClick emranClick) {
        this.emranClick = emranClick;
    }

    public CategoryAdapter(Context context, String[] categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapterClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_layout,parent,false);
        return new CategoryAdapterClass(view,emranClick);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapterClass holder, int position) {
        String category=categoryList[position];
        holder.nameTV.setText(category);


    }

    @Override
    public int getItemCount() {
        return categoryList.length;
    }

    public interface EmranClick{
        public void onClick(int position);
    };

    public class CategoryAdapterClass extends RecyclerView.ViewHolder {
        private TextView nameTV;

        public CategoryAdapterClass(@NonNull View itemView, EmranClick emranClick) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.priceId);
            itemView.setOnClickListener(v -> {
                emranClick.onClick(getAdapterPosition());
            });
        }
    }
}
