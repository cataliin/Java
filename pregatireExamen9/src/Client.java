import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(() -> {
            try (Socket socket = new Socket("localhost",8080);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ){
                out.println("Cibernetica");
                var rasp = in.readLine();
                System.out.println(rasp);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public static void start() throws Exception {
        Thread thread = new Thread(() -> {
            try (Socket socket = new Socket("localhost",8080);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ){
                out.println("Cibernetica");
                var rasp = in.readLine();
                System.out.println(rasp);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
