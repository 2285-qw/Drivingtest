package bzu.gc.gcfinalworkhuihaoda.tools;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import bzu.gc.gcfinalworkhuihaoda.entity.ShopInfo;

public class JsonParse {
    public static List<ShopInfo> getNewsInfo(String json) {
        List<ShopInfo> lists = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                String jsonObject = jsonArray.getString(1);
//                String img = jsonObject.getString("img1");
//                String title = jsonObject.getString("title");
//                String src = jsonObject.getString("src");
                Gson gson = new Gson();
                ShopInfo shopInfo = gson.fromJson(jsonObject,ShopInfo.class);
                lists.add(shopInfo);
            }
            return lists;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
