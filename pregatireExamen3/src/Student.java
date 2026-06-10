import java.util.ArrayList;
import java.util.List;

public class Student {
    public int IdStudent;
    public String nume;
    public String prenume;

    public static List<Materie> materii = new ArrayList<>();

    @Override
    public String toString() {
        return "Student{" +
                "IdStudent=" + IdStudent +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }

    public static List<Materie> getMaterii() {
        return materii;
    }

    public static void setMaterii(List<Materie> materii) {
        Student.materii = materii;
    }

    public void addMaterie(Materie m){
        materii.add(m);
    }

    public Student(int idStudent, String nume, String prenume) {
        IdStudent = idStudent;
        this.nume = nume;
        this.prenume = prenume;
    }

    public int getIdStudent() {
        return IdStudent;
    }

    public void setIdStudent(int idStudent) {
        IdStudent = idStudent;
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
