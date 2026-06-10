public class Aventura {
    public int cod_aventura;
    public String denumire;
    public double tarif;
    public int locuri_disponibile;

    public Aventura(int cod_aventura,String denumire,double tarif, int locuri_disponibile) {
        this.cod_aventura = cod_aventura;
        this.locuri_disponibile = locuri_disponibile;
        this.tarif = tarif;
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return "Aventura{" +
                "cod_aventura=" + cod_aventura +
                ", denumire='" + denumire + '\'' +
                ", tarif=" + tarif +
                ", locuri_disponibile=" + locuri_disponibile +
                '}';
    }

    public int getCod_aventura() {
        return cod_aventura;
    }

    public void setCod_aventura(int cod_aventura) {
        this.cod_aventura = cod_aventura;
    }

    public int getLocuri_disponibile() {
        return locuri_disponibile;
    }

    public void setLocuri_disponibile(int locuri_disponibile) {
        this.locuri_disponibile = locuri_disponibile;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}
