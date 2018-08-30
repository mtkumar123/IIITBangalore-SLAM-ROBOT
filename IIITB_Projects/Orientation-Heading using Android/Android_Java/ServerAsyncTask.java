package nxt.rotationsensing;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by manojkumar on 6/14/17.
 * This thread is used to transmit the values obtained from the Sensors on the phone through a socketconnection to a known IP Address
 */

public class ServerAsyncTask extends AsyncTask<Socket,Void,Void> {

    @Override
    protected Void doInBackground(Socket... params) {

        Socket socket = params[0];
        try {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while(true) {
                out.println(MainActivity.getPitch_value());
                Log.d("TAG: ", "Printed Pitch");

            }

        } catch (IOException e) {
            Log.e("TAG:", e.toString());
        }

        return null;
    }




}
