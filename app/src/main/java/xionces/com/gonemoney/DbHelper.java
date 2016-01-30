package xionces.com.gonemoney;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TayfunCESUR on 30.01.16.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GoneMoneyDB";
    private static final String TABLE_NAME = "tblStatus";
    private static final int DATABASE_VERSION = 5;
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME
            + " ("
            + "_id"+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "Description" + " VARCHAR(255), "
            + "Datetime" + " VARCHAR(255), "
            + "isMinus" + " INTEGER, "
            + "Amount" + " VARCHAR(255)," + "Category" + " VARCHAR(255)" +
            ");";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
