package com.nishat.healthcare.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.nishat.healthcare.Adapter.OrderAdapter;
import com.nishat.healthcare.Model.Order;
import com.nishat.healthcare.R;
import com.nishat.healthcare.Utils.Config;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity {
    private List<Order> cartList;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        showTitle();
        cartList=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance(Config.dbUrl);
        recyclerView=findViewById(R.id.recyclerView);
        lottieAnimationView=findViewById(R.id.loadingAnimationId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getAllOrder();
    }

    private void getAllOrder() {
        lottieAnimationView.setVisibility(View.VISIBLE);
        DatabaseReference databaseReference=firebaseDatabase.getReference("Order");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lottieAnimationView.setVisibility(View.GONE);
                cartList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Order order=dataSnapshot.getValue(Order.class);
                    if (order.getCartList().get(0).getUserId().contentEquals(firebaseAuth.getCurrentUser().getUid())){
                        cartList.add(order);
                    }
                }
                createMainPage();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                lottieAnimationView.setVisibility(View.GONE);
            }
        });

    }

    private void createMainPage() {
        OrderAdapter cartAdapter=new OrderAdapter(this,cartList);
        recyclerView.setAdapter(cartAdapter);

    }
    private void showTitle() {
        TextView nameTV=findViewById(R.id.activityNameId);
        CardView cardView=findViewById(R.id.backId);
        nameTV.setText("My Order");
        cardView.setOnClickListener(v -> {
            finish();
        });
    }
}