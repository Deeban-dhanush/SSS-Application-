package com.example.sssapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class MainActivity9 extends AppCompatActivity {

    String userPhone,userPassRecovery,userPasswordRecovery = "";
    RadioGroup radioGrpPasswordRecoveryCheck;
    RadioButton radioGrpPasswordRecoveryOptionCheck;
    DatabaseReference reference;
    boolean is_Valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
    }

    public void onButtonForgotPassword(View view) {

        EditText edtTxtNumberCheck = findViewById(R.id.edtTxtNumberCheck);
        userPhone = edtTxtNumberCheck.getText().toString();

        /*radioGrpPasswordRecoveryCheck = findViewById(R.id.radioGrpPasswordRecoveryCheck);
        RadioButton passGrpBtn=(RadioButton) findViewById(radioGrpPasswordRecoveryCheck.getCheckedRadioButtonId());
        userPasswordRecovery = passGrpBtn.getText().toString();

        radioGrpPasswordRecoveryCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                radioGrpPasswordRecoveryOptionCheck = radioGrpPasswordRecoveryCheck.findViewById(i);
                switch (i) {
                    case R.id.radioPasswordRecovery1Check:
                        userPassRecovery = "What is your birth Place?";
                        break;
                    case R.id.radioPasswordRecovery2Check:
                        userPassRecovery = "What is your nick name?";
                        break;
                    case R.id.radioPasswordRecovery3Check:
                        userPassRecovery = "What is your pet's name?";
                        break;
                    case R.id.radioPasswordRecovery4Check:
                        userPassRecovery = "What is your favorite food?";
                        break;
                    case R.id.radioPasswordRecovery5Check:
                        userPassRecovery = "What is your favorite game?";
                        break;
                    default:
                }
            }
        });*/

        EditText edtTxtPassRecoveryCheck = findViewById(R.id.edtTxtPassRecoveryCheck);
        userPassRecovery = edtTxtPassRecoveryCheck.getText().toString();

        if(!userPhone.isEmpty()  && !userPassRecovery.isEmpty()){
            Intent intent = new Intent(MainActivity9.this , MainActivity10.class);
            intent.putExtra("phone",userPhone);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please Fill all Details !!", Toast.LENGTH_SHORT).show();
        }
    }

   /* private void checkData(String Phone) {

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(Phone).child("Details").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        DataSnapshot dataSnapshot = task.getResult();
                        String passwordRecoveryData = String.valueOf(dataSnapshot.child("passwordRecovery").getValue());
                        String passRecoveryData = String.valueOf(dataSnapshot.child("passRecovery").getValue());
                        is_Valid = validating(passwordRecoveryData,passRecoveryData);

                        if(!is_Valid) {
                            Toast.makeText(MainActivity9.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(MainActivity9.this , MainActivity10.class);
                            intent.putExtra("phone",userPhone);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(MainActivity9.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity9.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean validating(String p1RecoveryData ,String p2RecoveryData){

        if(p1RecoveryData.equals(userPasswordRecovery) && p2RecoveryData.equals(userPassRecovery)){
            return true;
        }
        return false;
    }*/
}