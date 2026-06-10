public class Produs {
    public int ID;
    public String denumire;
    public double pret;

    public Produs(int ID, String denumire, double pret) {
        this.ID = ID;
        this.denumire = denumire;
        this.pret = pret;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "ID=" + ID +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                '}';
    }
}
