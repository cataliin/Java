import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Server {
    public static void main(String[] args) throws IOException, SQLException {
        List<Specializare> specializari = ManagerDate.citireSpecializari();
        List<Candidat> candidati = ManagerDate.citireCandidati("date\\inscrieri.txt");
        Map<Integer, Long> mapa = candidati.stream()
                .collect(Collectors.groupingBy(Candidat::getCod_specializare_aleasa, Collectors.counting()));
        try(ServerSocket server = new ServerSocket(8080)){
                Socket client = server.accept();
                Thread thread = new Thread(()->{
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                         PrintWriter out = new PrintWriter(client.getOutputStream(), true)
                    ){
                        String cl = in.readLine();
                        int cod = specializari.stream()
                                .filter(x->x.getDenumire().equalsIgnoreCase(cl))
                                .map(Specializare::getCod)
                                        .findFirst()
                                                .get();
                        int locuri = specializari.stream()
                                .filter(x -> x.getCod() == cod)
                                .findFirst()
                                .get()
                                .getLocuri();
                        out.println(locuri- mapa.getOrDefault(cod, 0l));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                thread.start();
                thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void start() throws Exception{
        List<Specializare> specializari = ManagerDate.citireSpecializari();
        List<Candidat> candidati = ManagerDate.citireCandidati("date\\inscrieri.txt");
        Map<Integer, Long> mapa = candidati.stream()
                .collect(Collectors.groupingBy(Candidat::getCod_specializare_aleasa, Collectors.counting()));
        try(ServerSocket server = new ServerSocket(8080)){
            Socket client = server.accept();
            Thread thread = new Thread(()->{
                try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true)
                ){
                    String cl = in.readLine();
                    int cod = specializari.stream()
                            .filter(x->x.getDenumire().equalsIgnoreCase(cl))
                            .map(Specializare::getCod)
                            .findFirst()
                            .get();
                    int locuri = specializari.stream()
                            .filter(x -> x.getCod() == cod)
                            .findFirst()
                            .get()
                            .getLocuri();
                    out.println(locuri- mapa.getOrDefault(cod, 0l));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
