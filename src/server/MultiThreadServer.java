package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultiThreadServer implements Runnable{
    public MultiThreadServer() {
    }

    @Override
    public void run() {
        DateFormat connectTime = new SimpleDateFormat("HH:mm:ss");
        Date date;
        Socket connection = null;
        try {//посылка строки клиенту
            ServerSocket server = new ServerSocket(8030);
            while (connection == null)
                connection = server.accept();


            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            DataInputStream in = new DataInputStream(connection.getInputStream());
            BufferedReader dis = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintStream ps = new PrintStream(connection.getOutputStream());
            date = new Date();
            long dateStart = date.getTime();
            System.out.println("start - " + connectTime.format(dateStart));


            ps.println(" Соединение установлено" + "\n~");
            ps.flush();
            //out.writeUTF("Соединение установлено");

            //out.flush();
            System.out.println("Соединение установлено");
            while (!connection.isClosed()) {
                String entry = dis.readLine();//in.readUTF();

                System.out.println("Получено сообщение - " + entry);

                if (entry.contains("quit")) {
                    date = new Date();
                    long dateFinish = date.getTime();
                    System.out.println("finish - " + connectTime.format(dateFinish));
                    date.setTime(dateFinish - dateStart);
                    String mess = null;

                    mess += "Получена команда на выход\n";
                    mess += "Соединение прервано. Время вашего подключения - " + connectTime.format(dateFinish - dateStart);
                    mess += "\n~";
                    ps.println(mess);
                    ps.flush();
                    //out.writeUTF("Получена команда на выход");
                    //out.writeUTF("Соединение прервано. Время вашего подключения - " + connectTime);
                    //out.flush();
                    break;
                }
                else if (entry.contains("your_Mega_Command")) {
                    ;
                }
                else {
                    ps.println(" Ты прав, бро\n~");
                    ps.flush();
                    //out.writeUTF("Ты прав, бро");
                    //out.flush();
                }
            }

            connection.close(); // разрыв соединения
        } catch (IOException e) {
            System.out.println("ошибка: " + e);
        }

    }
}
