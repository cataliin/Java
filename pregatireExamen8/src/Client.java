import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class Client {
    public static void main(String[] args) {
        Thread thread = new Thread (() ->{
           try (Socket socket=  new Socket("localhost", 8080);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true))
           {
               out.println(1);
               var rasp = in.readLine();
               System.out.println(rasp);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        });
        thread.start();
    }
}
