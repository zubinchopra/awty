package edu.washington.zubinc.awty;

import android.app.Service;
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
                String time = this.time.getText().toString();
                Toast toast = Toast.makeText(getApplicationContext(), phone + ":" + message, Toast.LENGTH_SHORT);
                toast.show();
                Bundle b = new Bundle();
                b.putString("MESSAGE", message);
                b.putString("PHONE", phone);
                b.putString("TIME", time);
                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                intent.putExtras(b);
                startService(intent);
            }
        }
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
