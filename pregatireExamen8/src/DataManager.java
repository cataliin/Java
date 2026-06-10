import org.json.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataManager{
    public static List<Facultate> citireJSON(String path) throws FileNotFoundException {
        List<Facultate> facultati = new ArrayList<>();

        JSONTokener tokener = new JSONTokener(new FileReader(path));
        JSONArray array = new JSONArray(tokener);
        for(int i =0;i<array.length();++i){
            JSONObject obj = array.getJSONObject(i);
            facultati.add(
                    new Facultate(obj.getInt("cod_facultate"), obj.getString("denumire"),obj.getInt("numar_locuri"))
            );
        }
        return facultati;
    }

    public static List<Student> citireBD() throws SQLException {
        List<Student> studenti = new ArrayList<>();
        String URL = "jdbc:sqlite:D:\\facultate\\java\\pregatireExamen8\\Date\\universitate.db";
        String sql = "SELECT * FROM Studenti";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet result = statement.executeQuery()
        ){
            while(result.next()){
                studenti.add(
                        new Student(result.getInt("CNP"),result.getString("NumeStudent"), result.getDouble("MedieAdmitere"), result.getInt("CodFacultate"))
                );
            }
        }
        return studenti;
    }

    public static void scriereTXT(String path, List<Student> studenti, List<Facultate> facultati) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))){
            var mapa = studenti.stream()
                    .filter(x -> x.getMedieAdmitere() >= 5)
                    .collect(Collectors.groupingBy(Student::getCodFacultate, Collectors.averagingDouble(Student::getMedieAdmitere)));
            facultati.stream()
                    .forEach(f-> pw.println(f.cod_facultate + " " + f.denumire + " " + f.numar_locuri + " " + mapa.getOrDefault(f.cod_facultate, 0d)));
        }
    }
}
