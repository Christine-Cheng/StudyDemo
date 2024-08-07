package com.test.callshell;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.InputStream;

/**
 * @author Happy
 * @create 2021/9/17-0:16
 **/
public class SshCommandExec_JSCH {
    private Session session;
    
    /**
     * 远程登录
     *
     * @param host     主机ip
     * @param port     端口号，默认22
     * @param user     主机登录用户名
     * @param password 主机登录密码
     *
     * @return
     *
     * @throws JSchException
     */
    public void login(String host, int port, String user, String password) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            // 设置第一次登陆的时候提示，可选值:(ask | yes | no)
            session.setConfig("StrictHostKeyChecking", "no");
            // 连接超时
            session.connect(1000 * 10);
            
        } catch (JSchException e) {
            System.out.println("登录时发生错误！");
            e.printStackTrace();
        }
    }
    
    /**
     * 执行命令
     *
     * @param command
     *
     * @return
     *
     * @throws Exception
     */
    public String executeShell(String command) throws Exception {
        
        byte[] tmp = new byte[1024];
        StringBuffer resultBuffer = new StringBuffer(); // 命令返回的结果
        
        Channel channel = session.openChannel("exec");
        ChannelExec exec = (ChannelExec) channel;
        // 返回结果流（命令执行错误的信息通过getErrStream获取）
        InputStream stdStream = exec.getInputStream();
        
        exec.setCommand(command);
        exec.connect();
        
        try {
            // 开始获得SSH命令的结果
            while (true) {
                while (stdStream.available() > 0) {
                    int i = stdStream.read(tmp, 0, 1024);
                    if (i < 0) break;
                    resultBuffer.append(new String(tmp, 0, i));
                }
                if (exec.isClosed()) {
//					System.out.println(resultBuffer.toString());
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } finally {
            //关闭连接
            channel.disconnect();
        }
        return resultBuffer.toString();
    }
    
    
    /**
     * 关闭连接
     */
    public void close() {
        if (session.isConnected())
            session.disconnect();
    }
    
    
}
