public class Candidat {
    public long cnp_candidat;
    public String nume_candidat;
    public double nota_bacalaureat;
    public int cod_specializare_aleasa;

    public Candidat(long cnp_candidat, String nume_candidat, double nota_bacalaureat, int cod_specializare_aleasa) {
        this.cnp_candidat = cnp_candidat;
        this.nume_candidat = nume_candidat;
        this.nota_bacalaureat = nota_bacalaureat;
        this.cod_specializare_aleasa = cod_specializare_aleasa;
    }

    public long getCnp_candidat() {
        return cnp_candidat;
    }

    @Override
    public String toString() {
        return "Candidat{" +
                "cnp_candidat=" + cnp_candidat +
                ", nume_candidat='" + nume_candidat + '\'' +
                ", nota_bacalaureat=" + nota_bacalaureat +
                ", cod_specializare_aleasa=" + cod_specializare_aleasa +
                '}';
    }

    public void setCnp_candidat(long cnp_candidat) {
        this.cnp_candidat = cnp_candidat;
    }

    public String getNume_candidat() {
        return nume_candidat;
    }

    public void setNume_candidat(String nume_candidat) {
        this.nume_candidat = nume_candidat;
    }

    public double getNota_bacalaureat() {
        return nota_bacalaureat;
    }

    public void setNota_bacalaureat(double nota_bacalaureat) {
        this.nota_bacalaureat = nota_bacalaureat;
    }

    public int getCod_specializare_aleasa() {
        return cod_specializare_aleasa;
    }

    public void setCod_specializare_aleasa(int cod_specializare_aleasa) {
        this.cod_specializare_aleasa = cod_specializare_aleasa;
    }
}
