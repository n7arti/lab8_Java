package frame;

import client.ClientHandler;
import server.LabServer;
import server.ServerHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener {
    private static JList clientList;
    private static JButton close;
    private static JPanel panel;
    private static List<String> clients;
    private static DefaultListModel model;

    public synchronized static void setClients(String client) {
        clients.add(client);
        model = new DefaultListModel();
        model.addAll(clients);
        clientList.setModel(model);
    }

    public synchronized static void delClients(String client) {
        int i = 0;
        for (String user : clients) {
            if (client.equals(user)) {
                clients.remove(i);
                break;
            }
            i++;
        }

        model = new DefaultListModel();
        model.addAll(clients);
        clientList.setModel(model);
    }


    public MainFrame() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new LabServer();
            }
        }).start();

        GridBagLayout layout = new GridBagLayout();
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        GridBagConstraints gbc = new GridBagConstraints();

        clients = new ArrayList<>();
        model = new DefaultListModel();
        model.addAll(clients);
        clientList = new JList(model);

        clientList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        close = new JButton("Отключить");
        close.addActionListener(this);
        panel.add(close, BorderLayout.NORTH);
        panel.add(new JScrollPane(clientList));
        getContentPane().add(panel);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    model = new DefaultListModel();
                    model.addAll(clients);
                    clientList.setModel(model);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Начинаем удаление");
        int i = 0;
        for (int userIndex : clientList.getSelectedIndices()) {
            System.out.println("Начинаем удаление" + (userIndex - i));
            ServerHandler.disconnect(clients.get(userIndex - i));
            i++;
        }

        model = new DefaultListModel();
        model.addAll(clients);
        clientList.setModel(model);
    }
}
