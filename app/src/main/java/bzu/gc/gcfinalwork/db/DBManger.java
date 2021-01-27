package bzu.gc.gcfinalwork.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import bzu.gc.gcfinalwork.entity.user;

public class DBManger {
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public DBManger(Context context) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
    }

    //增加用户
    public void add(user User) {
        db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME
                + " VALUES (null,?,?,0,0,0)", new Object[]{
                User.username, User.password
        });
    }

    //修改密码
    public void updatepassword(String usernmae,String password) {
        ContentValues cv = new ContentValues();
        cv.put("password", password);
        db.update(DatabaseHelper.TABLE_NAME, cv, "username=?", new String[]{usernmae});
    }

    //获取账号密码信息
    public user selectuser(String username) {
        Cursor c = db.rawQuery("select * from user where username=?", new String[]{username});
        if (c.moveToFirst()) {
            String Username = (c.getString(c.getColumnIndex("username")));
            String Password = (c.getString(c.getColumnIndex("password")));
            Integer ALLnum = (c.getInt(c.getColumnIndex("allnum")));
            Integer Errornum = (c.getInt(c.getColumnIndex("errornum")));
            Integer Aimnum = (c.getInt(c.getColumnIndex("aimnum")));
            user User = new user(null, Username, Password, ALLnum,  Aimnum,Errornum);
            return User;
        }
        c.close();
        return new user(null, "0", "0", null, null, null);
    }
    //每做一题+1总题量
    public void upallnum(int allnum,String username){
        allnum++;
        ContentValues cv = new ContentValues();
        cv.put("allnum", allnum);
        db.update(DatabaseHelper.TABLE_NAME, cv, "username=?", new String[]{username});
    }
    //更新错题数量
    public void updatewrong(int wrongnum,String username){
        ContentValues cv = new ContentValues();
        cv.put("errornum", wrongnum);
        db.update(DatabaseHelper.TABLE_NAME, cv, "username=?", new String[]{username});
    }
    //获取allnum的数值
    public int getallnum(String username){
        int allnum=0;
        Cursor c = db.rawQuery("select allnum from user where username=?", new String[]{username});
        if (c.moveToFirst()) {
            allnum=(c.getInt(c.getColumnIndex("allnum")));
        }
        return allnum;
    }



    public void closeDB() {
        db.close();
    }
}
