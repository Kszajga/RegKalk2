package regkalk2.kszajgapp.hu.regkalk;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import static java.sql.Types.NULL;

/**
 * Created by Csiga on 2018. 02. 21..
 */

public class Kalkulacio implements Parcelable{

    // Osztály tagjai
    private int loketterforgat;
    private String loketterfogatnev;
    private int jarmutipus;
    private String jarmutipusnev;
    private int kornyoszt;
    private String kornyosztnev;
    private int elteltHonapok;
    private int elteltEvek;
    private int uzemanyag;
    private String uzemanyagnev;
    private int teljesitmeny;
    private int regado;
    private int vagyonszerzesiIlletek;
    private String ervenyesMuszaki;
    private Context context;

    private int reward;

    public static final Creator<Kalkulacio> CREATOR = new Creator<Kalkulacio>() {

        @Override
        public Kalkulacio createFromParcel(Parcel source) {
            return new Kalkulacio(source);
        }

        @Override
        public Kalkulacio[] newArray(int size) {
            return new Kalkulacio[size];
        }
    };

    public Kalkulacio() {}

    public Kalkulacio(Context context) {
        this.context = context;
    }

    public Kalkulacio(int loketterforgat, int jarmutipus, int kornyoszt) {
        this.loketterforgat = loketterforgat;
        this.jarmutipus = jarmutipus;
        this.kornyoszt = kornyoszt;
    }

    public Kalkulacio (Parcel parcel) {
        this.loketterforgat = parcel.readInt();
        this.jarmutipus = parcel.readInt();
        this.kornyoszt = parcel.readInt();
        this.jarmutipusnev = parcel.readString();
        this.uzemanyag = parcel.readInt();
        this.uzemanyagnev = parcel.readString();
        this.regado = parcel.readInt();
        this.vagyonszerzesiIlletek = parcel.readInt();
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

    public int getElteltHonapok() {
        return elteltHonapok;
    }

    public void setElteltHonapok(int elteltHonapok) {
        this.elteltHonapok = elteltHonapok;
    }

    public int getElteltEvek() {
        return elteltEvek;
    }

    public void setElteltEvek(int elteltEvek) {
        this.elteltEvek = elteltEvek;
    }

    public String getLoketterfogatnev() {
        return loketterfogatnev;
    }

    public void setLoketterfogatnev(String loketterfogatnev) {
        this.loketterfogatnev = loketterfogatnev;
    }

    public String getJarmutipusnev() {
        return jarmutipusnev;
    }

    public void setJarmutipusnev(String jarmutipusnev) {
        this.jarmutipusnev = jarmutipusnev;
    }

    public String getKornyosztnev() {
        return kornyosztnev;
    }

    public void setKornyosztnev(String kornyosztnev) {
        this.kornyosztnev = kornyosztnev;
    }

    public int getUzemanyag() {
        return uzemanyag;
    }

    public void setUzemanyag(int uzemanyag) {
        this.uzemanyag = uzemanyag;
    }

    public String getUzemanyagnev() {
        return uzemanyagnev;
    }

    public void setUzemanyagnev(String uzemanyagnev) {
        this.uzemanyagnev = uzemanyagnev;
    }

    public int getRegado() {
        return regado;
    }

    public void setRegado(int regado) {
        this.regado = regado;
    }

    public int getVagyonszerzesiIlletek() {
        return vagyonszerzesiIlletek;
    }

    public void setVagyonszerzesiIlletek(int vagyonszerzesiIlletek) {
        this.vagyonszerzesiIlletek = vagyonszerzesiIlletek;
    }

    public int getTeljesitmeny() {
        return teljesitmeny;
    }

    public void setTeljesitmeny(int teljesitmeny) {
        this.teljesitmeny = teljesitmeny;
    }

    public String getErvenyesMuszaki() {
        return ervenyesMuszaki;
    }

    public void setErvenyesMuszaki(String ervenyesMuszaki) {
        this.ervenyesMuszaki = ervenyesMuszaki;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.loketterforgat);
        dest.writeInt(this.jarmutipus);
        dest.writeInt(this.kornyoszt);
        dest.writeString(this.jarmutipusnev);
        dest.writeInt(this.uzemanyag);
        dest.writeString(this.uzemanyagnev);
        dest.writeInt(this.regado);
        dest.writeInt(this.vagyonszerzesiIlletek);
    }

    //public int getRegAdoAlapdij(int loketterforgat, int jarmutipus, int kornyoszt) {
    public int getRegAdoAlapdij() {
        int regado = 0;
        AssetDatabaseHelper databaseHelper = new AssetDatabaseHelper(context, "regkalk.db");
        if (jarmutipus == 2) {
            regado = Integer.parseInt(databaseHelper.getRegAdoAlapdij(jarmutipus, loketterforgat, 0, 0));
        }
        else {
            regado = Integer.parseInt(databaseHelper.getRegAdoAlapdij(jarmutipus, loketterforgat, uzemanyag, kornyoszt));
        }
        return regado;
    }

    public int getRegAdo() {
        AssetDatabaseHelper databaseHelper = new AssetDatabaseHelper(context, "regkalk.db");
        List<CsokkentesMerteke> csokkentesMertekeList = databaseHelper.getCsokkentesMerteke(elteltHonapok);
        int F = 0;
        double K; //Az eltelt hónapok számától eggyel előbbre levő csökkentési érték
        double k; //Az eltelt hónapok számától függő csökkentési érték
        int T; //Az adott időszakra vonatkozó hónapok száma (EHMAX-EHMIN)
        int t; //Eltelt hónapok száma csökkentve K EHMAX értékével

        // helyett a Kalkulacio osztályban int alapdij = Integer.parseInt(dbHelper.getRegAdoAlapdij(kalkulacio.getLoketterforgat(), kalkulacio.getJarmutipus(), kalkulacio.getKornyoszt()));
        switch (csokkentesMertekeList.size()){
            case 0:
                //Hibát kell dobni, mert az adatbázisból nem kaptunk értéket
                break;
            case 1:
                // 0-2 hónapos autóról van szó
                break;
            case 2:
                K = csokkentesMertekeList.get(0).getCsokkMerteke();
                k = csokkentesMertekeList.get(1).getCsokkMerteke();
                T = csokkentesMertekeList.get(1).getEHMax()- csokkentesMertekeList.get(1).getEHMin();
                t = elteltHonapok-csokkentesMertekeList.get(0).getEHMax();
                double roundedT = Math.round(((k-K)*t/T*100.0))/100.0;
                F = (int) Math.round(getRegAdoAlapdij() * (1-K-roundedT));
                //btn_Szamol.setText(Integer.toString(F));
                setRegado(F);
                break;
        }
        return F;
    }

    public int getVagyonszerzesiIlletekDb(int elteltEvek, int teljesitmeny) {
        AssetDatabaseHelper databaseHelper = new AssetDatabaseHelper(context, "regkalk.db");
        int vi = databaseHelper.getVagyonszerzesiIlletekAlap(elteltEvek, teljesitmeny);
        return vi*teljesitmeny;
    }

    public int getEredetVizsgaDij(int jarmutipus, int loketterforgat) {
        switch (jarmutipus){
            case 1: //ha személyautó

                break;
            case 2: //ha motor

                break;
            case 3: //ha teherautó

                break;
        }
        return 0;
    }
}
