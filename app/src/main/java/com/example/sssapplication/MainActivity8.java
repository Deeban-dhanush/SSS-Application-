package com.example.sssapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity8 extends AppCompatActivity {

    String userPhone,msgNumberOne,msgNumberTwo,msgNumberThree,msgNumberFour,msgNumberFive,callNumberOne,altMessage;
    FirebaseDatabase db;
    DatabaseReference reference;
    public static final int PICK_CONTACT = 1;
    EditText edtTxtMessageNumberOne;
    EditText edtTxtMessageNumberTwo;
    EditText edtTxtMessageNumberThree;
    EditText edtTxtMessageNumberFour;
    EditText edtTxtMessageNumberFive;
    EditText edtTxtCallNumberOne;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        /*edtTxtMessageNumberOne = findViewById(R.id.edtTxtMessageNumberOne);
        edtTxtMessageNumberTwo = findViewById(R.id.edtTxtMessageNumberTwo);
        edtTxtMessageNumberThree = findViewById(R.id.edtTxtMessageNumberThree);
        edtTxtMessageNumberFour = findViewById(R.id.edtTxtMessageNumberFour);
        edtTxtMessageNumberFive = findViewById(R.id.edtTxtMessageNumberFive);
        edtTxtCallNumberOne = findViewById(R.id.edtTxtCallNumberOne);

        edtTxtMessageNumberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        edtTxtMessageNumberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        edtTxtMessageNumberThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        edtTxtMessageNumberFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        edtTxtMessageNumberFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });

        edtTxtCallNumberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });*/

    }


    /*@Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {

                    Uri contactData = data.getData();
                    Cursor c =  managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {


                        String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        @SuppressLint("Range") String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                        if (hasPhone.equalsIgnoreCase("1")) {
                            Cursor phones = getContentResolver().query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                    null, null);
                            phones.moveToFirst();
                            @SuppressLint("Range") String cNumber = phones.getString(phones.getColumnIndex("data1"));
                            *//*edtTxtMessageNumberOne.setText(cNumber);
                            edtTxtMessageNumberTwo.setText(cNumber);
                            edtTxtMessageNumberThree.setText(cNumber);
                            edtTxtMessageNumberFour.setText(cNumber);
                            edtTxtMessageNumberFive.setText(cNumber);
                            edtTxtCallNumberOne.setText(cNumber);*//*

                            *//*switch (){
                                case :
                                {

                                }
                                case :
                                {

                                }
                                case :
                                {

                                }
                                case :
                                {

                                }
                                case :
                                {

                                }
                                case :
                                {

                                }*//*

                        }


                    }
                }
                break;
        }
    }*/

    public void btnNumbersSave(View view) {

        EditText edtTxtMessageNumberOne = findViewById(R.id.edtTxtMessageNumberOne);
        EditText edtTxtMessageNumberTwo = findViewById(R.id.edtTxtMessageNumberTwo);
        EditText edtTxtMessageNumberThree = findViewById(R.id.edtTxtMessageNumberThree);
        EditText edtTxtMessageNumberFour = findViewById(R.id.edtTxtMessageNumberFour);
        EditText edtTxtMessageNumberFive = findViewById(R.id.edtTxtMessageNumberFive);
        EditText edtTxtCallNumberOne = findViewById(R.id.edtTxtCallNumberOne);
        EditText edtTxtAlertMessage = findViewById(R.id.edtTxtAlertMessage);

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
                    Toast.makeText(MainActivity8.this,"Successfully Saved",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME,0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("hasLoggedIn",true);
                    editor.commit();
                    Intent intent = new Intent(MainActivity8.this , MainActivity3.class);
                    intent.putExtra("phone",userPhone);
                    startActivity(intent);


                }
            });
        } else {
            Toast.makeText(MainActivity8.this,"Please Fill all Details !!",Toast.LENGTH_SHORT).show();
        }
    }
}