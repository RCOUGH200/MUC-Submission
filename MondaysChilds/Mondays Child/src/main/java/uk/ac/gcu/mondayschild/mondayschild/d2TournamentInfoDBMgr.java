package uk.ac.gcu.mondayschild.mondayschild;

import android.content.ContentValues;
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
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by Ryan
 */
public class d2TournamentInfoDBMgr extends SQLiteOpenHelper {

    private static final int DB_VER = 1;
    private static final String DB_PATH = "/data/data/uk.ac.gcu.mondayschild.mondayschild/databases/";
    private static final String DB_NAME = "tournaments.s3db";
    private static final String TBL_TOURNAMENTINFO = "tournamentinfo";

    public static final String COL_TOURNAMENTID = "tournID";
    public static final String COL_TOURNAMENTNAME = "Name";
    public static final String COL_TOURNAMENTIMG = "Logo";
    public static final String COL_TOURNAMENTDATE = "Month";
    public static final String COL_TOURNAMENTFUND = "PrizeFund";

    private final Context appContext;

    public d2TournamentInfoDBMgr(Context context, String name,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.appContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TOURNAMENTINFO_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_TOURNAMENTINFO + "("
                + COL_TOURNAMENTID + " INTEGER PRIMARY KEY," + COL_TOURNAMENTNAME
                + " TEXT," + COL_TOURNAMENTIMG + " TEXT," + COL_TOURNAMENTDATE + " TEXT,"
                + COL_TOURNAMENTFUND + " TEXT" + ")";
        db.execSQL(CREATE_TOURNAMENTINFO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + TBL_TOURNAMENTINFO);
            onCreate(db);
        }
    }

    // ================================================================================
    // Creates a empty database on the system and rewrites it with your own database.
    // ================================================================================
    public void dbCreate() throws IOException {

        boolean dbExist = dbCheck();

        if(!dbExist){
            //By calling this method an empty database will be created into the default system path
            //of your application so we can overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDBFromAssets();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    // ============================================================================================
    // Check if the database already exist to avoid re-copying the file each time you open the application.
    // @return true if it exists, false if it doesn't
    // ============================================================================================
    private boolean dbCheck(){

        SQLiteDatabase db = null;

        try{
            String dbPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);

        }catch(SQLiteException e){

            Log.e("SQLHelper","Database not Found!");

        }

        if(db != null){

            db.close();

        }

        return db != null ? true : false;
    }

    // ============================================================================================
    // Copies your database from your local assets-folder to the just created empty database in the
    // system folder, from where it can be accessed and handled.
    // This is done by transfering bytestream.
    // ============================================================================================
    private void copyDBFromAssets() throws IOException{

        InputStream dbInput = null;
        OutputStream dbOutput = null;
        String dbFileName = DB_PATH + DB_NAME;

        try {

            dbInput = appContext.getAssets().open(DB_NAME);
            dbOutput = new FileOutputStream(dbFileName);
            //transfer bytes from the dbInput to the dbOutput
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
            }

            //Close the streams
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        } catch (IOException e)
        {
            throw new Error("Problems copying DB!");
        }
    }


    public void addStarSignInfo(d2TournInfo aTournInfo) {

        ContentValues values = new ContentValues();
        values.put(COL_TOURNAMENTNAME, aTournInfo.getTournamentName());
        values.put(COL_TOURNAMENTIMG, aTournInfo.getTournamentLogo());
        values.put(COL_TOURNAMENTDATE, aTournInfo.getTournamentDate());
        values.put(COL_TOURNAMENTFUND, aTournInfo.getTournamentFund());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TBL_TOURNAMENTINFO, null, values);
        db.close();
    }

    public d2TournInfo findTournament(int aTournID) {
        String query = "Select * FROM " + TBL_TOURNAMENTINFO + " WHERE " + COL_TOURNAMENTID + " =  \"" + Integer.toString(aTournID) + "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        d2TournInfo tournInfo = new d2TournInfo();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            tournInfo.setTournamentID(Integer.parseInt(cursor.getString(0)));
            tournInfo.setTournamentName(cursor.getString(1));
            tournInfo.setTournamentLogo(cursor.getString(2));
            tournInfo.setTournamentDate(cursor.getString(3));
            tournInfo.setTournamentFund(cursor.getString(4));
            cursor.close();
        } else {
            tournInfo = null;
        }
        db.close();
        return tournInfo;
    }

    public d2TournInfo[] getAll() {
        d2TournInfo[] allTournaments = new d2TournInfo[18];
        for(int i = 1; i <= 18; i++) {
            String query = "Select * FROM " + TBL_TOURNAMENTINFO + " WHERE " + COL_TOURNAMENTID + " =  \"" + Integer.toString(i) + "\"";

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            d2TournInfo tournInfo = new d2TournInfo();

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                tournInfo.setTournamentID(Integer.parseInt(cursor.getString(0)));
                tournInfo.setTournamentName(cursor.getString(1));
                tournInfo.setTournamentLogo(cursor.getString(2));
                tournInfo.setTournamentDate(cursor.getString(3));
                tournInfo.setTournamentFund(cursor.getString(4));
                cursor.close();
            } else {
                tournInfo = null;
            }
            db.close();
            allTournaments[i-1] = tournInfo;
            Log.e("broken", tournInfo.getTournamentDate());
        }
        return allTournaments;
    }

    public boolean removeTournament(int aTournID) {

        boolean result = false;

        String query = "Select * FROM " + TBL_TOURNAMENTINFO + " WHERE " + COL_TOURNAMENTID + " =  \"" + aTournID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        d2TournInfo tournInfo = new d2TournInfo();

        if (cursor.moveToFirst()) {
            tournInfo.setTournamentID(Integer.parseInt(cursor.getString(0)));
            db.delete(TBL_TOURNAMENTINFO, COL_TOURNAMENTID + " = ?",
                    new String[] { String.valueOf(tournInfo.getTournamentID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}