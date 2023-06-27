package com.example.sssapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public static String PREFS_NAME = "MyPrefsFile";

    ActivityResultLauncher<String[]> mPermissionResultLauncher;
    private boolean isCallPermissionGranted = false;
    private boolean isContactPermissionGranted = false;
    private boolean isMessagePermissionGranted = false;
    private boolean isLocationPermissionGranted = false;
    private boolean isReadPermissionGranted = false;
    private boolean isRecordPermissionGranted = false;
    private boolean isVideoPermissionGranted = false;
    String userPhone,userPassword;
    boolean isValid = false;
    DatabaseReference reference;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mPermissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {

                if (result.get(Manifest.permission.READ_CONTACTS) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.READ_CONTACTS);

                }

                if (result.get(Manifest.permission.CALL_PHONE) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.CALL_PHONE);

                }

                if (result.get(Manifest.permission.SEND_SMS) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.SEND_SMS);

                }

                if (result.get(Manifest.permission.ACCESS_FINE_LOCATION) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.ACCESS_FINE_LOCATION);

                }

                if (result.get(Manifest.permission.READ_EXTERNAL_STORAGE) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.READ_EXTERNAL_STORAGE);

                }

                if (result.get(Manifest.permission.RECORD_AUDIO) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.RECORD_AUDIO);

                }

                if (result.get(Manifest.permission.CAMERA) != null){

                    isReadPermissionGranted = result.get(Manifest.permission.CAMERA);

                }
            }
        });

        requestPermission();

    }

    private void requestPermission(){

        isContactPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED;

        isCallPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED;

        isMessagePermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
        ) == PackageManager.PERMISSION_GRANTED;

        isLocationPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED;

        isReadPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED;

        isRecordPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED;

        isVideoPermissionGranted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;

        List<String> permissionRequest = new ArrayList<String>();

        if (!isContactPermissionGranted){

            permissionRequest.add(Manifest.permission.READ_CONTACTS);

        }

        if (!isCallPermissionGranted){

            permissionRequest.add(Manifest.permission.CALL_PHONE);

        }

        if (!isMessagePermissionGranted){

            permissionRequest.add(Manifest.permission.SEND_SMS);

        }

        if (!isLocationPermissionGranted){

            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION);

        }

        if (!isReadPermissionGranted){

            permissionRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        }

        if (!isRecordPermissionGranted){

            permissionRequest.add(Manifest.permission.RECORD_AUDIO);

        }

        if (!isVideoPermissionGranted){

            permissionRequest.add(Manifest.permission.CAMERA);

        }

        if (!permissionRequest.isEmpty()){

            mPermissionResultLauncher.launch(permissionRequest.toArray(new String[0]));

        }
    }

    @SuppressLint("SetTextI18n")
    public void onBtnClick (View view) {

        EditText edtTxtMobileNo = findViewById(R.id.edtTxtMobileNo);
        EditText edtTxtPass = findViewById(R.id.edtTxtPass);

        userPhone = edtTxtMobileNo.getText().toString();
        userPassword = edtTxtPass.getText().toString();

        if (!userPhone.isEmpty() && !userPassword.isEmpty()){

            readData(userPhone);

        }else{

            Toast.makeText(this, "Please Fill all Details !!", Toast.LENGTH_SHORT).show();
        }

    }

    private void readData(String phone) {

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(phone).child("Details").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        DataSnapshot dataSnapshot = task.getResult();
                        String phone = String.valueOf(dataSnapshot.child("phone").getValue());
                        String newPassword = String.valueOf(dataSnapshot.child("newPassword").getValue());
                        isValid = validate(phone,newPassword);
                        if(!isValid) {
                            Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                        } else {
                            sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME,0);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("hasLoggedIn",true);
                            editor.putString("userPhone",userPhone);
                            editor.apply();
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this , MainActivity3.class);
                            intent.putExtra("phone",userPhone);
                            startActivity(intent);
                        }

                    }else {

                        Toast.makeText(LoginActivity.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();

                    }

                }else {

                    Toast.makeText(LoginActivity.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean validate(String mobile,String key){

        if(mobile.equals(userPhone) && key.equals(userPassword)){
            return true;
        }
        return false;
    }

    public void onBtnClickNew(View view) {
        Intent intent = new Intent(LoginActivity.this , MainActivity2.class);
        startActivity(intent);
    }

    public void onBtnClickForgot(View view) {
        Intent intent = new Intent(LoginActivity.this , MainActivity9.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, ReactivateService.class);
        this.sendBroadcast(broadcastIntent);
        super.onDestroy();
    }
}