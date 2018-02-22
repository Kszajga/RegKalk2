package regkalk2.kszajgapp.hu.regkalk;

/**
 * Created by Csiga on 2018. 02. 21..
 */

public class Kalkulacio {
    private int loketterforgat;
    private int jarmutipus;
    private int kornyoszt;

    public Kalkulacio() {}

    public Kalkulacio(int loketterforgat, int jarmutipus, int kornyoszt) {
        this.loketterforgat = loketterforgat;
        this.jarmutipus = jarmutipus;
        this.kornyoszt = kornyoszt;
    }

    public int getLoketterforgat() {
        return loketterforgat;
    }

    public void setLoketterforgat(int loketterforgat) {
        this.loketterforgat = loketterforgat;
    }

    public int getJarmutipus() {
        return jarmutipus;
    }

    public void setJarmutipus(int jarmutipus) {
        this.jarmutipus = jarmutipus;
    }

    public int getKornyoszt() {
        return kornyoszt;
    }

    public void setKornyoszt(int kornyoszt) {
        this.kornyoszt = kornyoszt;
    }
}
