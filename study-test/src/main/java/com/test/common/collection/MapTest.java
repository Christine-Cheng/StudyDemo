package com.test.common.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: MyTest
 * @author: Zheng Heng Xun
 * @create: 2021-04-02 13:40
 */
public class MapTest {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", 1);
        map1.put("value", 2);
        list.add(map1);
        
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", 1);
        map2.put("value", 5);
        list.add(map2);
        
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("id", 2);
        map3.put("value", 5);
        list.add(map3);
        
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("id", 2);
        map4.put("value", 4);
        list.add(map4);
        
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("id", 1);
        map5.put("value", 4);
        list.add(map5);
        
        //id相等value相加（合并id相同数据）
        System.out.println("原始数据：" + list);
        
        //方法一
        Map<String, Object> result1 = new HashMap<String, Object>();
        for (Map<String, Object> map : list) {
            String id = map.get("id").toString();
            Long value = Long.parseLong(map.get("value").toString());
            if (result1.containsKey(id)) {
                Long temp = Long.parseLong(result1.get(id).toString());
                value += temp;
            }
            result1.put(id, value);
        }
        System.out.println("合并后的数据：" + result1);
        
        //方法二
        Map<String, Map<String, Object>> result2 = new HashMap<String, Map<String, Object>>();
        for (Map<String, Object> map : list) {
            String id = map.get("id").toString();
            Long value = Long.parseLong(map.get("value").toString());
            if (result2.containsKey(id)) {
                Long temp = Long.parseLong(result2.get(id).get("value").toString());
                value += temp;
                result2.get(id).put("value", value);
                continue;
            }
            result2.put(id, map);
        }
        System.out.println("合并后的数据2：" + result2);
        
    }
}
