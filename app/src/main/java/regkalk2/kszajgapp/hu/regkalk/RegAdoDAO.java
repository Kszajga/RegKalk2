package regkalk2.kszajgapp.hu.regkalk;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Csiga on 2018. 01. 06..
 */

@Dao
public interface RegAdoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRegAdo();

    @Query("SELECT * FROM RegAdoAlap WHERE uzemanyag = :uzemanyag AND loketterfogat = :loketterfogat AND kornyoszt = :kornyoszt")
    public int getRegAdoAlap(String uzemanyag, String loketterfogat, String kornyoszt);
}
