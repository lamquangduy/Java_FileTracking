package Util.Client;

import Util.Constant;
import Util.WatchFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.FolderTracking;

import java.io.*;
import java.net.Socket;

public class SocketClient extends Thread{
    private Socket socket = null;
    private InputStream is = null;
    private BufferedReader br;
    private boolean status = false;
    private String receivedMessage = null;
    private OutputStream os = null;
    BufferedWriter bw = null;
    private ObjectMapper objectMapper = null;


    public void initData(){
        try {
            is=socket.getInputStream();
            os=socket.getOutputStream();
            bw = new BufferedWriter(new OutputStreamWriter(os));
            br=new BufferedReader(new InputStreamReader(is));
            objectMapper = new ObjectMapper();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAction(FolderTracking folderTracking){
        try {
            String message = objectMapper.writeValueAsString(folderTracking);
            bw.write(message);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        try {
            bw.write(message);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        do {
            try {
                receivedMessage = br.readLine();
                System.out.println("Received : " + receivedMessage);
                if (receivedMessage.equalsIgnoreCase("quit")) {
                    this.status = false;
                    break;
                }
                listenMessage(receivedMessage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (true);
    }

    public void listenMessage(String message){
        String[] meStrings = message.split(";");
        String type = meStrings[1];
        String des = meStrings[2];
        if(Constant.trackingFile.equals(type)){
            WatchFile.setPath(des);
        }

    }

    public boolean connect(String host, String port){

        try {
            socket = new Socket(host,Integer.valueOf(port));
            this.status = true;
            initData();
            return true;
        } catch (Exception e) {
            System.out.println("Connect fail");
            return false;
        }
    }

    public Socket getSocket() {
        return socket;
    }
}