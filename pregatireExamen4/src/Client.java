import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class Client {
    public static void main(String[] args) throws IOException {
        int PORT = 8080;
        try (Socket socket = new Socket("127.0.0.1", PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ){
            out.println("saluitare");
            String rasp = in.readLine();
            System.out.println(rasp);
        }
    }
}
