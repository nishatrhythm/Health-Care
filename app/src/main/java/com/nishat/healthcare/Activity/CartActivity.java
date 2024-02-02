package com.nishat.healthcare.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.nishat.healthcare.Model.Cart;
import com.nishat.healthcare.Model.Item;
import com.nishat.healthcare.R;
import com.nishat.healthcare.Utils.Config;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.UUID;

public class CartActivity extends AppCompatActivity {
    private Item item;
    private TextView packageNameTv,priceTV,titleTV;
    private Button addToCartButton;
    private FirebaseDatabase firebaseDatabase;
    private LottieAnimationView lottieAnimationView;
    private int position;
    private String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        firebaseDatabase=FirebaseDatabase.getInstance(Config.dbUrl);
        from=getIntent().getStringExtra("from");
        item= (Item) getIntent().getSerializableExtra("ItemClass");
        position=getIntent().getIntExtra("position",0);
        packageNameTv=findViewById(R.id.packageNameId);
        priceTV=findViewById(R.id.totalPriceId);
        addToCartButton=findViewById(R.id.addCartId);
        titleTV=findViewById(R.id.titleTV);
        lottieAnimationView=findViewById(R.id.loadingAnimationId);
        if (item.getCategoryName().contentEquals("Doctor")){
            packageNameTv.setText(item.getDoctorName()+"("+item.getDoctorCategory()+")");
            titleTV.setText("Hospital Location: "+item.getDoctorAddress());
        }else {
            packageNameTv.setText("Package "+(position+1)+": "+item.getName());
        }
        priceTV.setText("Total Price: Tk"+item.getPrice());
        addToCartButton.setOnClickListener(v -> {
            Cart cart=new Cart(UUID.randomUUID().toString(),from,item,new Date(), FirebaseAuth.getInstance().getCurrentUser().getUid());
            saveDataInFirebase(cart);
        });
        showTitle();
    }

    private void saveDataInFirebase(Cart cart) {
        lottieAnimationView.setVisibility(View.VISIBLE);
        DatabaseReference databaseReference=firebaseDatabase.getReference("MyCart").child(cart.getId());
        databaseReference.setValue(cart).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                lottieAnimationView.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(CartActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(CartActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showTitle() {
        TextView nameTV=findViewById(R.id.activityNameId);
        CardView cardView=findViewById(R.id.backId);
        nameTV.setText("Add Cart");
        cardView.setOnClickListener(v -> {
            finish();
        });
    }
}