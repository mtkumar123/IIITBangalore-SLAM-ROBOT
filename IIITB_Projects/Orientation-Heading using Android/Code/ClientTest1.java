
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author manojkumar
 * This program is written in Netbeans. It runs on the PC, and acts as a client in the server client
 * architecture where the phone is acting as the server, by providing the sensor values. 
 */
public class ClientTest1 {
    
    public static void main(String args[])throws IOException
    {
        Socket s = new Socket("192.168.0.101", 9090);
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer;
        answer = input.readLine();
        System.out.println(answer);
       
    }
    
}
