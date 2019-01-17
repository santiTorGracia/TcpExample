import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Receiver implements Runnable {
    private final ServerSocket serverSocket;

    public Receiver(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void readLine(BufferedReader br) {
        try {
            String message = br.readLine();
            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(BufferedReader br) {
        try {
            char[] data = new char[1024];
            int bytesRead = br.read(data, 0, data.length);
            if (bytesRead == -1) {
                System.out.println("end of stream");
                return;
            }
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                Socket clientSocket = serverSocket.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                read(br);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
