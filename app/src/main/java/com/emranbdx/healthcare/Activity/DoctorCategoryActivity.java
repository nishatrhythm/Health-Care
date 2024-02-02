package com.emranbdx.healthcare.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.emranbdx.healthcare.Adapter.CategoryAdapter;
import com.emranbdx.healthcare.Adapter.ItemAdapter;
import com.emranbdx.healthcare.Model.Item;
import com.emranbdx.healthcare.R;
import com.emranbdx.healthcare.Utils.Config;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DoctorCategoryActivity extends AppCompatActivity {
    private List<Item> itemList;
    private FirebaseDatabase firebaseDatabase;
    private RecyclerView recyclerView;
    private ExtendedFloatingActionButton extendedFloatingActionButton;

    private String[] categoryName={"Surgeon","Dentist","Cardiologist","Medicine Specialist"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_category);
        showTitle();
        itemList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        extendedFloatingActionButton=findViewById(R.id.myCartId);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        firebaseDatabase=FirebaseDatabase.getInstance(Config.dbUrl);
        extendedFloatingActionButton.setOnClickListener(v -> {
            Intent intent=new Intent(this,MyCartActivity.class);
            intent.putExtra("from","Doctor");
            startActivity(intent);
        });
        createTestList();
    }

    private void showTitle() {
        TextView nameTV=findViewById(R.id.activityNameId);
        CardView cardView=findViewById(R.id.backId);
        nameTV.setText("Doctor Category");
        cardView.setOnClickListener(v -> {
            finish();
        });
    }

    private void createTestList() {
        creteMainPage();
    }

    private void creteMainPage() {
       CategoryAdapter itemAdapter=new CategoryAdapter(this,categoryName);
       recyclerView.setAdapter(itemAdapter);
        itemAdapter.setEmranClick(position -> {
            Intent intent=new Intent(this,DoctorActivity.class);
            intent.putExtra("from",categoryName[position]);
            startActivity(intent);
        });
    }
}