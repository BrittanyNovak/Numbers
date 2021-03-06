package com.example.student.numbers;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collections;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        // Default Everything
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
    }

    private final SensorEventListener mSensorListener = new SensorEventListener(){

        public void onSensorChanged(SensorEvent event){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double)(x*x + y*y + z*z));

            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.10f + delta;

            if(mAccel > 12){
                Toast toast = Toast.makeText(getApplicationContext(), "Here are your Powerball Numbers", Toast.LENGTH_LONG);
                toast.show();
                getRandomNum();
                getRandNumb();

            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy){

        }
    };

    private void getRandomNum(){
        EditText etRandomNum = (EditText)findViewById(R.id.etRandomNums);
        Random r = new Random();

        int i1 = r.nextInt(59) + 1;
        int i2 = r.nextInt(59) + 1;
        int i3 = r.nextInt(59) + 1;
        int i4 = r.nextInt(59) + 1;
        int i5 = r.nextInt(59) + 1;
        //Powerball Number


        String display = String.valueOf(i1) + " " + String.valueOf(i2) + " "
               + String.valueOf(i3) + " " + String.valueOf(i4) + " "
                       + String.valueOf(i5);
        etRandomNum.setText(display);



    }

    private void getRandNumb(){
        EditText etRandNumb = (EditText)findViewById(R.id.etRandNumb);
        Random b = new Random();

        int ip = b.nextInt(35) + 1;

        String display = String.valueOf(ip);

        etRandNumb.setText(display);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
