package com.emranbdx.healthcare.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.emranbdx.healthcare.Adapter.DoctorAdapter;
import com.emranbdx.healthcare.Model.Item;
import com.emranbdx.healthcare.R;
import com.emranbdx.healthcare.Utils.Config;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {
    private List<Item> itemList;
    private FirebaseDatabase firebaseDatabase;
    private RecyclerView recyclerView;
    private String from;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        from=getIntent().getStringExtra("from");
        showTitle();
        itemList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseDatabase=FirebaseDatabase.getInstance(Config.dbUrl);
        createTestList();
    }
    private void showTitle() {
        TextView nameTV=findViewById(R.id.activityNameId);
        CardView cardView=findViewById(R.id.backId);
        nameTV.setText("Doctor List");
        cardView.setOnClickListener(v -> {
            finish();
        });
    }
    private void createTestList() {
        String categoryName="Doctor";
        itemList.add(new Item("11",categoryName,"500","Asik Rana Sagor","Dhaka","Surgeon","7 Years","01740120526"));
        itemList.add(new Item("12",categoryName,"500","Fazley Rabby","Dhaka","Surgeon","3 Years","01740120520"));
        itemList.add(new Item("13",categoryName,"400","Shamim Hossain","Dhaka","Surgeon","4 Years","01770120528"));

        itemList.add(new Item("14",categoryName,"400","Rana Hossain","Pabna","Dentist","9 Years","01770120528"));
        itemList.add(new Item("15",categoryName,"500","Rakib Hossain","Dhaka","Dentist","8 Years","01770120528"));
        itemList.add(new Item("16",categoryName,"600","Shetu Parvin","Dhaka","Dentist","10 Years","01770120528"));

        itemList.add(new Item("17",categoryName,"400","Bindu Hossain","Pabna","Cardiologist","9 Years","01770120528"));
        itemList.add(new Item("18",categoryName,"500","Zerin Khan","Dhaka","Cardiologist","8 Years","01770120528"));
        itemList.add(new Item("19",categoryName,"600","Khulsum Parvin","Dhaka","Cardiologist","10 Years","01770120528"));

        createMainPage();
    }

    private void createMainPage() {
        List<Item> items=new ArrayList<>();
        for (Item item:itemList){
            if (item.getDoctorCategory().contentEquals(from)){
                items.add(item);
            }
        }
        DoctorAdapter doctorAdapter=new DoctorAdapter(this,items);
        recyclerView.setAdapter(doctorAdapter);
        doctorAdapter.setEmranClick(position -> {
            Intent intent=new Intent(this,CartActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("ItemClass",items.get(position));
            bundle.putSerializable("position",position);
            bundle.putSerializable("from","Doctor");
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

}