package regkalk2.kszajgapp.hu.regkalk;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Csiga on 2018. 01. 06..
 */

/*
Üzemanyag típusok
1 - Benzin
2 - Gázolaj
3 - Hibrid
4 - Elektromos
 */

@Database(entities = {RegAdoDB.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract RegAdoDB regAdoDB();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "regkalk")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
