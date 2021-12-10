package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class RunnableClient implements Runnable{
    private Socket socket;
    public RunnableClient() {
        try {//получение строки клиентом
            socket = new Socket("localhost", 8030);
            System.out.println("Client connected to socket");
            Thread.sleep(2000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {//получение строки клиентом
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream ps = new PrintStream(socket.getOutputStream());

            while (!socket.isOutputShutdown()) {
                System.out.println("g");
                if (in.read() > -1) {
                    System.out.println("Читаем с сервера: ");
                    String msg;
                    while (!(msg = dis.readLine()).equals("~"))
                        System.out.println(msg);
                    //String str = in.readUTF();
                    //System.out.println(str);
                }
                System.out.println("alive");
// ждём консоли клиента на предмет появления в ней данных
                //if (br.ready()) {

// данные появились - работаем

                System.out.println("Client start writing in channel...");
                //Thread.sleep(1000);
                String clientCommand = br.readLine();

// пишем данные с консоли в канал сокета для сервера
                ps.println(clientCommand);
                ps.flush();
                //out.writeUTF(clientCommand);
                //out.flush();
                System.out.println("Clien sent message " + clientCommand + " to server.");
                Thread.sleep(1000);
                //socket.shutdownOutput();
                //}
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("ошибка: " + e);
        }
    }
}
