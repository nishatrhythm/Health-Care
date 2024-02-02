package com.nishat.healthcare.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.nishat.healthcare.Adapter.ItemAdapter;
import com.nishat.healthcare.Model.Item;
import com.nishat.healthcare.R;
import com.nishat.healthcare.Utils.Config;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class LabTestActivity extends AppCompatActivity {
    private List<Item> itemList;
    private FirebaseDatabase firebaseDatabase;
    private RecyclerView recyclerView;
    private ExtendedFloatingActionButton extendedFloatingActionButton;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        from=getIntent().getStringExtra("from");
        showTitle();
        itemList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        extendedFloatingActionButton=findViewById(R.id.myCartId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase=FirebaseDatabase.getInstance(Config.dbUrl);
        createTestList();
        extendedFloatingActionButton.setOnClickListener(v -> {
            Intent intent=new Intent(this,MyCartActivity.class);
            intent.putExtra("from",from);
            startActivity(intent);
        });

    }

    private void showTitle() {
        TextView nameTV=findViewById(R.id.activityNameId);
        CardView cardView=findViewById(R.id.backId);
        nameTV.setText(from);
        cardView.setOnClickListener(v -> {
            finish();
        });
    }

    private void createTestList() {
        if (from.contentEquals("Lab Test")){
            itemList.add(new Item("1",from,"Full Body Check Up","1000"));
            itemList.add(new Item("2",from,"Blood Glucose Fasting","300"));
            itemList.add(new Item("3",from,"Covid-19 Antibody-IgG","500"));
            itemList.add(new Item("4",from,"Thyroid Check","500"));
            itemList.add(new Item("5",from,"Imunity Check","700"));
        } else if (from.contentEquals("Buy Medicine")) {
            itemList.add(new Item("6",from,"Napa Syrup","50"));
            itemList.add(new Item("7",from,"Fexo Tablet 50mg) ","100"));
            itemList.add(new Item("8",from,"Amodis","40"));
            itemList.add(new Item("9",from,"Napa Extra Tablet 500mg","20"));
            itemList.add(new Item("10",from,"Filmet","45"));
        }
        creteMainPage();
    }

    private void creteMainPage() {
        ItemAdapter itemAdapter=new ItemAdapter(this,itemList,from);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.setEmranClick(position -> {
            Intent intent=new Intent(this,CartActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("ItemClass",itemList.get(position));
            bundle.putSerializable("position",position);
            bundle.putSerializable("from",from);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}