package com.example.ted.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "BoundService";

  MyService mservice;
  boolean servicestatus = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btnBind = (Button) findViewById(R.id.btnBind);
    Button btnUnbind = (Button) findViewById(R.id.btnUnbind);
    Button btnGCF = (Button) findViewById(R.id.btnGCF);

    EditText etfno = (EditText) findViewById(R.id.etfirstno);
    EditText etsno = (EditText) findViewById(R.id.etsecondno);

    assert btnBind != null;
    btnBind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(getBaseContext(), MyService.class);
        bindService(i,conn, Context.BIND_AUTO_CREATE);
        servicestatus = true;
        Log.d(TAG, "Service is already bound");
      }
    });

    assert btnUnbind != null;
    btnUnbind.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(servicestatus) {
          unbindService(conn);
          servicestatus = false;
          Log.d(TAG, "Service was unbound");
        }
        else {
          Log.d(TAG, "Service is already unbound, no further action taken");
        }
      }
    });

    assert btnGCF != null;
    btnGCF.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int a = Integer.parseInt(((EditText) findViewById(R.id.etfirstno)).getText().toString());
        int b = Integer.parseInt(((EditText) findViewById(R.id.etsecondno)).getText().toString());

        int result = mservice.getGreatestCommonFactor(a,b);

        ((TextView)findViewById(R.id.txtResult)).setText(new Integer(result).toString());

      }
    });

  }

  ServiceConnection conn = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      MyService.MyBinder binder = (MyService.MyBinder) service;
      mservice = binder.getService();
      servicestatus = true;
      Log.d(TAG, "onServiceConnected, service status is true");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
  };

}
