package regkalk2.kszajgapp.hu.regkalk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Csiga on 2018. 01. 11..
 */

public class AssetDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DATABASEHELPER";

    private static final String REGADOALAPDIJ = "RegAdoAlap";
    private static final String JARMUTIPUS = "jarmutipus";
    private static final String LOKETMIN = "loketMin";
    private static final String LOKETMAX = "loketMax";
    private static final String UZEMANYAG = "uzemanyag";
    private static final String KORNYOSZT = "kornyoszt";
    private static final String ALAPDIJ = "alapdij";

    private static final String CSOKKENTESMERTEKE = "CsokkentesMerteke";
    private static final String CSMID = "CSMId";
    private static final String EHMIN = "EHMin";
    private static final String EHMAX = "EHMax";
    private static final String CSOKKMERTEKE = "CsokkMerteke";

    private static final String VAGYONSZERZESIILLETEK = "VagyonszerzesiIlletek";
    private static final String TELJMIN = "TeljMin";
    private static final String TELJMAX = "TeljMax";
    private static final String EVMIN = "EvMin";
    private static final String EVMAX = "EvMax";
    private static final String TELJAR = "TeljAr";


    private static final int VERSION = 8;
    private String dbName;
    private String db_path;
    private Context context;

    /**
     * A helper class to import db files.
     *
     * @param base
     *            /app context
     * @param dbName
     *            The name of the db in asset folder .
     */
    public AssetDatabaseHelper(Context context, String dbName) {
        super(context, dbName, null, VERSION);
        this.dbName = dbName;
        this.context = context;
        db_path = "/data/data/" + context.getPackageName() + "/databases/";
    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    public boolean checkExist() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = db_path + dbName;
            checkDB = SQLiteDatabase.openDatabase(db_path, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            e.printStackTrace();
            // database does't exist yet.

        } catch (Exception ep) {
            ep.printStackTrace();
        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     * */
    public void importIfNotExist() throws IOException {
        int dbVersion = 0;
        String myPath = db_path + dbName;
        SQLiteDatabase checkDB = null;
        boolean dbExist = checkExist();


        if(dbExist) {
            dbVersion = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY).getVersion();
        }



        if (!dbExist || dbVersion<VERSION) {
            // do nothing - database already exist
            if(dbVersion<VERSION) {
                SQLiteDatabase.deleteDatabase(new File(myPath));
            }

            this.getReadableDatabase();
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }

        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.

        }

    }

    private void copyDatabase() throws IOException {
        InputStream is = context.getAssets().open(dbName);

        OutputStream os = new FileOutputStream(db_path + dbName);

        byte[] buffer = new byte[4096];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        os.flush();
        os.close();
        is.close();
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String getRegAdoAlapdij(int jarmutipus, int loketterfogat, int uzemanyag, int kornyoszt)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String alapdij = "";
        Cursor c = db.query(REGADOALAPDIJ, new String[]{ALAPDIJ}, LOKETMIN+"<=? AND " + LOKETMAX + ">=? AND " + UZEMANYAG + "=? AND " + JARMUTIPUS + "=? AND " + KORNYOSZT + "=?", new String[]{Integer.toString(loketterfogat), Integer.toString(loketterfogat),Integer.toString(uzemanyag), Integer.toString(jarmutipus), Integer.toString(kornyoszt)}, null, null, null);
        try {
            if (c.moveToFirst()) {
                do {
                    alapdij = (c.getString(c.getColumnIndex("alapdij")));
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Hiba az alapdíj betöltése közben.");
        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        return alapdij;
    }

    public List<CsokkentesMerteke> getCsokkentesMerteke(int elteltHonapok) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<CsokkentesMerteke> csokkentesMertekeList = new ArrayList<CsokkentesMerteke>();

        //Cursor c=db.rawQuery("SELECT * FROM CSOKKENTESMERTEKE WHERE EHMIN<=" + elteltHonapok + " AND EHMAX >=" + elteltHonapok, null);
        Cursor c = db.rawQuery("select * from CsokkentesMerteke where CSMId = (select CSMid from CsokkentesMerteke where ehmin<=" + elteltHonapok + " AND ehmax>=" + elteltHonapok + ") or CSMId = (select CSMId from CsokkentesMerteke where ehmin<=" + elteltHonapok + " AND ehmax>=" + elteltHonapok + ")-1", null);
        try {
            if (c.moveToFirst()) {
                do {
                    csokkentesMertekeList.add(new CsokkentesMerteke(c.getInt(c.getColumnIndex(CSMID)), c.getInt(c.getColumnIndex(EHMIN)), c.getInt(c.getColumnIndex(EHMAX)), c.getFloat(c.getColumnIndex(CSOKKMERTEKE))));
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Hiba a csökkentés mértékének betöltése közben.");
        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        return csokkentesMertekeList;
    }

    public int getVagyonszerzesiIlletekAlap(int elteltEvek, int teljesitmeny) {
        SQLiteDatabase db = this.getReadableDatabase();
        int vialap = 0;

        Cursor c = db.rawQuery("select "+TELJAR+" from "+VAGYONSZERZESIILLETEK+" where ("+EVMIN+"<="+elteltEvek+" AND "+EVMAX+">=" +elteltEvek+ ") AND ("+TELJMIN+"<=" +teljesitmeny+ " AND "+TELJMAX+">=" + teljesitmeny+ ")", null);
        try {
            if (c.moveToFirst()) {
                do {
                    vialap = c.getInt(c.getColumnIndex(TELJAR));
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Hiba a vagyonszerzési illeték betöltése közben.");
        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        return vialap;
    }


    public List<String> getall()
    {
        List<String> all = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("RegAdoAlap", new String[]{"*"}, null,null, null, null, null);
        try {
            if (c.moveToFirst()) {
                do {
                    all.add(c.getString(c.getColumnIndex("alapdij")));
                } while(c.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (c != null && !c.isClosed()) {
                c.close();
            }
        }
        return all;


    }


}