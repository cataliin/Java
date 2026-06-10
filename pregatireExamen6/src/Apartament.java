public class Apartament {
    public int NumarApartament;
    public String Nume;
    public int Suprafata;
    public int NumarPersoane;

    public int getNumarApartament() {
        return NumarApartament;
    }

    public void setNumarApartament(int numarApartament) {
        NumarApartament = numarApartament;
    }

    public Apartament(int numarApartament,String nume,int suprafata,int numarPersoane) {
        NumarApartament = numarApartament;
        NumarPersoane = numarPersoane;
        Nume = nume;
        Suprafata = suprafata;
    }

    @Override
    public String toString() {
        return "Apartament{" +
                "NumarApartament=" + NumarApartament +
                ", Nume='" + Nume + '\'' +
                ", Suprafata=" + Suprafata +
                ", NumarPersoane=" + NumarPersoane +
                '}';
    }

    public int getNumarPersoane() {
        return NumarPersoane;
    }

    public void setNumarPersoane(int numarPersoane) {
        NumarPersoane = numarPersoane;
    }

    public int getSuprafata() {
        return Suprafata;
    }

    public void setSuprafata(int suprafata) {
        Suprafata = suprafata;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }
}
