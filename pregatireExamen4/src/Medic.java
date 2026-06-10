public class Medic {
    public int IdMedic;
    public String Nume;
    public String Specialitate;
    public int Tarif;

    public Medic(int idMedic, int tarif, String specialitate, String nume) {
        IdMedic = idMedic;
        Tarif = tarif;
        Specialitate = specialitate;
        Nume = nume;
    }

    @Override
    public String toString() {
        return "Medic{" +
                "IdMedic=" + IdMedic +
                ", Nume='" + Nume + '\'' +
                ", Specialitate='" + Specialitate + '\'' +
                ", Tarif=" + Tarif +
                '}';
    }

    public int getIdMedic() {
        return IdMedic;
    }

    public void setIdMedic(int idMedic) {
        IdMedic = idMedic;
    }

    public int getTarif() {
        return Tarif;
    }

    public void setTarif(int tarif) {
        Tarif = tarif;
    }

    public String getSpecialitate() {
        return Specialitate;
    }

    public void setSpecialitate(String specialitate) {
        Specialitate = specialitate;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }
}
