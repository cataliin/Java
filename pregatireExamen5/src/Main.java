import org.json.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Aventura> aventuri = citireJSON("date\\aventuri.json");
//        for (var i:aventuri)
//            System.out.println(i);
        System.out.println("Exercitiul 1");
        aventuri.stream()
                .filter(x -> x.getLocuri_disponibile() >=20)
                .forEach(x-> System.out.printf("%s", x.getDenumire()));
        System.out.println("\nExercitiul 2");
        List<Rezervare> rezervari = citireTXT("date\\rezervari.txt");
        Map<Integer, Integer> locuriRezervate = rezervari.stream()
                .collect(Collectors.groupingBy(Rezervare::getCod_aventura, Collectors.summingInt(Rezervare::getNr_locuri_rezervate)));
        aventuri.stream()
                .filter(x -> x.getLocuri_disponibile() - locuriRezervate.getOrDefault(x.getCod_aventura(), 0) >= 5)
                .forEach(x -> System.out.printf("%s\n", x.getDenumire()));
        aventuri = citireJSON("date\\aventuri.json");
        scriereText("date\\venituri.txt", aventuri, locuriRezervate);
    }

    public static void scriereText(String path, List<Aventura> aventuri, Map<Integer, Integer> locuriRezervate) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))){
            aventuri.stream()
                    .sorted((a1,a2) -> a1.getDenumire().compareTo(a2.getDenumire()))
                    .forEach(a ->{
                        double valoare = a.getTarif()* locuriRezervate.getOrDefault(a.getCod_aventura(), 0);
                        pw.printf("%s, %d, %f\n", a.getDenumire(), locuriRezervate.getOrDefault(a.getCod_aventura(), 0), valoare);
                    });
        }
    }

    public static List<Aventura> citireJSON(String path) throws IOException {
        List<Aventura> aventuri = new ArrayList<>();
        try (FileReader reader = new FileReader(path)){
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray array = new JSONArray(tokener);

            for(int i = 0;i<array.length();++i){
                JSONObject obj = array.getJSONObject(i);
                aventuri.add(new Aventura(obj.getInt("cod_aventura"), obj.getString("denumire"), obj.getDouble("tarif"), obj.getInt("locuri_disponibile")));

            }
        }
        return aventuri;
    }

    public static List<Rezervare> citireTXT (String path) throws FileNotFoundException {
        List<Rezervare> rezervari = new ArrayList<>();
        try (var scanner = new Scanner(new File(path))){
            while(scanner.hasNext()){
                String linie = scanner.nextLine();
                String[] cuvinte = linie.split(",");
                rezervari.add(
                        new Rezervare(cuvinte[0].trim(), Integer.parseInt(cuvinte[1].trim()), Integer.parseInt(cuvinte[2].trim()))
                );
            }
        }
        return rezervari;
    }


}
