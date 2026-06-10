public class Consultatie {
    public int idConsultatie;
    public int idMedic;
    public String numePacient;
    public int durataMin;

    @Override
    public String toString() {
        return "Consultatie{" +
                "idConsultatie=" + idConsultatie +
                ", idMedic=" + idMedic +
                ", numePacient='" + numePacient + '\'' +
                ", durataMin=" + durataMin +
                '}';
    }

    public Consultatie(int idConsultatie, int durataMin, String numePacient, int idMedic) {
        this.idConsultatie = idConsultatie;
        this.durataMin = durataMin;
        this.numePacient = numePacient;
        this.idMedic = idMedic;
    }

    public int getIdConsultatie() {
        return idConsultatie;
    }

    public void setIdConsultatie(int idConsultatie) {
        this.idConsultatie = idConsultatie;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public int getDurataMin() {
        return durataMin;
    }

    public void setDurataMin(int durataMin) {
        this.durataMin = durataMin;
    }
}
