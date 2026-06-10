public class Factura {
    public String Denumire;
    public String Repartizare;
    public double valoare;

    public String getDenumire() {
        return Denumire;
    }

    public void setDenumire(String denumire) {
        Denumire = denumire;
    }

    public double getValoare() {
        return valoare;
    }

    public void setValoare(double valoare) {
        this.valoare = valoare;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "Denumire='" + Denumire + '\'' +
                ", Repartizare='" + Repartizare + '\'' +
                ", valoare=" + valoare +
                '}';
    }

    public String getRepartizare() {
        return Repartizare;
    }

    public void setRepartizare(String repartizare) {
        Repartizare = repartizare;
    }

    public Factura(String denumire,String repartizare, double valoare) {
        Denumire = denumire;
        this.valoare = valoare;
        Repartizare = repartizare;
    }
}
