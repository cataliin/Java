import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ParserConfigurationException, SAXException {

        List<Specializare> specializari = ManagerDate.citireSpecializari();
//        specializari.stream().forEach(System.out::println);

        List<Candidat> candidati = ManagerDate.citireCandidati("date\\inscrieri.txt");
//        candidati.stream().forEach(System.out::println);
        System.out.println("\nCerinta 1\n");
        var sum =specializari.stream()
                .collect(Collectors.summarizingInt(Specializare::getLocuri))
                .getSum();
        System.out.println(sum);
        System.out.println("\nCerinta 2\n");
        Map<Integer, Long> mapa = candidati.stream()
                .collect(Collectors.groupingBy(Candidat::getCod_specializare_aleasa, Collectors.counting()));
        for(var i : specializari){
            if (i.getLocuri()-mapa.getOrDefault(i.getCod(), 0l) >= 100){
                System.out.printf("%-2d | %-18s | %-5d\n", i.getCod(), i.getDenumire(), i.getLocuri()-mapa.getOrDefault(i.getCod(), 0l));
            }
        }

        System.out.println("\nCerinta 3\n");
        ManagerDate.scriereJSON("date\\inscriere_specializari.json", specializari, candidati, mapa);

        System.out.println("\nCITIRE XML\n");
        List<Specializare> specializariXML = ManagerDate.citireXML1("date\\specializari.xml");
        specializariXML.stream()
                .forEach(System.out::println);
        ManagerDate.scriereTXT("date\\inscrieriScriere.txt", candidati);
    }

}