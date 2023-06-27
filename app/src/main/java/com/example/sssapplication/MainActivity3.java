package com.example.sssapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    private SensorManager sm;
    private float acelVal,acelLast,shake;
    private static int counter = 0;
    DatabaseReference reference;
    FusedLocationProviderClient fusedLocationProviderClient;
    String userPhone,msgNumberOne,msgNumberTwo,msgNumberThree,msgNumberFour,msgNumberFive,callNumberOne,callNumberTwo,callNumberThree,altMessage;
    String loc = "http://maps.google.com/?q=";
    String latitude,longitude;
    private final static int REQUEST_CODE = 100;
    AlertDialog.Builder alert;
    SharedPreferences sharedPreferences;


    @SuppressLint("MissingInflatedId")

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME,0);
        userPhone = sharedPreferences.getString("userPhone","");

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        /*sm=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(sensorListener,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelVal=SensorManager.GRAVITY_EARTH;
        acelLast=SensorManager.GRAVITY_EARTH;
        shake=0.00f;*/

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit_profile:
                    {

                        Intent intent = new Intent(MainActivity3.this , MainActivity7.class);
                        intent.putExtra("phone",userPhone);
                        startActivity(intent);
                        /*MainActivity4 start = new MainActivity4();
                        start.retrieveDetails(userPhone);*/
                        break;
                    }
                    case R.id.edit_emergency_contacts:
                    {
                        Intent intent = new Intent(MainActivity3.this , MainActivity4.class);
                        intent.putExtra("phone",userPhone);
                        startActivity(intent);
                        break;
                    }
                    case R.id.about_us:
                    {
                        Intent intent = new Intent(MainActivity3.this , MainActivity6.class);
                        startActivity(intent);
                        break;
                    }
                    case R.id.help_feedback:
                    {
                        Intent intent = new Intent(MainActivity3.this , MainActivity5.class);
                        intent.putExtra("phone",userPhone);
                        startActivity(intent);
                        break;
                    }
                    case R.id.log_out:
                    {
                        alert = new AlertDialog.Builder(MainActivity3.this);
                        alert.setTitle("Shake Send Save");
                        alert.setMessage("Logout From This Device?");
                        alert.setCancelable(true);
                        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity3.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                                sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME,0);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("hasLoggedIn",false);
                                editor.apply();
                                Intent intent = new Intent(MainActivity3.this , LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        final AlertDialog dlg = alert.create();
                        dlg.show();
                    }
                }
                return false;
            }
        });


    }

    /*private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x =event.values[0];
            float y =event.values[1];
            float z =event.values[2];
            acelLast=acelVal;
            acelVal=(float) Math.sqrt((double) (x*x)+(y*y)+(z*z));
            float delta= acelVal-acelLast;
            shake =shake*0.9f+delta;

            if (shake > 20){
                counter = counter + 1 ;
            }
            if (counter >= 17) {
                counter = 0;
                getActivate();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };*/

    public void onClickActivate(View view) {
        getActivate();
    }

    public void getActivate() {

        Vibrator vibrator;
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if (!vibrator.hasVibrator()) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                    VibrationEffect.createOneShot(10000,VibrationEffect.EFFECT_DOUBLE_CLICK)
            );

        } else {
            long[] pattern = {0, 800, 200};
            vibrator.vibrate(pattern, 10);
        }

        alert = new AlertDialog.Builder(this);
        alert.setTitle("Shake Send Save");
        alert.setMessage("Do you want to activate?");
        alert.setCancelable(true);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity3.this, "Activated Successfully", Toast.LENGTH_SHORT).show();
                vibrator.cancel();
                smsSend(userPhone);
                getLastLocation();

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity3.this, "Not Activated Successfully", Toast.LENGTH_SHORT).show();
                vibrator.cancel();
            }
        });
        final AlertDialog dlg = alert.create();
        //dlg.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dlg.show();

        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (dlg.isShowing()) {
                    dlg.dismiss();
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 10000);

    }

    public void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){


            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            if (location != null){



                                try {
                                    Geocoder geocoder = new Geocoder(MainActivity3.this, Locale.getDefault());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    latitude = String.valueOf(addresses.get(0).getLatitude());
                                    longitude = String.valueOf(addresses.get(0).getLongitude());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }

                        }
                    });


        }else {

            askPermission();


        }


    }

    private void askPermission() {

        ActivityCompat.requestPermissions(MainActivity3.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {

        if (requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else {
                Toast.makeText(MainActivity3.this,"Please provide the required permission",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void smsSend(String userPhone) {
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(userPhone).child("Contact").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        DataSnapshot dataSnapshot = task.getResult();
                        msgNumberOne = String.valueOf(dataSnapshot.child("msgNumberOne").getValue());
                        msgNumberTwo = String.valueOf(dataSnapshot.child("msgNumberTwo").getValue());
                        msgNumberThree = String.valueOf(dataSnapshot.child("msgNumberThree").getValue());
                        msgNumberFour = String.valueOf(dataSnapshot.child("msgNumberFour").getValue());
                        msgNumberFive = String.valueOf(dataSnapshot.child("msgNumberFive").getValue());
                        callNumberOne = String.valueOf(dataSnapshot.child("callNumberOne").getValue());
                        altMessage = String.valueOf(dataSnapshot.child("altMessage").getValue());

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(msgNumberOne,null,altMessage+" "+loc+latitude+","+longitude,null,null);
                        smsManager.sendTextMessage(msgNumberTwo,null,altMessage+" "+loc+latitude+","+longitude,null,null);
                        smsManager.sendTextMessage(msgNumberThree,null,altMessage+" "+loc+latitude+","+longitude,null,null);
                        if (!msgNumberFour.isEmpty()){
                            smsManager.sendTextMessage(msgNumberFour,null,altMessage+" "+loc+latitude+","+longitude,null,null);
                        }
                        if (!msgNumberFive.isEmpty()){
                            smsManager.sendTextMessage(msgNumberFive,null,altMessage+" "+loc+latitude+","+longitude,null,null);
                        }


                        Intent callOne = new Intent(Intent.ACTION_CALL);
                        callOne.setData(Uri.parse("tel:" +callNumberOne));
                        startActivity(callOne);

                    }

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            new AlertDialog.Builder(this).setMessage("Are you sure Yo want to Exit?").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finishAffinity();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        };
    }

}