package Util;

import model.FolderTracking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<FolderTracking> readFile() throws IOException {
        List<FolderTracking> folderTrackings = new ArrayList<>();
        File file = new File(System.getProperty("user.dir") + File.separator + "log.txt");
        if(file.exists()) {
            FileReader fr = new FileReader(file);
            String line = "";
            while (true) {
                int i = fr.read();
                if (i == -1) {
                    break;
                }
                char ch = (char) i;
                line += ch;
                if (i == 10) {// "\n" = 10 bao hieu la da doc den cuoi dong
                    String[] itemLine = line.trim().split(", ");
                    folderTrackings.add(new FolderTracking(Integer.valueOf(itemLine[0]), itemLine[1], itemLine[2], itemLine[3], itemLine[4]));
                    line = "";
                }
            }
            fr.close();
        }
        return folderTrackings;
    }

    public static void writeFile(List<FolderTracking> folderTrackings) throws IOException {
        File file = new File(System.getProperty("user.dir") + File.separator + "log.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fw=new FileWriter(file);
        for (int i=0;i<folderTrackings.size();i++){
            folderTrackings.get(i).setStt(i+1);
            fw.write(folderTrackings.get(i).toStringServer());
        }
        fw.close();
    }
}