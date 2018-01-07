package regkalk2.kszajgapp.hu.regkalk;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Csiga on 2018. 01. 06..
 */
@Entity(tableName = "RegAdoAlap")
public class RegAdoDB {
    @PrimaryKey
    public int id;

    public String uzemanyag;
    public String loketterfogat;
    public String kornyoszt;
    public int alap;
}
