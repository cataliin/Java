import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        List<Student> studenti = DataManager.citireBD();
//        studenti.stream().forEach(System.out::println);

        List<Facultate> facultati = DataManager.citireJSON("Date\\json.json");
//        facultati.stream().forEach(System.out::println);

        System.out.println("\nCerinta 1\n");
        facultati.stream()
                .filter(x -> x.getNumar_locuri() > 60)
                .forEach(System.out::println);
        System.out.println("\nCerinta 2\n");
        var mapa = studenti.stream()
                .filter(x -> x.getMedieAdmitere() > 5)
                .collect(Collectors.groupingBy(Student::getCodFacultate, Collectors.counting()));
        facultati.stream()
                .forEach(f -> System.out.println(f.cod_facultate + " " + f.denumire + " " + mapa.getOrDefault(f.cod_facultate, 0l)));

        System.out.println("\nCerinta 3\n");
        DataManager.scriereTXT("Date\\situatie.txt", studenti, facultati);
    }
}