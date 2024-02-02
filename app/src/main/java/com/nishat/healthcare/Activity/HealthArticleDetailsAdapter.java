package com.nishat.healthcare.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

import com.nishat.healthcare.Model.HealthArticle;
import com.nishat.healthcare.R;

public class HealthArticleDetailsAdapter extends AppCompatActivity {
    private HealthArticle healthArticle;
    private TextView bodyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_details_adapter);
        healthArticle= (HealthArticle) getIntent().getSerializableExtra("HealthArticleClass");
        bodyId=findViewById(R.id.healthArticleId);
        bodyId.setText(healthArticle.getArticleDetails());
        showTitle();
    }
    private void showTitle() {
        TextView nameTV=findViewById(R.id.activityNameId);
        CardView cardView=findViewById(R.id.backId);
        nameTV.setText(healthArticle.getArticleName());
        cardView.setOnClickListener(v -> {
            finish();
        });
    }
}