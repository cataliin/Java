import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.annotation.processing.Filer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, SQLException{
         List<Materie> materii = citireTXT("produse.txt");
         List<Student> studenti = citireJSON("studenti.json");

        Map<Integer, Student> mapStudenti = studenti.stream()
                .collect(Collectors.toMap(Student::getIdStudent, s ->s ));

//        for (var mat : materii){
//            int idStud = mat.getID();
//
//            Student stud = mapStudenti.get(idStud);
//
//            if (stud!= null){
//                stud.addMaterie(mat);
//            }
//        }
//
//        Student prim = studenti.get(0);
//        List<Materie> matPrim = prim.getMaterii();
//        for (var i : matPrim){
//            System.out.println(i);
//        }

//        initBd("jdbc:sqlite:materie.bd");
        insertBd("jdbc:sqlite:materie.bd", materii);
    }


    public static void initBd(String path) throws SQLException {
        String sql =
                "CREATE TABLE Materii (" +
                "IdStud INTEGER," +
                "Denumire TEXT NOT NULL," +
                "Nota NUMBER NOT NULL)";

        try (Connection conn = DriverManager.getConnection(path);
             PreparedStatement statement = conn.prepareStatement(sql)
        ){
            statement.execute();

        }
    }

    public static void insertBd(String path, List<Materie> materii){
        String sql = "INSERT INTO Materii (" +
                "IdStud," +
                "Denumire," +
                "Nota)" +
                "VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(path);
            PreparedStatement statement = conn.prepareStatement(sql)
        ){
            for(var mat:materii){
                statement.setInt(1,mat.getID());
                statement.setString(2,mat.getNume());
                statement.setDouble(3,mat.getNota());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Materie> citireTXT(String numeFis) throws IOException, FileNotFoundException {
        List<Materie> lista = new ArrayList<>();
        String linie;
        try (var reader = new BufferedReader(new FileReader(numeFis))){
            while((linie = reader.readLine()) != null){
                String[] campuri = linie.split(",");
                if (campuri.length == 3){
                    int id = Integer.parseInt(campuri[0].trim());
                    String nume = campuri[1].trim();
                    double nota = Double.parseDouble(campuri[2].trim());
                    lista.add(new Materie(id,nume,nota));
                }
            }
        }
        return lista;
    }

    public static List<Student> citireJSON(String numeFis) throws FileNotFoundException, IOException{
        List<Student> studenti = new ArrayList<>();
        try (var reader = new FileReader(numeFis)){
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray array = new JSONArray(tokener);
            for(int i = 0;i<array.length();++i){
                JSONObject obj = array.getJSONObject(i);
                int id = obj.getInt("IdStudent");
                String nume = obj.getString("Nume");
                String prenume = obj.getString("Prenume");
                studenti.add(new Student(id,nume,prenume));
            }
        }
        return studenti;
    }
}