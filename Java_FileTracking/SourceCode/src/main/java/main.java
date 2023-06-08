import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        Thread thread1 = new Thread(() -> {
            Client client = new Client("C:\\\\Users\\\\ASUS\\\\Documents\\\\NetBeansProjects\\\\NetWorking\\\\Data1");
            client.setVisible(true);
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            Client client = new Client("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\NetWorking\\Data2");
            client.setVisible(true);
        });
        thread2.start();

        try {
            Server server = new Server();
            server.setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
