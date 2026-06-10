import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {
    public static void main(String[] args) throws IOException {
        int PORT = 8080;
        try(ServerSocket socket = new ServerSocket(PORT)){
            while(true){
                Socket socketClient = socket.accept();
                Thread thread = new Thread(() -> prelucrare(socketClient));
                thread.start();
            }
        }
    }
    public static void prelucrare (Socket socket){
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String req = in.readLine();
            out.println(req.toUpperCase());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
