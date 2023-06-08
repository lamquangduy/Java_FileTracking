package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FolderTracking {
    private String action;
    private String description;
    private int stt;
    private String time;
    private String IpClient;

    public FolderTracking(String action, String des) {
        this.action = action.split("_")[1];
        this.description = this.action + " "+des;
        this.time = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    }

    public FolderTracking(String IpClient, String action, String des) {
        this.IpClient = IpClient;
        this.action = action;
        this.description = des;
        this.time = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
    }

    public FolderTracking(int stt, String ipClient, String time, String action, String description) {
        this.action = action;
        this.description = description;
        this.stt = stt;
        this.time = time;
        IpClient = ipClient;
    }

    public String toStringClient(){
       return  this.stt + ", "+ this.time+", "+this.action+", "+ this.description+"\n";

   }

    public String toStringServer(){
        return  this.stt + ", "+this.getIpClient()+", "+ this.time+", "+this.action+", "+ this.description+"\n";

    }

    public FolderTracking() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIpClient() {
        return IpClient;
    }

    public void setIpClient(String ipClient) {
        IpClient = ipClient;
    }
}