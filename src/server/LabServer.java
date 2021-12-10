package server;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LabServer {
    static int clientCount = 0;
    public static Map<Integer, Socket> socketMass = new HashMap<>();

    public static void disconnect (int id) {

    }

    public LabServer() {
        try {
            System.out.println("Server work");
            ServerSocket server = new ServerSocket(9090);
            while (true) {
                Socket connection = server.accept();
                socketMass.put(clientCount, connection);
                new Thread(new ServerHandler(connection)).start();
                System.out.println("Size is :" + clientCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
