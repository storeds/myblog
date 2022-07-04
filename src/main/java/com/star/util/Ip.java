package com.star.util;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


public class Ip {
    private static String path;

    static {

            path = "/D:/JavaWorkSpace/myblog-mybatis/myblog/ss";

    }

    private static String filenameTemp;

    public static void main(String[] args) throws IOException {

        //get all local ips
        Enumeration<NetworkInterface> interfs = NetworkInterface.getNetworkInterfaces();

        // 创建
        //Ip.creatTxtFile(path);

        FileWriter fileWriter=new FileWriter("/opt/my/Result.txt");

        while (interfs.hasMoreElements()) {
            NetworkInterface interf = interfs.nextElement();
            Enumeration<InetAddress> addres = interf.getInetAddresses();
            while (addres.hasMoreElements()) {
                InetAddress in = addres.nextElement();
                if (in instanceof Inet4Address) {
                    String ipv4 = "v4:" + in.getHostAddress() + '\n';
                    System.out.println("v4:" + in.getHostAddress());
                    //.writeTxtFile(ipv4);
                    fileWriter.write(in.getHostAddress().replace("%ens33",""));
                } else if (in instanceof Inet6Address) {
                    String ipv6 = "v6:" + in.getHostAddress() + '\n';
                    System.out.println("v6:" + in.getHostAddress());
                    //Ip.writeTxtFile(ipv6);
                    fileWriter.write(ipv6);
                }

            }


        }
        fileWriter.flush();
        fileWriter.close();
    }


    public static boolean writeTxtFile(String newStr) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String filein = newStr + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }

    public static boolean creatTxtFile(String name) throws IOException {
        boolean flag = false;
        filenameTemp = path + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            filename.createNewFile();
            flag = true;
        }
        return flag;
    }

}

