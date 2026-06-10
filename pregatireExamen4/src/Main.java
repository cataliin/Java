import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
//        initDb("jdbc:sqlite:date\\medici.db");
//        insertDb("jdbc:sqlite:date\\medici.db");
        List<Consultatie> consultatii = citesteCSV("date\\consultatii.csv");
        for(var i : consultatii){
            System.out.println(i);
        }

        List<Medic> medici = citireBD("jdbc:sqlite:date\\medici.db");
        for(var i : medici){
            System.out.println(i);
        }
        medici.stream()
                .filter(x -> x.Tarif > 150)
                .sorted((m1,m2) -> Integer.compare(m2.getTarif(), m1.getTarif()))
                .forEach(x -> System.out.printf("%s, %s, %d\n", x.Nume, x.getSpecialitate(), x.getTarif()));
        Map<Integer, Long> consultatieMedic= consultatii.stream()
                .sorted((m1,m2) -> Integer.compare(m1.getIdMedic(), m2.getIdMedic()))
                .collect(Collectors.groupingBy(Consultatie::getIdMedic, Collectors.counting()));

        consultatieMedic.forEach((m,c) -> System.out.printf("Medic %d - %d consultatii\n", m,c));

        scriereJSON("date\\raport_medici.json", consultatieMedic, medici);
    }

    public static void scriereJSON(String path, Map<Integer, Long> consultatieMedic, List<Medic> medici) throws IOException {
        JSONArray array = new JSONArray();
        List<Medic> mediciSortati = medici.stream()
                .sorted((x1,x2) ->
                        Long.compare(x2.getTarif()*consultatieMedic.getOrDefault(x2.getIdMedic(), 0l),
                                x1.getTarif()*consultatieMedic.getOrDefault(x1.getIdMedic(), 0l)))
                .collect(Collectors.toList());
        for(var i: mediciSortati){
            JSONObject obj = new JSONObject();
            obj.put("id",i.getIdMedic());
            obj.put("nume", i.getNume());
            obj.put("numar consultatii", consultatieMedic.getOrDefault(i.getIdMedic(), 0l));
            obj.put("venit estimat", i.getTarif()*consultatieMedic.getOrDefault(i.getIdMedic(), 0l));
            array.put(obj);
        }
        try (FileWriter fw = new FileWriter(path)){
            fw.write(array.toString(4));
        }
    }

    public static void initDb(String path){
        String sql = "CREATE TABLE Medici (" +
                "IdMedic INTEGER PRIMARY KEY," +
                "NUME TEXT NOT NULL," +
                "SPECIALITATE TEXT NOT NULL," +
                "TARIF INTEGER NOT NULL)";

        try (Connection  conn = DriverManager.getConnection(path);
             PreparedStatement statement = conn.prepareStatement(sql)
        ){
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Medic> citireBD(String path){
        List<Medic> medici = new ArrayList<>();
        String sql = "SELECT * FROM Medici";
        try (var conn = DriverManager.getConnection(path);
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery()
        ) {
            while(result.next()){
                medici.add(new Medic(result.getInt("IdMedic"), result.getInt("TARIF"), result.getString("SPECIALITATE"), result.getString("NUME")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return medici;
    }

    public static List<Consultatie> citesteCSV(String path){
        List<Consultatie> consultatii = new ArrayList<>();
        try (var scanner = new Scanner(new File(path))) {
            scanner.nextLine();
            while(scanner.hasNext()){
                String linie = scanner.nextLine();
                String[] cuvinte = linie.split(",");
                consultatii.add(
                        new Consultatie(Integer.parseInt(cuvinte[0].trim()), Integer.parseInt(cuvinte[3].trim()), cuvinte[2].trim(),Integer.parseInt(cuvinte[1].trim())));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return consultatii;
    }


    public static void insertDb(String path) throws SQLException {
        String sql = "INSERT INTO Medici (" +
                "IdMedic," +
                "Nume," +
                "Specialitate," +
                "Tarif) " +
                "VALUES (?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(path);
            PreparedStatement statement = conn.prepareStatement(sql)
        ){
            statement.setInt(1, 1);
            statement.setString(2, "Andrei Popescu");
            statement.setString(3, "Cardiologie");
            statement.setInt(4, 200);
            statement.execute();

            statement.setInt(1, 2);
            statement.setString(2, "Maria Ionescu");
            statement.setString(3, "Dermatologie");
            statement.setInt(4, 150);
            statement.execute();

            statement.setInt(1, 3);
            statement.setString(2, "Ion Dumitrescu");
            statement.setString(3, "Neurologie");
            statement.setInt(4, 250);
            statement.execute();

        }
    }
}
