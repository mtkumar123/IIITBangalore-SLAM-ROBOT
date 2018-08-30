package nxt.rotationsensing;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
This is the main activity of the RotationSensing app for Android Devices. This app uses the Magnetometer and Accelerometer sensors on the
phone to obtain roll, pitch and azimuth values.
Azimuth value is the reading of the phones angle with respect to the north pole, when rotated about the z-axis, which passes perpendicularly through
the phone screen.
Pitch value is the reading of the phones horizontal axis or the vertical tilt of the phone.
Roll value is the reading of the phones y-axis, or the horizontal tilt of the phone.

These values are then sent over a established SocketConnection to a known IP Address.
 */

public class MainActivity extends Activity implements SensorEventListener {

    private TextView azimuth,roll,pitch;
    private final int SERVER_PORT = 9090;

    SensorManager senseManager;
    Sensor mAccelerometer;
    Sensor mMagnetometer;
    float[] mGravity;
    float[] mGeoMagnetic;
    long timestamp = 0;

    static int azimuth_value = 0 ,roll_value = 0,pitch_value = 0;


    @Override
    protected void onResume()
    {
        super.onResume();

            senseManager.registerListener(this, mAccelerometer, 1000000000);
            senseManager.registerListener(this, mMagnetometer, 1000000000);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializes the TextView widgets
        pitch = (TextView)findViewById(R.id.TextViewPitch);
        azimuth = (TextView) findViewById(R.id.TextViewAzimuth);
        roll = (TextView) findViewById(R.id.TextViewRoll);

        //initializes the Sensor Manager and the sensor variables
        senseManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = senseManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = senseManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        /*
        The below thread is a AsyncTask thread, it is responsible for establishing the SocketConnection
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket socServer = new ServerSocket(SERVER_PORT);
                    Socket socClient = null;

                    Log.v("TAG: ", "Going to listen for socket connections");
                    socClient = socServer.accept();
                    Log.v("TAG:", "It has been accepted");
                    ServerAsyncTask serverAsyncTask = new ServerAsyncTask();
                    serverAsyncTask.execute(new Socket[]{socClient});

                } catch (IOException e) {
                    Log.e("TAG:", e.toString());
                }
            }
        }).start();
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

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Log.d("TAG EVENT TIME", Long.toString(sensorEvent.timestamp));

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //Log.d("Sensor type ", "Accelerometer");
            mGravity = sensorEvent.values;
            //if (mGravity == null)
                //Log.d("Empty", "Gravity is empty");
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            //Log.d("Sensor type", "Magnetometer");
            mGeoMagnetic = sensorEvent.values;
            //if (mGeoMagnetic == null)
                //Log.d("Empty", "Magnetic field is empty");
        }

        if ((mGravity != null) && (mGeoMagnetic != null)) {
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeoMagnetic);
            if (success) {
                //Log.d("Success", Boolean.toString(success));
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);


                azimuth_value = (int) Math.toDegrees(orientation[0]);
                pitch_value = (int) Math.toDegrees(orientation[1]);
                roll_value = (int) Math.toDegrees(orientation[2]);

                if ((sensorEvent.timestamp - timestamp) >= 300000000) {
                    Log.d("In the update", Long.toString(sensorEvent.timestamp));
                    azimuth.setText(Integer.toString(azimuth_value));
                    pitch.setText(Integer.toString(pitch_value));
                    roll.setText(Integer.toString(roll_value));
                    timestamp = sensorEvent.timestamp;
                }
            }


        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause()
    {
        super.onPause();

        senseManager.unregisterListener(this);
    }


    public static int getAzimuth()
    {
        return azimuth_value;
    }

    public static int getRoll_value()
    {
        return roll_value;
    }

    public static int getPitch_value()
    {
        return pitch_value;
    }


}
