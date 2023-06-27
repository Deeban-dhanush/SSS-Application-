package com.example.sssapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity10 extends AppCompatActivity {

    boolean isValid = false;
    String userPhone,newPassword,conformPassword;
    EditText edtTxtNewPassword,edtConformPassword;
    DatabaseReference reference;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
    }
    public void onButtonNewPassword(View view) {

        edtTxtNewPassword = findViewById(R.id.edtTxtNewPassword);
        edtConformPassword = findViewById(R.id.edtConfirmPassword);

        newPassword = edtTxtNewPassword.getText().toString();
        conformPassword = edtConformPassword.getText().toString();

        userPhone = getIntent().getExtras().getString("phone");

        if(!newPassword.isEmpty() || !conformPassword.isEmpty()){

            isValid = validate(newPassword,conformPassword);
            if(!isValid){
                HashMap newPass = new HashMap();
                newPass.put("newPassword",conformPassword);

                db = FirebaseDatabase.getInstance();
                reference = db.getReference("Users");
                reference.child(userPhone).child("Details").updateChildren(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity10.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity10.this , LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity10.this,"Failed to Update",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {

                Toast.makeText(MainActivity10.this,"Doesn't Match",Toast.LENGTH_SHORT).show();

            }
        }
    }

    private boolean validate(String newPassword1 , String conformPassword1){

        if(newPassword1.equals(conformPassword1) ){
            return true;
        }
        return false;
    }
}