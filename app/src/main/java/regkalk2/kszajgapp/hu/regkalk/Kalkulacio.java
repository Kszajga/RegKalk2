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
    private String elso_forg;
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
    private int eredetVizsgaDij;
    private int ervenyesMuszaki;
    private int osszkerek;
    private int kisteher;
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
        this.elso_forg = parcel.readString();
        this.loketterforgat = parcel.readInt();
        this.jarmutipus = parcel.readInt();
        this.kornyoszt = parcel.readInt();
        this.jarmutipusnev = parcel.readString();
        this.uzemanyag = parcel.readInt();
        this.uzemanyagnev = parcel.readString();
        this.regado = parcel.readInt();
        this.vagyonszerzesiIlletek = parcel.readInt();
        this.eredetVizsgaDij = parcel.readInt();
        this.osszkerek = parcel.readInt();
        this.kisteher = parcel.readInt();
    }

    public String getElso_forg() {
        return elso_forg;
    }

    public void setElso_forg(String elso_forg) {
        this.elso_forg = elso_forg;
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

    public int getEredetVizsgaDij() {
        return eredetVizsgaDij;
    }

    public void setEredetVizsgaDij(int eredetVizsgaDij) {
        this.eredetVizsgaDij = eredetVizsgaDij;
    }

    public int getTeljesitmeny() {
        return teljesitmeny;
    }

    public void setTeljesitmeny(int teljesitmeny) {
        this.teljesitmeny = teljesitmeny;
    }

    public int getErvenyesMuszaki() {
        return ervenyesMuszaki;
    }

    public void setErvenyesMuszaki(int ervenyesMuszaki) {
        this.ervenyesMuszaki = ervenyesMuszaki;
    }

    public int getOsszkerek() {
        return osszkerek;
    }

    public void setOsszkerek(int osszkerek) {
        this.osszkerek = osszkerek;
    }

    public int getKisteher() {
        return kisteher;
    }

    public void setKisteher(int kisteher) {
        this.kisteher = kisteher;
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
        dest.writeString(this.elso_forg);
        dest.writeInt(this.loketterforgat);
        dest.writeInt(this.jarmutipus);
        dest.writeInt(this.kornyoszt);
        dest.writeString(this.jarmutipusnev);
        dest.writeInt(this.uzemanyag);
        dest.writeString(this.uzemanyagnev);
        dest.writeInt(this.regado);
        dest.writeInt(this.vagyonszerzesiIlletek);
        dest.writeInt(this.eredetVizsgaDij);
        dest.writeInt(this.osszkerek);
        dest.writeInt(this.kisteher);
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

        switch (uzemanyag) {
            // TODO: Üzemanyag típusától függő vizsgadíj számítása és hozzáadása
            case 1: // Benzin
                break;
            case 2: //Dízel
                break;
            case 3: // Hibrid
                break;
            case 4: // Elektromos / Plug-In hybrid
                break;
        }

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
                if (0<=loketterforgat && loketterforgat<=1400) {
                    return Constants.EREDET_B1;
                }
                else if (1401<=loketterforgat && loketterforgat<=2000) {
                    return Constants.EREDET_B2;
                }
                else if (2001<=loketterforgat) {
                    return Constants.EREDET_B3;
                }
                break;
            case 2: //ha motor
                if (0<=loketterforgat && 500<=loketterforgat) {
                    return Constants.EREDET_A1;
                }
                else if (501<=loketterforgat) {
                    return Constants.EREDET_A2;
                }
                break;
            case 3: //ha teherautó

                break;
        }
        return 0;
    }

    public int getMuszakiVizsgaDij () {
        int vizsgadij = 0;
        if (this.jarmutipus == 1) {
            if (this.ervenyesMuszaki == 1) {
                vizsgadij = vizsgadij + 8000;
            }
            else {
                vizsgadij = vizsgadij + 22800 + 16290;
            }
            if (this.osszkerek == 1) {
                vizsgadij = vizsgadij + 4100;
            }
            if (this.kisteher == 1) {
                vizsgadij = vizsgadij + 800;
            }
        }
        else if (this.jarmutipus == 2){
            // TODO: A motoros rész nem biztos, hogy így kell, hogy működjön, ellenőrizni!
            if (this.ervenyesMuszaki == 1) {
                vizsgadij = vizsgadij + 4350;
            }
            else {
                vizsgadij = vizsgadij + 4350 + 22800;
            }
        }
        return vizsgadij;
    }
}
