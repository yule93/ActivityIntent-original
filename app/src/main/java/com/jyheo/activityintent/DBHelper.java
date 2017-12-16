package com.jyheo.activityintent;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DBHelper extends SQLiteOpenHelper {
    final static String TAG="SQLiteDBTest";

    public DBHelper(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,getClass().getName()+".onCreate()");
        db.execSQL(UserContract.Users.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG,getClass().getName() +".onUpgrade()");
        db.execSQL(UserContract.Users.DELETE_TABLE);
        onCreate(db);
    }

    public void insertUserBySQL(String name, String place) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder()
                .setContentTitle("새로운 대피소가 추가되었습니다.")
                .setContentText("새로운 대피소가 추가되었습니다. 확인하려면 알림을 클릭하세요.")
                .setAutoCancel()
                .setWhen();
        try {
            String sql = String.format (
                    "INSERT INTO %s (%s, %s, %s) VALUES (NULL, '%s', '%s')",
                    UserContract.Users.TABLE_NAME,
                    UserContract.Users._ID,
                    UserContract.Users.KEY_NAME,
                    UserContract.Users.KEY_PLACE,
                    name,
                    place);
            getWritableDatabase().execSQL(sql);
            copyExcelDataToDatabase();
        } catch (SQLException e) {
            Log.e(TAG,"Error in inserting recodes");
        }
    }

    public Cursor getAllUsersBySQL() {
        String sql = "Select * FROM " + UserContract.Users.TABLE_NAME;
        return getReadableDatabase().rawQuery(sql,null);
    }

    public void deleteUserBySQL(String _id) {
        try {
            String sql = String.format (
                    "DELETE FROM %s WHERE %s = %s",
                    UserContract.Users.TABLE_NAME,
                    UserContract.Users._ID,
                    _id);
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in deleting recodes");
        }
    }

    public void updateUserBySQL(String _id, String name, String place) {
        try {
            String sql = String.format (
                    "UPDATE  %s SET %s = '%s', %s = '%s' WHERE %s = %s",
                    UserContract.Users.TABLE_NAME,
                    UserContract.Users.KEY_NAME, name,
                    UserContract.Users.KEY_PLACE, place,
                    UserContract.Users._ID, _id) ;
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in updating recodes");
        }
    }
    private void copyExcelDataToDatabase() {
        Log.w("ExcelToDatabase", "copyExcelDataToDatabase()");
        Workbook workbook = null;
        Sheet sheet = null;
        try{
            workbook = Workbook.getWorkbook(new File("서울시_대피소_방재시설_현황(좌표계_WGS1984).xls"));
            workbook.getSheets();

            if(workbook != null) {
                sheet = workbook.getSheet(0);

                if(sheet != null) {
                    Cell a1 = sheet.getCell(1,0);
                    String con1 = a1.getContents();
                    int nRowStartIndex = 1;
                    int nRowEndindex = sheet.getColumnPageBreaks().length -1;
                    int nColumnStartIndex = 0;
                    int nColumnEndIndex = sheet.getRowPageBreaks().length - 1;

                    String szValue = "";
                    for(int nRow = nRowStartIndex; nRow <= nRowEndindex; nRow++) {
                        for(int nColumn = nColumnStartIndex; nColumn <= nColumnEndIndex; nColumn++) {
                            szValue = sheet.getCell(nColumn, nRow).getContents();
                            System.out.print(szValue);
                            System.out.print("\t");
                        }
                        System.out.println();
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(workbook != null) {
                workbook.close();
            }
        }
    }
}