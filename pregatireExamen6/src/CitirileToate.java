import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitirileToate {
    public static List<Factura> citireFacturi(String path) throws IOException {
        List<Factura> facturi = new ArrayList<>();
        String linie;
        try(var br = new BufferedReader(new FileReader(path))){
            String[] campuri;
            while((linie = br.readLine()) != null){
                campuri = linie.split(",");
                if (campuri.length == 3){
                    facturi.add(new Factura(
                            campuri[0].trim(),
                            campuri[1].trim(),
                            Double.parseDouble(campuri[2].trim())
                    ));
                }
            }
        }
        return facturi;
    }
    public static List<Apartament> citireAparatamente(){
        String path = "jdbc:sqlite:D:\\facultate\\java\\pregatireExamen6\\date\\intretinere_apartamente.db";
        String sql = "SELECT * FROM Apartamente";
        List<Apartament> apartamente = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(path);
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery()
        ){
            while(result.next()){
                apartamente.add(
                        new Apartament(result.getInt("NumarApartament"), result.getString("Nume"), result.getInt("Suprafata"), result.getInt("NumarPersoane")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return apartamente;
    }

//    public String Denumire;
//    public String Repartizare;
//    public double valoare;

    public static void scriereJSON(String path, List<Factura> facturi) throws FileNotFoundException {
        JSONArray array = new JSONArray();
        for(var i: facturi){
            JSONObject obj = new JSONObject();
            obj.put("Denumire", i.getDenumire());
            obj.put("Repartizare", i.getRepartizare());
            obj.put("Valoare", i.getValoare());
            array.put(obj);
        }
        try (PrintWriter pw = new PrintWriter(new File(path))){
            pw.println(array.toString(4));
        }
    }

    public static List<Factura> citireJSON(String path) throws FileNotFoundException {
        List<Factura> facturi = new ArrayList<>();
        JSONTokener tokener = new JSONTokener(new FileReader(path));
        JSONArray array = new JSONArray(tokener);
        for(int i = 0;i<array.length();++i){
            JSONObject obj = array.getJSONObject(i);
            facturi.add(
                    new Factura(obj.getString("Denumire"), obj.getString("Repartizare"), obj.getInt("Valoare"))
            );
        }
        return facturi;
    }
}
