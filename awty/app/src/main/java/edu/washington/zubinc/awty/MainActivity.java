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
    AlarmManager alarmManager;

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
            if(isPositiveInteger(time) && this.button.getText().toString().equalsIgnoreCase("START")){
                this.button.setText("STOP");
                String message = this.text.getText().toString();
                String phone = this.phoneNumber.getText().toString();
                int time = Integer.parseInt(this.time.getText().toString());
                Intent intent = new Intent(MainActivity.this, MyReceiver.class);
                intent.putExtra("MESSAGE", message);
                intent.putExtra("PHONE", phone);
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                start(time);
            }
            else if(this.button.getText().toString().equalsIgnoreCase("STOP")) {
                stop();
                this.button.setText("START");
            }
        }
    }

    public void start(int time){
        this.alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 60000 * time;
        this.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 0, interval, pendingIntent);
    }

    public void stop(){
        this.alarmManager.cancel(pendingIntent);
    }

    public boolean isPositiveInteger(EditText time){
        boolean check = false;
        try{
            int n = Integer.parseInt(time.getText().toString());
            if(n > 0)
                check = true;
        } catch(NumberFormatException e) {
            Log.d("TAG", "INVALID TIME!");
        }
        return check;
    }
}
