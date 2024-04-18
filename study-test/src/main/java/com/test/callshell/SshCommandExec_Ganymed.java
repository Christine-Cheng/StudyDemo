package com.test.callshell;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


/**
 * ganymed-ssh2
 *
 * @author Happy
 * @create 2021/9/16-23:57
 **/
public class SshCommandExec_Ganymed {
    public static void exec(String ip, String command, String username, String password) {
        //String username = "root";  //用户名
        //String password = "admin01519";  //密码
        try {
            Connection conn = new Connection(ip);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username,
                    password);
            if (false == isAuthenticated) {
                throw new IOException("Authentication failed.");
            }
            Session session = conn.openSession();
            session.execCommand(command);
            
            String charset = Charset.defaultCharset().toString();
            InputStream stdOut = new StreamGobbler(session.getStdout());
            String outStr = processStream(stdOut, charset);
            
            InputStream stdErr = new StreamGobbler(session.getStderr());
            String errStr = processStream(stdErr, charset);
            
            System.out.println("stdErr:" + errStr);
            System.out.println("stdOut:" + outStr);
            
            session.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String processStream(InputStream in, String charset)
            throws Exception {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }
}
