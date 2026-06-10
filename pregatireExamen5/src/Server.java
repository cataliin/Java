import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
        List<Aventura>aventuri = Main.citireJSON("date\\aventuri.json");
        int PORT  = 8080;
        try(ServerSocket ss = new ServerSocket(PORT)){
            while(true){
                Socket client = ss.accept();
                Thread t = new Thread(() -> {
                    try {
                        prelucrare(client, aventuri);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                t.start();
            }
        }
    }

    public static void prelucrare(Socket socket, List<Aventura> aventuri) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ){
            String denumire = in.readLine();
            System.out.println("Clientul a cerut : " + denumire);

            int locuri = aventuri.stream()
                    .filter(a -> a.getDenumire().equalsIgnoreCase(denumire))
                    .findFirst()
                    .map(Aventura::getLocuri_disponibile)
                    .orElse(-1);
            System.out.println("Locuri disponibile " + locuri);
            out.println(locuri);
        }
    }
}
