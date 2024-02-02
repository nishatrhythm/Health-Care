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
import com.nishat.healthcare.Model.Order;
import com.nishat.healthcare.R;
import com.nishat.healthcare.Utils.Config;
import com.nishat.healthcare.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MakeOrderActivity extends AppCompatActivity {
    private TextInputEditText nameEditText,addressEditText,mobileNumberEditText,emailEditText;
    private Button submitButton;
    private LottieAnimationView lottieAnimationView;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private Order order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        showTitle();
        nameEditText=findViewById(R.id.nameEditText);
        addressEditText=findViewById(R.id.addressEditText);
        mobileNumberEditText=findViewById(R.id.mobileNumberEditText);
        emailEditText=findViewById(R.id.emailEditText);
        submitButton=findViewById(R.id.submitButtonId);
        lottieAnimationView=findViewById(R.id.loadingAnimationId);
        firebaseDatabase=FirebaseDatabase.getInstance(Config.dbUrl);
        firebaseAuth=FirebaseAuth.getInstance();
        order= (Order) getIntent().getSerializableExtra("OrderClass");
        submitButton.setOnClickListener(v -> {
            if (Utils.checkEditTextIsNull(nameEditText,"Enter Name")&&Utils.checkEditTextIsNull(addressEditText,"Enter Address")&&Utils.checkEditTextIsNull(mobileNumberEditText,"Enter Mobile Number")&&Utils.checkEditTextIsNull(emailEditText,"Enter Email")){
                String name=nameEditText.getText().toString();
                String address=addressEditText.getText().toString();
                String mobileNumber=mobileNumberEditText.getText().toString();
                String email=emailEditText.getText().toString();
                order.setName(name);
                order.setAddress(address);
                order.setMobileNumber(mobileNumber);
                order.setEmail(email);
                saveDataInFirebase(order);
            }
        });
    }

    private void saveDataInFirebase(Order order) {
        lottieAnimationView.setVisibility(View.VISIBLE);
        DatabaseReference databaseReference=firebaseDatabase.getReference("Order").child(order.getId());
        databaseReference.setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                lottieAnimationView.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(MakeOrderActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(MakeOrderActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showTitle() {
        TextView nameTV=findViewById(R.id.activityNameId);
        CardView cardView=findViewById(R.id.backId);
        nameTV.setText("Make Order");
        cardView.setOnClickListener(v -> {
            finish();
        });
    }
}