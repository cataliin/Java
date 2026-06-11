import org.json.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManagerDate {
    public static List<Specializare> citireSpecializari() throws SQLException {
        List<Specializare> specializari = new ArrayList<>();
        String url = "jdbc:sqlite:D:\\facultate\\java\\pregatireExamen9\\date\\facultate.db";
        String sql = "SELECT * FROM SPECIALIZARI";
        try(Connection conn =DriverManager.getConnection(url);
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery()
        ){
            while(result.next()){
                specializari.add(
                        new Specializare(result.getInt("cod"), result.getString("denumire"), result.getInt("locuri"))
                );
            }
        }
        return specializari;
    }
    public static List<Candidat> citireCandidati(String path) throws IOException {
        List<Candidat> candidati = new ArrayList<>();
        String linie;
        try(var br = new BufferedReader(new FileReader(path))){
            while((linie = br.readLine()) != null) {
                String[] campuri = linie.split(",");
                if (campuri.length == 4) {
                    candidati.add(
                            new Candidat(Long.parseLong(campuri[0].trim()), campuri[1].trim(), Double.parseDouble(campuri[2].trim()), Integer.parseInt(campuri[3].trim()))
                    );
                }
            }
        }
        return candidati;
    }
    public static void scriereJSON(String path, List<Specializare> specializari, List<Candidat> candidati, Map<Integer, Long> mapa) throws FileNotFoundException {
        JSONArray array = new JSONArray();
        var mapaMedii = candidati.stream()
                .collect(Collectors.groupingBy(Candidat::getCod_specializare_aleasa, Collectors.averagingDouble(Candidat::getNota_bacalaureat)));
        for(var s : specializari){
            JSONObject obj= new JSONObject();
            obj.put("cod_specializare", s.getCod());
            obj.put("denumire", s.getDenumire());
            obj.put("numar_inscrieri", mapa.getOrDefault(s.getCod(), 0l));
            obj.put("medie", mapaMedii.getOrDefault(s.getCod(), 0d));
            array.put(obj);
        }
        try(PrintWriter pw = new PrintWriter(new File(path))){
            pw.println(array.toString(4));
        }
    }
    public static List<Specializare> citireXML(String path) throws ParserConfigurationException, IOException, SAXException {
        List<Specializare> specializari = new ArrayList<>();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(path));
        doc.getDocumentElement().normalize();

        NodeList noduri = doc.getElementsByTagName("specializare");
        for(int i = 0;i<noduri.getLength();++i){
            Element e = (Element) noduri.item(i);
            int cod = Integer.parseInt(e.getElementsByTagName("cod").item(0).getTextContent());
            String denumire = e.getElementsByTagName("denumire").item(0).getTextContent();
            int locuri = Integer.parseInt(e.getElementsByTagName("locuri").item(0).getTextContent());
            specializari.add(new Specializare(cod,denumire,locuri));
        }
        return specializari;
    }

    public static List<Specializare> citireXML1(String path) throws IOException, SAXException, ParserConfigurationException {
        List<Specializare> specializari = new ArrayList<>();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new File(path));

        NodeList noduri = doc.getElementsByTagName("specializare");

        for(int i =0;i<noduri.getLength();++i){
            Element e = (Element)noduri.item(i);
            int cod = Integer.parseInt(e.getElementsByTagName("cod").item(0).getTextContent());
            String denumire = e.getElementsByTagName("denumire").item(0).getTextContent();
            int locuri = Integer.parseInt(e.getElementsByTagName("locuri").item(0).getTextContent());
            specializari.add(new Specializare(cod,denumire,locuri));
        }
        return specializari;
    }

    public static void scriereTXT(String path, List<Candidat> candidati) throws IOException {
        try(PrintWriter pw = new PrintWriter(new FileWriter(path))){
            for(var i:candidati){
                pw.println(i.getCnp_candidat() + "," + i.getNume_candidat());
            }
        }
    }


























}
