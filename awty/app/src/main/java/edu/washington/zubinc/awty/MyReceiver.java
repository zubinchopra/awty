package edu.washington.zubinc.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "";
        String phone = "";
        try {
            message = intent.getStringExtra("MESSAGE");
            phone = intent.getStringExtra("PHONE");
        } catch(NullPointerException e){
            Log.d("TAG", "message not reached");
        }
        Toast.makeText(context, phone + ":" + message, Toast.LENGTH_SHORT).show();
    }
}
