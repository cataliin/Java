public class Facultate {
    public int cod_facultate;
    public String denumire;
    public int numar_locuri;

    public int getCod_facultate() {
        return cod_facultate;
    }

    public Facultate(int cod_facultate,String denumire, int numar_locuri) {
        this.cod_facultate = cod_facultate;
        this.numar_locuri = numar_locuri;
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return "Facultate{" +
                "cod_facultate=" + cod_facultate +
                ", denumire='" + denumire + '\'' +
                ", numar_locuri=" + numar_locuri +
                '}';
    }

    public void setCod_facultate(int cod_facultate) {
        this.cod_facultate = cod_facultate;
    }

    public int getNumar_locuri() {
        return numar_locuri;
    }

    public void setNumar_locuri(int numar_locuri) {
        this.numar_locuri = numar_locuri;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}
