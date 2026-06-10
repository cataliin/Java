import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Factura> facturi = CitirileToate.citireFacturi("date\\intretinere_facturi.txt");
//        facturi.stream().forEach(System.out::println);
//
//        List<Apartament> apartamente = CitirileToate.citireAparatamente();
//        apartamente.stream().forEach(System.out::println);

        CitirileToate.scriereJSON("date\\json.json", facturi);
        List<Factura> facturi1 = CitirileToate.citireJSON("date\\json.json");
        facturi1.stream().forEach(System.out::println);
//        System.out.println("\nCerinta 1\n");
//        long nr_Persoane = facturi.stream()
//                .filter(f-> f.getRepartizare().equalsIgnoreCase("persoane"))
//                .count();
//        long nr_Suprafata = facturi.stream()
//                .filter(f-> f.getRepartizare().equalsIgnoreCase("suprafata"))
//                .count();
//        System.out.println("Persoane " + nr_Persoane + " / Suprafata " + nr_Suprafata);
//
//        System.out.println("\nCerinta 2\n");
//        Map<String, Long> mapa = facturi.stream()
//                .collect(Collectors.groupingBy(Factura::getRepartizare, Collectors.counting()));
//        System.out.println(mapa.toString());
//
//        System.out.println("\nCerinta 3\n");
//        int val = apartamente.stream()
//                .collect(Collectors.summingInt(Apartament::getSuprafata));
//        System.out.println(val);

        var maxim = facturi1.stream()
                .max((x1,x2) -> Double.compare(x1.getValoare(),x2.getValoare()))
                .get()
                .valoare;

        Map<String, Double> grupare = facturi1.stream()
                        .collect(Collectors.groupingBy(Factura::getRepartizare, Collectors.summingDouble(Factura::getValoare)));
//        System.out.println(maxim);
        System.out.println(grupare.toString());
    }
}