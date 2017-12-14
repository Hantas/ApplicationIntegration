package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by 10742 on 2017/11/24.
 */
public class JSONUtils {
    //读取中间件txt
    public static String getJSON(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //将txt内容作为json解析,根据key获取value
    public static String getMatchName(String json, String attribute, String dbName) {
        JSONObject object = new JSONObject(json);
        JSONArray jsonArray = object.getJSONArray(attribute);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        return jsonObject.getString(dbName);
    }
}
