package Util.Server;

import Util.Util;
import model.Client;
import model.FolderTracking;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException; 
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer extends Thread {
    private ServerSocket s = null;
    private volatile JTextPane tpKetNoi;
    public List<Client> clients = new ArrayList<>();
    private Socket ss = null;
    public SocketServer(JTextPane tpKetNoi) {
        try {
            this.tpKetNoi = tpKetNoi;
            s = new ServerSocket(3000);
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getS() {
        return s;
    }

    public void setS(ServerSocket s) {
        this.s = s;
    }

    public Socket getSs() {
        return ss;
    }

    public void setSs(Socket ss) {
        this.ss = ss;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            while (true) {
                ss = s.accept(); //synchronous

                InetSocketAddress sockaddr = (InetSocketAddress) ss.getRemoteSocketAddress();
                InetAddress inaddr = sockaddr.getAddress();
                clients.add(new Client(inaddr.toString(), ss.getPort()));
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        try {
                            List<FolderTracking> folderTrackings = Util.readFile();
                            folderTrackings.add(new FolderTracking(inaddr.toString(), "KET NOI", "Ket Noi Server"));
                            Util.writeFile(folderTrackings);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        tpKetNoi.setText(tpKetNoi.getText() + "IP: " + inaddr.toString() + " PORT: " + ss.getPort() + " Kết Nối\n");
                    }
                });
                ServerListens serverListens = new ServerListens(ss, tpKetNoi);
            }
        } catch (InterruptedException | InvocationTargetException | IOException e) {
            System.out.println("There're some error");
        }
    }
    
}