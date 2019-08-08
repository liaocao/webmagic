package com.cookie.webmagic.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author sky
 * @date 2019/8/7 15:01
 * 去重问题还需要特别针对字段定制了，某些返回的数据即便内容一样，id却不一样
 * delRepeatIndexid未对null值处理，暂时不用
 */
public class JsonUtil {

    /**
     * 根据Key去重复
     */
    public static String delRepeatIndexid(String result) {

        JSONArray array = JSON.parseArray(result);
        JSONArray arrayTemp = new JSONArray();
        int num = 0;
        for(int i = 0;i < array.size();i++){
            if(num==0){
                arrayTemp.add(array.get(i));
            }else{
                int numJ = 0;
                for(int j = 0;j < arrayTemp.size(); j++){
                    JSONObject newJsonObjectI = (JSONObject)array.get(i);
                    JSONObject newJsonObjectJ = (JSONObject)arrayTemp.get(j);
                    String index_idI = newJsonObjectI.get("Key").toString();
                    String valueI = newJsonObjectI.get("Value").toString();
                    String index_idJ = newJsonObjectJ.get("Key").toString();
                    if(index_idI.equals(index_idJ)){
                        arrayTemp.remove(j);
                        JSONObject newObject = new JSONObject();
                        newObject.put("Key", index_idI);
                        newObject.put("Value", valueI);
                        arrayTemp.add(newObject);
                        break;
                    }
                    numJ++;
                }
                if(numJ-1 == arrayTemp.size()-1){
                    arrayTemp.add(array.get(i));
                }
            }
            num++;
        }
        return arrayTemp.toString();
    }

//    public static String delRepeatData(){
//        import net.sf.json.JSONArray;
//        List list = JSONArray.toList(jsonarray);
//        HashSet hs = new HashSet(list);
//        JSONArray newjsonarray= JSONArray.fromObject(hs);
//    }
}
