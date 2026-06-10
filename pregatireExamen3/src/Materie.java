public class Materie {
    public int ID;
    public String nume;
    public double nota;

    public Materie(int ID, String nume, double nota) {
        this.ID = ID;
        this.nume = nume;
        this.nota = nota;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Materie{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", nota=" + nota +
                '}';
    }
}
