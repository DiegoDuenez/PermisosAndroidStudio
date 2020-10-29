package com.example.practicapermisosandroidstudio;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    final private int REQUEST_CODE_ASK_PERMISSION = 124;
    int hasReadContactsPermission;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.btnCont).setOnClickListener(this);
        findViewById(R.id.btnAlm).setOnClickListener(this);
        findViewById(R.id.btnEmail).setOnClickListener(this);
        findViewById(R.id.btnTel).setOnClickListener(this);
        findViewById(R.id.btnUbi).setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnTel:
                //startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:8711223529")));

                int permisoLlamar = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE);

                if(permisoLlamar != PackageManager.PERMISSION_GRANTED){

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.READ_CONTACTS}, REQUEST_CODE_ASK_PERMISSION);
                        Toast toast1 = Toast.makeText(getApplicationContext(),"La app no tiene los permisos para hacer la llamada", Toast.LENGTH_SHORT);
                        toast1.show();
                        return;

                    }


                }
                hacerLlamada();


                break;

            case R.id.btnEmail:
                Intent correo = new Intent(Intent.ACTION_SEND);
                correo.putExtra(Intent.EXTRA_EMAIL, "19170154@uttcampus.edu.mx");
                correo.putExtra(Intent.EXTRA_SUBJECT, "PRUEBA DE PERMISOS");
                correo.putExtra(Intent.EXTRA_TEXT, "Texto...");
                startActivity(Intent.createChooser(correo, "Enviar"));

                break;
            case R.id.btnUbi:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0")));
                break;


        }

    }
    public void hacerLlamada(){
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:8711223529")));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_CODE_ASK_PERMISSION){

            for(int x = 0; x <= permissions.length; x++){
                if(grantResults[x]==PackageManager.PERMISSION_GRANTED){
                    hacerLlamada();
                }
            }


        }

        /*if(requestCode==REQUEST_CODE_ASK_PERMISSION){

            if(permissions.length>=1){
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    hacerLlamada();
                }
            }

        }*/


    }



}