package knn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author lmx
 * @date 2020-07-10 13:32
 */
public class JsonUtil {

    public static String parseJson(KnnSample[] array) {

        JSONArray jsonArray = new JSONArray();
        for (KnnSample sort : array) {
            JSONObject sortObj = new JSONObject();
            sortObj.put("typeId", sort.getTypeId());
            sortObj.put("score", sort.getScore());
            jsonArray.add(sortObj);
        }
        return jsonArray.toString();
    }

    public static String parseJson(String s) {
        return s;
    }

}
