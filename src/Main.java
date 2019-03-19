import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;

        try {
            ServerSocket receiverSocket = new ServerSocket(port);
            Receiver receiver = new Receiver(receiverSocket);
            Thread receiverThread = new Thread(receiver);
            receiverThread.start();


            Socket senderSocket = new Socket(hostName, port);
            PrintWriter out =
                    new PrintWriter(senderSocket.getOutputStream(), true);
            Sender sender = new Sender(out);

            sender.send("hello world\n");
            Thread.sleep(2000);
            senderSocket.close();
            receiverThread.interrupt();
            receiverSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
