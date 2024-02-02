package com.nishat.healthcare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishat.healthcare.Model.HealthArticle;
import com.nishat.healthcare.R;

import java.util.List;

public class HealthArticleAdapter extends RecyclerView.Adapter<HealthArticleAdapter.HealthArticleAdapterClass>{
    private Context context;
    private List<HealthArticle> itemList;
    private EmranClick emranClick;
    private String from;
    public void setEmranClick(EmranClick emranClick) {
        this.emranClick = emranClick;
    }
    public HealthArticleAdapter(Context context, List<HealthArticle> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.from=from;
    }
    @NonNull
    @Override
    public HealthArticleAdapterClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new HealthArticleAdapterClass(view,emranClick);
    }
    @Override
    public void onBindViewHolder(@NonNull HealthArticleAdapterClass holder, int position) {
        HealthArticle item=itemList.get(position);
        holder.priceTV.setText("See Details");
        holder.nameTV.setText(item.getArticleName());
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public interface EmranClick{
        public void onClick(int position);
    };
    public class HealthArticleAdapterClass extends RecyclerView.ViewHolder {
        private TextView nameTV,priceTV;

        public HealthArticleAdapterClass(@NonNull View itemView, EmranClick emranClick) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.nameId);
            priceTV=itemView.findViewById(R.id.priceId);
            itemView.setOnClickListener(v -> {
                emranClick.onClick(getAdapterPosition());
            });
        }
    }
}
