package regkalk2.kszajgapp.hu.regkalk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final String LOKETTERFOGAT = "loketterfogat";
    private static final String JARMUTIPUS = "jarmutipus";
    private static final String KORNYOSZT = "kornyoszt";
    private static final String ALAPDIJ = "alapdij";

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
        super(context, dbName, null, 3);
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
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
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

        boolean dbExist = checkExist();

        if (dbExist) {
            // do nothing - database already exist
        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            try {

                copyDatabase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
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

    public String getRegAdoAlapdij(int loketterfogat, int jarmutipus, int kornyoszt)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String alapdij = "";
        Cursor c = db.query(REGADOALAPDIJ, new String[]{ALAPDIJ}, LOKETTERFOGAT+"=? AND " + JARMUTIPUS + "=? AND " + KORNYOSZT + "=?", new String[]{Integer.toString(loketterfogat), Integer.toString(jarmutipus), Integer.toString(kornyoszt)}, null, null, null);
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