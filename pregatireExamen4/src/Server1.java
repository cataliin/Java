import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 implements Runnable{
    @Override
    public void run() {
        int PORT = 8080;
        try (ServerSocket socket = new ServerSocket(PORT)){
            while(true){
                try (Socket socketC = socket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socketC.getInputStream()));
                     PrintWriter out = new PrintWriter(socketC.getOutputStream(), true)
                ){
                    String msg = in.readLine();
                    out.println(msg.toUpperCase());

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
