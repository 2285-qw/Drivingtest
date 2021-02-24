package bzu.gc.gcfinalwork.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bzu.gc.gcfinalwork.entity.Question;

public class QDBManger {
    private SQLiteDatabase db;
    private QDatebaseHelper helper;
    private int wrongnum;


    public QDBManger(Context context){
        helper= new QDatebaseHelper(context);
        db=helper.getReadableDatabase();
    }

    //判断错题是否存在
    public Boolean idfrist(int id){
        Cursor c = db.rawQuery("select * from question where id='" + id + "'",null);
        Log.d("ccc",c+"");
        if (c.moveToFirst()){
            c.close();
            return true;
        }else {
            c.close();
            return false;
        }


    }


    //增加错题
    public void add(Question question,String username) {
        db.execSQL("INSERT INTO " + QDatebaseHelper.TABLE_NAME
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?)", new Object[]{question.getId(),
                question.getQuestion(),question.getAnswer(),question.getItem1(),question.getItem2(),question.getItem3()
                ,question.getItem4(),question.getExplains(),question.getUrl(),username,"1"
        });
    }
    //获取错题
    public List<Question> finderror(String username){
        List<Question> lists=new ArrayList<Question>();
        Cursor c = db.rawQuery("select * from question where username=?", new String[]{"111"});
        Log.d("ccc",c+"");
        while(c.moveToNext()){
            int id=c.getInt(c.getColumnIndex("id"));
            String question=c.getString(c.getColumnIndex("question"));
            int answer=c.getInt(c.getColumnIndex("answer"));
            String item1=c.getString(c.getColumnIndex("item1"));
            String item2=c.getString(c.getColumnIndex("item2"));
            String item3=c.getString(c.getColumnIndex("item3"));
            String item4=c.getString(c.getColumnIndex("item4"));
            String explains=c.getString(c.getColumnIndex("explains"));
            String url=c.getString(c.getColumnIndex("url"));
            String collect=c.getString(c.getColumnIndex("collect"));
            Log.d("collect",collect);
           Question question1=new Question(id,question,answer,item1,item2,item3,item4,explains,url,username);
           lists.add(question1);
        }
        c.close();
        wrongnum=lists.size();
        return lists;
    }
    //删除错题
    public void deleterror(int id){
        db.delete(QDatebaseHelper.TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
    }
    //获取错题的总行数
    public int getWrongnum(String username){
         List<Question> lists=new ArrayList<Question>();
        Cursor c = db.rawQuery("select * from question where username=?", new String[]{username});
        while(c.moveToNext()){
            int id=c.getInt(c.getColumnIndex("id"));
            String question=c.getString(c.getColumnIndex("question"));
            int answer=c.getInt(c.getColumnIndex("answer"));
            String item1=c.getString(c.getColumnIndex("item1"));
            String item2=c.getString(c.getColumnIndex("item2"));
            String item3=c.getString(c.getColumnIndex("item3"));
            String item4=c.getString(c.getColumnIndex("item4"));
            String explains=c.getString(c.getColumnIndex("explains"));
            String url=c.getString(c.getColumnIndex("url"));
            Question question1=new Question(id,question,answer,item1,item2,item3,item4,explains,url,username);
            lists.add(question1);
        }
        c.close();
        wrongnum=lists.size();
        return  wrongnum;
    }

    //获取刷题总数
    public int getWrongnum(){
        List<Question> lists=new ArrayList<Question>();
        Cursor c = db.rawQuery("select * from question ", null);
        while(c.moveToNext()){
            int id=c.getInt(c.getColumnIndex("id"));
            String question=c.getString(c.getColumnIndex("question"));
            int answer=c.getInt(c.getColumnIndex("answer"));
            String item1=c.getString(c.getColumnIndex("item1"));
            String item2=c.getString(c.getColumnIndex("item2"));
            String item3=c.getString(c.getColumnIndex("item3"));
            String item4=c.getString(c.getColumnIndex("item4"));
            String explains=c.getString(c.getColumnIndex("explains"));
            String url=c.getString(c.getColumnIndex("url"));
            String username=c.getString(c.getColumnIndex("username"));
            Question question1=new Question(id,question,answer,item1,item2,item3,item4,explains,url,username);
            lists.add(question1);
        }
        c.close();
        wrongnum=lists.size();
        return  wrongnum;
    }

    //删除错题
    public void updateuser(String usernmae,String password) {
        ContentValues cv = new ContentValues();
        cv.put("password", password);
        db.update(DatabaseHelper.TABLE_NAME, cv, "username=?", new String[]{usernmae});
    }

    //添加我的收藏
    public void addCollect(int id) {
        ContentValues cv = new ContentValues();
        cv.put("collect", "0");
        db.update("question", cv, "id=?", new String[]{String.valueOf(id)});
    }

    //获取我的收藏
    public List<Question> getCollect(){
        List<Question> lists=new ArrayList<Question>();
        Cursor c = db.rawQuery("select * from question where collect=?", new String[]{"0"});
        Log.d("ccc",c+"");
        while(c.moveToNext()){
            int id=c.getInt(c.getColumnIndex("id"));
            String question=c.getString(c.getColumnIndex("question"));
            int answer=c.getInt(c.getColumnIndex("answer"));
            String item1=c.getString(c.getColumnIndex("item1"));
            String item2=c.getString(c.getColumnIndex("item2"));
            String item3=c.getString(c.getColumnIndex("item3"));
            String item4=c.getString(c.getColumnIndex("item4"));
            String explains=c.getString(c.getColumnIndex("explains"));
            String url=c.getString(c.getColumnIndex("url"));
            Question question1=new Question(id,question,answer,item1,item2,item3,item4,explains,url,"");
            lists.add(question1);
        }
        c.close();
        wrongnum=lists.size();
        return lists;
    }
}
