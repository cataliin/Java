import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        int PORT = 8080;
        List<Apartament> apartamente = CitirileToate.citireAparatamente();
        try (ServerSocket socket = new ServerSocket(PORT)) {
            while(true) {
                Thread t = new Thread(() ->
                {
                    try (Socket client = socket.accept();
                         BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                         PrintWriter out = new PrintWriter(client.getOutputStream(), true)
                    ) {
                        int rasp = Integer.parseInt(in.readLine());
                        var nume = apartamente.stream()
                                .filter(a -> a.getNumarApartament() == rasp)
                                .findFirst()
                                .get();
                        out.println(nume.getNume());

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                );
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
