package com.example.sssapplication;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    String userName,phone,newPassword,passRecovery,gender = "",passwordRecovery = "";
    FirebaseDatabase db;
    DatabaseReference reference;
    EditText edtTxtUserName,edtTxtMobileNumber,edtTxtNewPass,edtTxtPassRecovery;
    RadioGroup radioGrpGender,radioGrpPasswordRecovery;
    RadioButton radioGrpGenderOption,radioGrpPasswordRecoveryOption;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void onButtonDetailSave(View view) {

        edtTxtUserName = findViewById(R.id.edtTxtUserName);
        edtTxtMobileNumber = findViewById(R.id.edtTxtMobileNumber);
        edtTxtNewPass = findViewById(R.id.edtTxtNewPass);
        edtTxtPassRecovery = findViewById(R.id.edtTxtPassRecovery);

        userName = edtTxtUserName.getText().toString();
        phone = edtTxtMobileNumber.getText().toString();
        newPassword = edtTxtNewPass.getText().toString();
        passRecovery = edtTxtPassRecovery.getText().toString();

        radioGrpGender = findViewById(R.id.radioGrpGender);
        RadioButton genderBtn=(RadioButton) findViewById(radioGrpGender.getCheckedRadioButtonId());


        radioGrpGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                radioGrpGenderOption = radioGrpGender.findViewById(i);
                switch (i) {
                    case R.id.radioMale:
                        gender = "male";
                        break;
                    case R.id.radioFemale:
                        gender = "female";
                        break;
                    case R.id.radioOthers:
                        gender = "others";
                        break;
                    default:
                }
            }
        });
        gender = genderBtn.getText().toString();

        radioGrpPasswordRecovery = findViewById(R.id.radioGrpPasswordRecovery);
        RadioButton passRecBtn=(RadioButton) findViewById(radioGrpPasswordRecovery.getCheckedRadioButtonId());
        passwordRecovery = passRecBtn.getText().toString();

        radioGrpPasswordRecovery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                radioGrpPasswordRecoveryOption = radioGrpPasswordRecovery.findViewById(i);
                switch (i) {
                    case R.id.radioPasswordRecovery1:
                        passwordRecovery = "What is your birth Place?";
                        break;
                    case R.id.radioPasswordRecovery2:
                        passwordRecovery = "What is your nick name?";
                        break;
                    case R.id.radioPasswordRecovery3:
                        passwordRecovery = "What is your pet's name?";
                        break;
                    case R.id.radioPasswordRecovery4:
                        passwordRecovery = "What is your favorite food?";
                        break;
                    case R.id.radioPasswordRecovery5:
                        passwordRecovery = "What is your favorite game?";
                        break;
                    default:
                }
            }
        });



        if (!userName.isEmpty() && !gender.isEmpty() && !phone.isEmpty() && !newPassword.isEmpty() && !passRecovery.isEmpty() && !passwordRecovery.isEmpty()){

            Users users = new Users(userName,gender,phone,newPassword,passwordRecovery,passRecovery);
            db = FirebaseDatabase.getInstance();
            reference = db.getReference("Users");
            reference.child(phone).child("Details").setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME,0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userPhone",phone);
                    editor.apply();
                    Toast.makeText(MainActivity2.this,"Successfully Saved",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this , MainActivity8.class);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                }
            });
        }else{
            Toast.makeText(MainActivity2.this,"Please Fill all Details !!",Toast.LENGTH_SHORT).show();
        }
    }
}