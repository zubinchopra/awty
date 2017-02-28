package edu.washington.zubinc.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
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
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, message, null, null);
        Toast.makeText(context, "Message Sent!", Toast.LENGTH_SHORT).show();
    }
}
