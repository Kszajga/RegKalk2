package regkalk2.kszajgapp.hu.regkalk;

/**
 * Created by Csiga on 2018. 02. 20..
 */

public class CsokkentesMerteke {
    private int Id;
    private int EHMin;
    private int EHMax;
    private float CsokkMerteke;

    public CsokkentesMerteke() {}

    public CsokkentesMerteke(int Id, int EHMin, int EHMax, float csokkMerteke) {
        this.Id = Id;
        this.EHMin = EHMin;
        this.EHMax = EHMax;
        this.CsokkMerteke = csokkMerteke;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getEHMin() {
        return EHMin;
    }

    public void setEHMin(int EHMin) {
        this.EHMin = EHMin;
    }

    public int getEHMax() {
        return EHMax;
    }

    public void setEHMax(int EHMax) {
        this.EHMax = EHMax;
    }

    public double getCsokkMerteke() {
        return CsokkMerteke;
    }

    public void setCsokkMerteke(float csokkMerteke) {
        this.CsokkMerteke = csokkMerteke;
    }
}
