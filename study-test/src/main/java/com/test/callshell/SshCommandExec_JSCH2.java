package com.test.callshell;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * @author Happy
 * @create 2021/9/17-0:23
 **/
public class SshCommandExec_JSCH2 {
    private String ipAddress;
    
    private String username;
    
    private String password;
    
    public static final int DEFAULT_SSH_PORT = 22;
    
    private Vector<String> stdout;
    
    
    public static void main(final String[] args) {
        SshCommandExec_JSCH2 sshExecutor = new SshCommandExec_JSCH2("xx.xx.xx.xx", "username", "password");
        sshExecutor.execute("uname -s -r -v");
        
        Vector<String> stdout = sshExecutor.getStandardOutput();
        for (String str : stdout) {
            System.out.println(str);
        }
    }
    
    
    public SshCommandExec_JSCH2(final String ipAddress, final String username, final String password) {
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
        stdout = new Vector<String>();
    }
    
    public int execute(final String command) {
        int returnCode = 0;
        JSch jsch = new JSch();
        MyUserInfo userInfo = new MyUserInfo();
        
        try {
            // Create and connect session.
            Session session = jsch.getSession(username, ipAddress, DEFAULT_SSH_PORT);
            session.setPassword(password);
            session.setUserInfo(userInfo);
            session.connect();
            
            // Create and connect channel.
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            
            channel.setInputStream(null);
            BufferedReader input = new BufferedReader(new InputStreamReader(channel
                    .getInputStream()));
            
            channel.connect();
            System.out.println("The remote command is: " + command);
            
            // Get the output of remote command.
            String line;
            while ((line = input.readLine()) != null) {
                stdout.add(line);
            }
            input.close();
            
            // Get the return code only after the channel is closed.
            if (channel.isClosed()) {
                returnCode = channel.getExitStatus();
            }
            
            // Disconnect the channel and session.
            channel.disconnect();
            session.disconnect();
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnCode;
    }
    
    public Vector<String> getStandardOutput() {
        return stdout;
    }
    
    
}


class MyUserInfo implements UserInfo {
    private String password;
    
    private String passphrase;
    
    @Override
    public String getPassphrase() {
        System.out.println("MyUserInfo.getPassphrase()");
        return null;
    }
    
    @Override
    public String getPassword() {
        System.out.println("MyUserInfo.getPassword()");
        return null;
    }
    
    @Override
    public boolean promptPassphrase(final String arg0) {
        System.out.println("MyUserInfo.promptPassphrase()");
        System.out.println(arg0);
        return false;
    }
    
    @Override
    public boolean promptPassword(final String arg0) {
        System.out.println("MyUserInfo.promptPassword()");
        System.out.println(arg0);
        return false;
    }
    
    @Override
    public boolean promptYesNo(final String arg0) {
        System.out.println("MyUserInfo.promptYesNo()");
        System.out.println(arg0);
        if (arg0.contains("The authenticity of host")) {
            return true;
        }
        return false;
    }
    
    @Override
    public void showMessage(final String arg0) {
        System.out.println("MyUserInfo.showMessage()");
    }
}
