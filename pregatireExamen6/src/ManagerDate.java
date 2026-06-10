import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDate {
    public static List<Factura> citesteFacturi(String path) throws FileNotFoundException {
        List<Factura> facturi = new ArrayList<>();
        String linie;
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            while((linie=br.readLine()) != null){
                String[] campuri = linie.split(",");
                if (campuri.length == 3){
                    facturi.add(new Factura(campuri[0].trim(), campuri[1].trim(), Double.parseDouble(campuri[2].trim())));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return facturi;
    }

//    public static List<Apartament> citesteApartamente(){
//        String path = "jdbc:sqlite:D:\\facultate\\java\\pregatireExamen6\\date\\intretinere_apartamente.db";
//        String sql = "SELECT * FROM APARTAMENTE";
//        List<Apartament> apartamente = new ArrayList<>();
//        try(Connection conn = DriverManager.getConnection(path);
//            PreparedStatement statement = conn.prepareStatement(sql);
//            ResultSet result = statement.executeQuery()
//        ) {
//
//            while(result.next()){
//                Apartament a = new Apartament(
//                        result.getInt("NumarApartament"),
//                        result.getInt("NumarPersoane"),
//                        result.getString("Nume"),
//                        result.getInt("Suprafata")
//
//                );
//                apartamente.add(a);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return apartamente;
//    }
}
