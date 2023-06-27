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

public class MainActivity4 extends AppCompatActivity {

    String userPhone,msgNumberOne,msgNumberTwo,msgNumberThree,msgNumberFour,msgNumberFive,callNumberOne,altMessage;
    DatabaseReference reference;
    FirebaseDatabase db;
    /*EditText edtMessageNumberOne,edtMessageNumberTwo,edtMessageNumberThree,edtMessageNumberFour,edtMessageNumberFive,edtCallNumberOne,edtAlertMessage ;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


    }

    /*public void retrieveDetails(String Phone){
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("Users");
        reference.child(Phone).child("Contact").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String msgNumberOne = String.valueOf(dataSnapshot.child("msgNumberOne").getValue());
                        String msgNumberTwo = String.valueOf(dataSnapshot.child("msgNumberTwo").getValue());
                        String msgNumberThree = String.valueOf(dataSnapshot.child("msgNumberThree").getValue());
                        String msgNumberFour = String.valueOf(dataSnapshot.child("msgNumberFour").getValue());
                        String msgNumberFive = String.valueOf(dataSnapshot.child("msgNumberFive").getValue());
                        String callNumberOne = String.valueOf(dataSnapshot.child("callNumberOne").getValue());
                        String altMessage = String.valueOf(dataSnapshot.child("altMessage").getValue());
                        edtMessageNumberOne.setText(msgNumberOne);
                        edtMessageNumberTwo.setText(msgNumberTwo);
                        edtMessageNumberThree.setText(msgNumberThree);
                        edtMessageNumberFour.setText(msgNumberFour);
                        edtMessageNumberFive.setText(msgNumberFive);
                        edtCallNumberOne.setText(callNumberOne);
                        edtAlertMessage.setText(altMessage);

                    }
                }
            }
        });
    }*/

    public void btnEditNumbersSave(View view) {

        EditText edtTxtMessageNumberOne = findViewById(R.id.edtMessageNumberOne);
        EditText edtTxtMessageNumberTwo = findViewById(R.id.edtMessageNumberTwo);
        EditText edtTxtMessageNumberThree = findViewById(R.id.edtMessageNumberThree);
        EditText edtTxtMessageNumberFour = findViewById(R.id.edtMessageNumberFour);
        EditText edtTxtMessageNumberFive = findViewById(R.id.edtMessageNumberFive);
        EditText edtTxtCallNumberOne = findViewById(R.id.edtCallNumberOne);
        EditText edtTxtAlertMessage = findViewById(R.id.edtAlertMessage);

        msgNumberOne = edtTxtMessageNumberOne.getText().toString();
        msgNumberTwo = edtTxtMessageNumberTwo.getText().toString();
        msgNumberThree = edtTxtMessageNumberThree.getText().toString();
        msgNumberFour = edtTxtMessageNumberFour.getText().toString();
        msgNumberFive = edtTxtMessageNumberFive.getText().toString();
        callNumberOne = edtTxtCallNumberOne.getText().toString();
        altMessage = edtTxtAlertMessage.getText().toString();

        userPhone = getIntent().getExtras().getString("phone");


        if (!msgNumberOne.isEmpty() && !msgNumberTwo.isEmpty() && !msgNumberThree.isEmpty() && !callNumberOne.isEmpty() && !altMessage.isEmpty()){

            Contacts contacts = new Contacts(msgNumberOne,msgNumberTwo,msgNumberThree,msgNumberFour,msgNumberFive,callNumberOne,altMessage);
            db = FirebaseDatabase.getInstance();
            reference = db.getReference("Users");
            reference.child(userPhone).child("Contact").setValue(contacts).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(MainActivity4.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity4.this , MainActivity3.class);
                    intent.putExtra("phone",userPhone);
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(MainActivity4.this,"Please Fill all Details !!",Toast.LENGTH_SHORT).show();
        }
    }
}