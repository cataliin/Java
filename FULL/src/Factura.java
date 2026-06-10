import java.io.Serializable;
import java.util.List;

public class Factura implements Serializable {
    public String denumire;
    public String repartizare;
    public double valoare;
    public List<Integer> lista;

    public Factura(String denumire, String repartizare, double valoare, List<Integer> lista) {
        this.denumire = denumire;
        this.repartizare = repartizare;
        this.valoare = valoare;
        this.lista = lista;
    }

    @Override
    public String toString()
    {
        return this.denumire + " " + this.repartizare + " " + this.valoare + this.lista + "\n";
    }
}
