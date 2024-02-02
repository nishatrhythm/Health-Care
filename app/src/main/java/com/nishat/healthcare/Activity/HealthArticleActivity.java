package com.nishat.healthcare.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.nishat.healthcare.Adapter.HealthArticleAdapter;
import com.nishat.healthcare.Model.HealthArticle;
import com.nishat.healthcare.R;

import java.util.ArrayList;
import java.util.List;

public class HealthArticleActivity extends AppCompatActivity {
    private List<HealthArticle> itemList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article);
        showTitle();
        itemList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        createTestList();
    }

    private void showTitle() {
        TextView nameTV=findViewById(R.id.activityNameId);
        CardView cardView=findViewById(R.id.backId);
        nameTV.setText("Health Article");
        cardView.setOnClickListener(v -> {
            finish();
        });
    }

    private void createTestList() {
        itemList.add(new HealthArticle("Daily Walking",getString(R.string.daily_walking)));
        itemList.add(new HealthArticle("Stop Smoking",getString(R.string.stop_smoking)));
        creteMainPage();
    }

    private void creteMainPage() {
        HealthArticleAdapter itemAdapter=new HealthArticleAdapter(this,itemList);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.setEmranClick(position -> {
            Intent intent=new Intent(this, HealthArticleDetailsAdapter.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("HealthArticleClass",itemList.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}