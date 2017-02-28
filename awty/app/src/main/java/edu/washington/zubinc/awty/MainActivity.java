package edu.washington.zubinc.awty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text;
    EditText phoneNumber;
    EditText time;
    Button button;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.text = (EditText)findViewById(R.id.message);
        this.phoneNumber = (EditText)findViewById(R.id.phoneNumber);
        this.time = (EditText)findViewById(R.id.time);
        this.button = (Button)findViewById(R.id.button);

        this.button.setOnClickListener(new MyListener(this.text, this.phoneNumber, this.time, button));

    }

    public class MyListener implements View.OnClickListener{

        EditText text;
        EditText phoneNumber;
        EditText time;
        Button button;

        public MyListener(EditText text, EditText phoneNumber, EditText time, Button button){
            this.text = text;
            this.phoneNumber = phoneNumber;
            this.button = button;
            this.time = time;
        }

        @Override
        public void onClick(View v) {
            if(isNumber(time)){
                this.button.setText("STOP");
                String message = this.text.getText().toString();
                String phone = this.phoneNumber.getText().toString();
                int time = Integer.parseInt(this.time.getText().toString());
                Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
                start(time);
            }
        }
    }

    public void start(int time){
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 60000 * time;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, interval, pendingIntent);
    }

    public boolean isNumber(EditText time){
        boolean check = false;
        try{
            int n = Integer.parseInt(time.getText().toString());
            check = true;
        } catch(NumberFormatException e) {
            Log.d("TAG", "IS NOT NUMBER!");
        }
        return check;
    }
}
