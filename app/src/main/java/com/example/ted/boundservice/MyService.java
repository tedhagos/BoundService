package com.example.ted.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ServiceConfigurationError;


public class MyService extends Service {

  private final IBinder mbinder = new MyBinder();

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return mbinder;
  }

  public class MyBinder extends Binder {

    public MyService getService() {
      return MyService.this;
    }
  }

  public int getGreatestCommonFactor(int a, int b) {
    int smallno, bigno, rem;


    if(a > b) {
      bigno = a; smallno = b;
    }
    else {
      bigno = b;smallno = a;
    }

    while((rem = bigno % smallno) != 0) {
      bigno = smallno;
      smallno = rem;
    }

    return smallno; // when the loop finishes, smallno is the GCF

  }

}
