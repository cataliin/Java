public class Tranzactie {
    public int id; //cod produs
    public int cantitate;
    public String tip;

    public Tranzactie(int id, int cantitate, String tip) {
        this.id = id;
        this.cantitate = cantitate;
        this.tip = tip;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
