import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;

import javax.swing.plaf.nimbus.State;
import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagerDate {
    public static List<Factura> getFacturiFromTXT(String path) throws FileNotFoundException {
        List<Factura> lista = new ArrayList<>();
        String linie;
        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            while((linie=reader.readLine())!=null)
            {
                String[] campuri = linie.split(",");
                if(campuri.length == 4)
                {
                    String den = campuri[0].trim();
                    String rep = campuri[1].trim();
                    double val = Double.parseDouble(campuri[2].trim());
                    String[] listInt = campuri[3].split(";");
                    List<Integer> lst = new ArrayList<>();
                    for(int i=0;i<listInt.length;i++)
                    {
                        lst.add(Integer.parseInt(listInt[i]));
                    }
                    lista.add(new Factura(den,rep,val,lst));
                }
            }
            return lista;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Factura> getFacturiFromBinar(String path) throws Exception
    {
        List<Factura> lista = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path)))
        {
            lista = (List<Factura>) ois.readObject();
        }
        return lista;
    }
    public static List<Factura> getFacturiFromJSON(String path) throws Exception
    {
        List<Factura> lista = new ArrayList<>();
        try(FileReader reader = new FileReader(path))
        {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray array = new JSONArray(tokener);
            for(int i=0;i< array.length();i++) {
                JSONObject obj = array.getJSONObject(i);
                String denumire = obj.getString("denumire");
                String rep = obj.getString("repartizare");
                double valoare = obj.getDouble("valoare");
                JSONArray arr = obj.getJSONArray("idUri");
                List<Integer> listaInt = new ArrayList<>();
                for (int j = 0; j < arr.length(); ++j) {
                    listaInt.add(arr.getInt(j));

                }
                lista.add(new Factura(denumire, rep, valoare, listaInt));
            }
        }
        return lista;
    }
    public static List<Factura> getFacturiFromXML(String path) throws Exception
    {
        List<Factura> lista = new ArrayList<>();
        try(FileReader xmlFile = new FileReader(path))
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(path));
            var nList = document.getElementsByTagName("factura");
            for(int i=0;i<nList.getLength();++i)
            {
                // autocomplete la Element ca vrem fix tipu din libraria asta ( org.w3c.dom )
                org.w3c.dom.Element element = (org.w3c.dom.Element) nList.item(i);
                String den = element.getElementsByTagName("denumire").item(0).getTextContent();
                String rep = element.getElementsByTagName("reprezentant").item(0).getTextContent();
                Double val = Double.parseDouble(element.getElementsByTagName("valoare").item(0).getTextContent());
                List<Integer> lst = new ArrayList<>();
                var idList = element.getElementsByTagName("id");
                for (int j = 0; j < idList.getLength(); j++) {
                    lst.add(Integer.parseInt(idList.item(j).getTextContent()));
                }

                lista.add(new Factura(den, rep, val, lst));
            }
        }
        return lista;
    }
    public static List<Factura> getFacturiFromDB() throws Exception {
        String URL = "jdbc:sqlite:C:\\Users\\panza\\IdeaProjects\\FULL\\data\\baza.db";
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT denumire, reprezentant, valoare FROM facturi";

        try (Connection connection = DriverManager.getConnection(URL);
             var comanda = connection.createStatement();
             var result = comanda.executeQuery(sql)) {

            while (result.next()) {
                String den = result.getString("denumire");
                String rep = result.getString("reprezentant");
                double val = result.getDouble("valoare");

                lista.add(new Factura(den, rep, val, new ArrayList<>()));
            }
        }

        return lista;
    }


}
