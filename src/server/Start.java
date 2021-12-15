package server;

import frame.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start {
    public  static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame serv = new MainFrame();

                serv.setTitle("Сервер");
                serv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                serv.setSize(9*50,15*50);
                serv.setLocationRelativeTo(null);
                serv.setVisible(true);
            }
        });
    }
}
