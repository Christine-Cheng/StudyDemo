package com.test.callshell;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Happy
 * @create 2021/9/15-0:57
 **/
public class CallShell3 {
    public static void main(String[] args) {
        try {
            String shpath = "Shell/ShellEcho/TestEcho01.sh";
            Process ps = Runtime.getRuntime().exec(shpath);
            ps.waitFor();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
