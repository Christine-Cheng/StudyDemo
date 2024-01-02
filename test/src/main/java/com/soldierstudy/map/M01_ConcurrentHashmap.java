package com.soldierstudy.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Happy
 * @create 2021/8/19-16:26
 **/
public class M01_ConcurrentHashmap {
    public static void main(String[] args) {
    
        ConcurrentHashMap<Integer, String> con= new ConcurrentHashMap<Integer, String>();
        con.put(001, "ZhangSan");
        con.put(002, "LiSi");
        con.get(002).hashCode();
    }
    
    
}
