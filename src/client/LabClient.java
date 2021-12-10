package client;

import java.io.*;
import java.net.*;

public class LabClient {
    public LabClient() {
        for (int i = 1; i < 3; i++) {
            new Thread(new ClientHandler("" + i)).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new LabClient();
    }
}
