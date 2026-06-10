import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Server {
    public static void main(String[] args) throws IOException, SQLException {
        List<Student> studenti = DataManager.citireBD();
        List<Facultate> facultati = DataManager.citireJSON("Date\\json.json");
        try (ServerSocket server = new ServerSocket(8080)){
            while(true){
                Socket client = server.accept();
                Thread thread = new Thread(() ->{
                    try(BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true)
                    ) {
                        int nr= Integer.parseInt(in.readLine());
                        var mapa = studenti.stream()
                                .filter(x -> x.getMedieAdmitere() > 5)
                                .collect(Collectors.groupingBy(Student::getCodFacultate, Collectors.counting()));
                        long rasp;
                        for(var i : facultati){
                            if (i.getCod_facultate() == nr){
                                rasp = i.getNumar_locuri()- mapa.getOrDefault(i.getCod_facultate(), 0l);
                                out.println(rasp);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                thread.start();
            }
        }
    }
}
