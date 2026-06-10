public class Student {
    public long CNP;
    public String NumeStudent;
    public double MedieAdmitere;
    public int CodFacultate;

    public long getCNP() {
        return CNP;
    }

    @Override
    public String toString() {
        return "Studenti{" +
                "CNP=" + CNP +
                ", NumeStudent='" + NumeStudent + '\'' +
                ", MedieAdmitere=" + MedieAdmitere +
                ", CodFacultate=" + CodFacultate +
                '}';
    }

    public Student(long CNP, String numeStudent, double medieAdmitere, int codFacultate) {
        this.CNP = CNP;
        NumeStudent = numeStudent;
        MedieAdmitere = medieAdmitere;
        CodFacultate = codFacultate;
    }

    public void setCNP(long CNP) {
        this.CNP = CNP;
    }

    public int getCodFacultate() {
        return CodFacultate;
    }

    public void setCodFacultate(int codFacultate) {
        CodFacultate = codFacultate;
    }

    public String getNumeStudent() {
        return NumeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        NumeStudent = numeStudent;
    }

    public double getMedieAdmitere() {
        return MedieAdmitere;
    }

    public void setMedieAdmitere(double medieAdmitere) {
        MedieAdmitere = medieAdmitere;
    }
}
