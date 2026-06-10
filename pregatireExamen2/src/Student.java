public class Student {
    public int idStudent;
    public String nume;
    public String prenume;

    public Student(int idStudent, String nume, String prenume) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.prenume = prenume;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
}
