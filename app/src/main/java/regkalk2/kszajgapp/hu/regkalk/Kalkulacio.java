package regkalk2.kszajgapp.hu.regkalk;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Csiga on 2018. 02. 21..
 */

public class Kalkulacio implements Parcelable{
    private int loketterforgat;
    private int jarmutipus;
    private int kornyoszt;
    private int elteltHonapok;

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

    public Kalkulacio(int loketterforgat, int jarmutipus, int kornyoszt) {
        this.loketterforgat = loketterforgat;
        this.jarmutipus = jarmutipus;
        this.kornyoszt = kornyoszt;
    }

    public Kalkulacio (Parcel parcel) {
        this.loketterforgat = parcel.readInt();
        this.jarmutipus = parcel.readInt();
        this.kornyoszt = parcel.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.loketterforgat);
        dest.writeInt(this.jarmutipus);
        dest.writeInt(this.kornyoszt);
    }

    public String getSelectedValueFromSpinner()
    {
        return "0";
    }
}
