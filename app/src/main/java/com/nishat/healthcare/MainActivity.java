package com.nishat.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.nishat.healthcare.Activity.DoctorCategoryActivity;
import com.nishat.healthcare.Activity.HealthArticleActivity;
import com.nishat.healthcare.Activity.LabTestActivity;
import com.nishat.healthcare.Activity.LoginActivity;
import com.nishat.healthcare.Activity.MyOrderActivity;
import com.nishat.healthcare.Model.User;
import com.nishat.healthcare.Utils.Config;
import com.nishat.healthcare.Utils.SharedPreferenceManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView nameTV;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private User user;
    private LottieAnimationView lottieAnimationView;
    private SharedPreferenceManager sharedPreferenceManager;
    private ImageView logoutImageView;
    private CardView labtestCardView,buyMedicineCardView,findDoctorCardView,healthArticleCardView,orderDetailsCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferenceManager=new SharedPreferenceManager(this);
        nameTV=findViewById(R.id.userNameId);
        logoutImageView=findViewById(R.id.logOut);

        lottieAnimationView=findViewById(R.id.loadingAnimationId);

        labtestCardView=findViewById(R.id.labTestId);
        buyMedicineCardView=findViewById(R.id.buyMedicineId);
        findDoctorCardView=findViewById(R.id.findDoctorId);
        healthArticleCardView=findViewById(R.id.healthArticleId);
        orderDetailsCardView=findViewById(R.id.orderDetailsId);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance(Config.dbUrl);
        if (!sharedPreferenceManager.isDataSaved()){
            getUserDetails();
        }else {
            nameTV.setText(sharedPreferenceManager.getName());
        }
        logoutImageView.setOnClickListener(v -> {
            logout();
        });

        labtestCardView.setOnClickListener(v -> {
            Intent intent=new Intent(this, LabTestActivity.class);
            intent.putExtra("from","Lab Test");
            startActivity(intent);
        });
        buyMedicineCardView.setOnClickListener(v -> {
            Intent intent=new Intent(this, LabTestActivity.class);
            intent.putExtra("from","Buy Medicine");
            startActivity(intent);
        });
        orderDetailsCardView.setOnClickListener(v -> {
            startActivity(new Intent(this, MyOrderActivity.class));
        });
        findDoctorCardView.setOnClickListener(v -> {
            startActivity(new Intent(this, DoctorCategoryActivity.class));
        });
        healthArticleCardView.setOnClickListener(v -> {
            startActivity(new Intent(this, HealthArticleActivity.class));
        });
    }

    private void logout() {
        MaterialAlertDialogBuilder materialAlertDialogBuilder=new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setTitle("Confirm you want to logout?");
        materialAlertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                firebaseAuth.signOut();
                sharedPreferenceManager.setDataSaved(false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
        materialAlertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        materialAlertDialogBuilder.show();

    }

    private void getUserDetails() {
        lottieAnimationView.setVisibility(View.VISIBLE);
        DatabaseReference databaseReference=firebaseDatabase.getReference("User").child(firebaseAuth.getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user=snapshot.getValue(User.class);
                nameTV.setText(user.getName());
                lottieAnimationView.setVisibility(View.GONE);
                sharedPreferenceManager.setName(user.getName());
                sharedPreferenceManager.setDataSaved(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                lottieAnimationView.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}