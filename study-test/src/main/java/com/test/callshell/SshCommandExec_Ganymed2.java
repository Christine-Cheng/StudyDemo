package com.test.callshell;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author Happy
 * @create 2021/9/17-0:07
 **/
public class SshCommandExec_Ganymed2 {
    private Connection conn;
    /**
     * 远程机器IP
     */
    private String ip;
    /**
     * 用户名
     */
    private String osUsername;
    /**
     * 密码
     */
    private String password;
    private String charset = Charset.defaultCharset().toString();
    
    private static final int TIME_OUT = 1000 * 5 * 60;
    
    /**
     * 构造函数
     *
     * @param ip
     * @param usr
     * @param pasword
     */
    public SshCommandExec_Ganymed2(String ip, String usr, String pasword) {
        this.ip = ip;
        this.osUsername = usr;
        this.password = pasword;
    }
    
    
    /**
     * 登录
     *
     * @return
     *
     * @throws IOException
     */
    private boolean login() throws IOException {
        conn = new Connection(ip);
        conn.connect();
        return conn.authenticateWithPassword(osUsername, password);
    }
    
    /**
     * 执行脚本
     *
     * @param cmds
     *
     * @return
     *
     * @throws Exception
     */
    public int exec(String cmds) throws Exception {
        InputStream stdOut = null;
        InputStream stdErr = null;
        String outStr = "";
        String outErr = "";
        int ret = -1;
        try {
            if (login()) {
                // Open a new {@link Session} on this connection
                Session session = conn.openSession();
                // Execute a command on the remote machine.
                session.execCommand(cmds);
                
                stdOut = new StreamGobbler(session.getStdout());
                outStr = processStream(stdOut, charset);
                
                stdErr = new StreamGobbler(session.getStderr());
                outErr = processStream(stdErr, charset);
                
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                
                System.out.println("outStr=" + outStr);
                System.out.println("outErr=" + outErr);
                
                ret = session.getExitStatus();
            } else {
                throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            IOUtils.closeQuietly(stdOut);
            IOUtils.closeQuietly(stdErr);
        }
        return ret;
    }
    
    /**
     * @param in
     * @param charset
     *
     * @return
     *
     * @throws IOException
     */
    private String processStream(InputStream in, String charset) throws Exception {
        byte[] buf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(buf) != -1) {
            sb.append(new String(buf, charset));
        }
        return sb.toString();
    }
}
