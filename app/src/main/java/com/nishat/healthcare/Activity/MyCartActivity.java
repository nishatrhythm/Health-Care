package com.nishat.healthcare.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.nishat.healthcare.Adapter.CartAdapter;
import com.nishat.healthcare.Model.Cart;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MyCartActivity extends AppCompatActivity {
    private List<Cart> cartList;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private RecyclerView recyclerView;
    private LottieAnimationView lottieAnimationView;
    private TextView totalQtyTV,totalAmountTV;
    private Button checkOutButton;
    private RelativeLayout bottomRL;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        from=getIntent().getStringExtra("from");
        showTitle();
        cartList=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance(Config.dbUrl);

        recyclerView=findViewById(R.id.recyclerView);
        lottieAnimationView=findViewById(R.id.loadingAnimationId);
        totalQtyTV=findViewById(R.id.qtyId);
        totalAmountTV=findViewById(R.id.amountId);
        checkOutButton=findViewById(R.id.checkOutButtonId);
        bottomRL=findViewById(R.id.bottom);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAllCart();
        checkOutButton.setOnClickListener(v -> {
            Order order=new Order(cartList, UUID.randomUUID().toString(),new Date());
            order.setType(from);
            Intent intent=new Intent(this,MakeOrderActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("OrderClass",order);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    private void getAllCart() {
        lottieAnimationView.setVisibility(View.VISIBLE);
        DatabaseReference databaseReference=firebaseDatabase.getReference("MyCart");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lottieAnimationView.setVisibility(View.GONE);
                cartList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Cart cart=dataSnapshot.getValue(Cart.class);
                    if (cart.getName().contentEquals(from)&&cart.getUserId().contentEquals(firebaseAuth.getCurrentUser().getUid())){
                        cartList.add(cart);
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
        CartAdapter cartAdapter=new CartAdapter(this,cartList);
        recyclerView.setAdapter(cartAdapter);
        if (cartList.size()>0){
            bottomRL.setVisibility(View.VISIBLE);
            totalQtyTV.setText("Total Item: "+cartList.size());
            int amount=0;
            for (Cart cart:cartList){
                amount=amount+Integer.parseInt(cart.getItem().getPrice());
            }
            totalAmountTV.setText("Total Amount: Tk"+amount);
        }
        cartAdapter.setEmranClick(position -> {
            Intent intent=new Intent(this,MakeOrderActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("CartClass",cartList.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
    private void showTitle() {
        TextView nameTV=findViewById(R.id.activityNameId);
        CardView cardView=findViewById(R.id.backId);
        nameTV.setText("My Cart");
        cardView.setOnClickListener(v -> {
            finish();
        });
    }
}