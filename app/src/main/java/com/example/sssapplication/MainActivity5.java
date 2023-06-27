package com.example.sssapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity5 extends AppCompatActivity {

    String Feedback,userPhone;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    public void onBtnFeedbackSubmit (View view) {

        EditText edtTxtFeedback = findViewById(R.id.edtTxtFeedback);

        Feedback = edtTxtFeedback.getText().toString();

        userPhone = getIntent().getExtras().getString("phone");

        if (!Feedback.isEmpty()){

            Feedback feedback = new Feedback(Feedback);
            db = FirebaseDatabase.getInstance();
            reference = db.getReference("Users");
            reference.child(userPhone).child("Feedback").setValue(feedback).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Toast.makeText(MainActivity5.this,"Feedback Submitted",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(MainActivity5.this,"Please Fill all Details !!",Toast.LENGTH_SHORT).show();
        }
    }
}