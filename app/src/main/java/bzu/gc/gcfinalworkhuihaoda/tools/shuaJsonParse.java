package bzu.gc.gcfinalworkhuihaoda.tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bzu.gc.gcfinalworkhuihaoda.entity.Question;

public class shuaJsonParse {
    public static List<Question> getquestioninfo(String json){
        List<Question> lists=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultArray = jsonObject.getJSONArray("result");
            for(int i=0;i<resultArray.length();i++){
                JSONObject sonObject=resultArray.getJSONObject(i);
                int id=sonObject.getInt("id");
                String question=sonObject.getString("question");
                int answer=sonObject.getInt("answer");
                String item1=sonObject.getString("item1");
                String item2=sonObject.getString("item2");
                String item3=sonObject.getString("item3");
                String item4=sonObject.getString("item4");
                String explains=sonObject.getString("explains");
                String url=sonObject.getString("url");
                Question question1=new Question(id,question,answer,item1,item2,item3,item4,explains,url,null,"1");
                lists.add(question1);

            }
            System.out.println(lists.size());
            return lists;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
