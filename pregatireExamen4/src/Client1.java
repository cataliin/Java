import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client1 implements Runnable{
    @Override
    public void run() {
        int PORT = 8080;
        try (Socket socket = new Socket("127.0.0.1", PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ){
            out.println("Salutmuie");
            String msg = in.readLine();
            System.out.println(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
