public class Specializare {
    public int cod;
    public String denumire;
    public int locuri;

    public Specializare(int cod, String denumire, int locuri) {
        this.cod = cod;
        this.denumire = denumire;
        this.locuri = locuri;
    }

    public int getCod() {
        return cod;
    }

    @Override
    public String toString() {
        return "Specializare{" +
                "cod=" + cod +
                ", denumire='" + denumire + '\'' +
                ", locuri=" + locuri +
                '}';
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getLocuri() {
        return locuri;
    }

    public void setLocuri(int locuri) {
        this.locuri = locuri;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}
