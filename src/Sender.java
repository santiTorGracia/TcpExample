import java.io.PrintWriter;

public class Sender {

    private final PrintWriter out;

    public Sender(PrintWriter out) {
        this.out = out;
    }

    public void send(String message) {
        out.write(message);
    }

    public void print(String message) {
        out.println(message);
    }
}