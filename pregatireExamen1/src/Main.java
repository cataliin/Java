import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Produs> produse = citesteProduse("produse.txt");
        System.out.println("Nr total de produse este : " + produse.size());
//        for(var p: produse){
//            System.out.println(p.toString());
//        }
        produse.stream()
                .sorted(Comparator.comparingDouble(Produs::getPret).reversed())
                .forEach(p-> System.out.println(p.toString()));

        var tranzactii = citesteTranzactii("tranzactii.json");
        Map<Integer, Integer> lista = tranzactii.stream()
                .collect(Collectors.groupingBy(Tranzactie::getId,Collectors.summingInt(Tranzactie::getCantitate)));

        List<Produs> listaOrdonata = produse.stream().sorted((p1, p2) -> {return Integer.compare(lista.get(p2.getID()), lista.get(p1.getID()));}).toList();
        scrieFisier(listaOrdonata, lista);

//        4) Să se afișeze la consolă valoarea totală a stocurilor.
        double sum = 0;
        for(var i : produse){
            sum+= (lista.getOrDefault(i.getID(), 0) * i.getPret());
        }
        System.out.println(sum);

    }
    //    3) Să se scrie în fișierul text date\subiect1\lista.txt un raport de forma:
//    Denumire Produs, Numar tranzactii
//
//    Produsele trebuie să fie ordonate în ordinea descrescătoare
//    a numărului de tranzacții.

    private static void scrieFisier(List<Produs> list, Map<Integer,Integer> map) throws Exception{
        var pw = new PrintWriter("date\\subiect1\\lista.txt");
        pw.println("Denumire produs | Numar tranzactii");
        for(var i:list){
            pw.println(i.denumire + " | " + map.get(i.getID()));
        }
        pw.close();
    }
    private static List<Produs> citesteProduse(String numeFis){
        List<Produs> produse = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(numeFis))){
            String linie;

            while((linie = br.readLine()) != null){
                if (linie.trim().isEmpty())
                    continue;
                String[] parti = linie.split(",");
                int cod = Integer.parseInt(parti[0].trim());
                String denumire = parti[1].trim();
                double pret = Double.parseDouble(parti[2].trim());
                Produs p = new Produs(cod, denumire,pret);
                produse.add(p);
            }
        } catch(IOException e){
            System.out.println("Eroare la citirea fisierului " + e.getMessage());
        }
        return produse;
    }

//    3) Să se scrie în fișierul text date\subiect1\lista.txt un raport de forma:
//    Denumire Produs, Numar tranzactii
//
//    Produsele trebuie să fie ordonate în ordinea descrescătoare
//    a numărului de tranzacții.
    private static List<Tranzactie> citesteTranzactii(String numeFis) throws Exception{
        List<Tranzactie> tranzactii = new ArrayList<>();
        try (var reader = new FileReader(numeFis)){
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray jsonArray = new JSONArray(tokener);
            for(int i = 0;i<jsonArray.length();++i){
                JSONObject obj = jsonArray.getJSONObject(i);

                int cod = obj.getInt("codProdus");
                int cant = obj.getInt("cantitate");
                String tip = obj.getString("tip");

                tranzactii.add(new Tranzactie(cod,cant,tip));
            }
        }

        return tranzactii;
    }

    private static void pornesteScenariuTCP(List<Produs> listaProduse) throws Exception {
        int PORT = 8080;
        Thread thread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT);
                 Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            ){
                System.out.println("[SERVER] Clientul s-a conectat");
                int coudCautat = Integer.parseInt(in.readLine().trim());
                System.out.println("[SERVER] Cod cautat " + coudCautat);

                double valoareStoc = 0;
                for(Produs p : listaProduse){
                    if (p.getID() == coudCautat){

                    }
                }
            }

            ){

            }
        })
    }
}