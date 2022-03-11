package com.zhjAdm.main;


import com.zhjAdm.util.NextSentenceTask;
import com.zhjAdm.util.NotificationUtil;
import com.zhjAdm.util.SimpleConfigUtil;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhjAdm
 */
public class ReadBook {
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private static RunnableScheduledFuture<?> scheduledFuture = null;
    public static String readLine(int row){
        String filePath = SimpleConfigUtil.getString("filePath");
        File file = null;
        try {
            file = new File(filePath);
        }catch (Exception e){
            NotificationUtil.error("Error reading file！");
        }
        BufferedInputStream fis;
        try {
            assert file != null;
            fis = new BufferedInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8),1024);

            String content = reader.lines().skip(row).limit(1).collect(Collectors.joining());
            if(StringUtils.isNotEmpty(content)){
                return content;
            }else{
                return "finish!";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file!";
        }
    }
    public static String nextSentence(){

        //获取行大小
        int lineSize = SimpleConfigUtil.getIntValue("lineSize",0);

        //获取行游标
        int nextCursor = SimpleConfigUtil.getIntValue("nextCursor",1);
        //获取文章游标
        int row = SimpleConfigUtil.getIntValue("row",0);
        //获取每句长度
        int length = SimpleConfigUtil.getIntValue("length",20);
        String contentsLine;
        if(nextCursor>lineSize){
            row++;
            SimpleConfigUtil.save("row",row);
            nextCursor = 1;
        }
        contentsLine = readLine(row);
        List<String> contents= getStrList(contentsLine,length);
        String resdContent =  contents.get(nextCursor-1);
        SimpleConfigUtil.save("nextCursor",nextCursor+1);
        return resdContent.trim();
    }
    public static String prevSentence() {
        //获取行大小
        int lineSize = SimpleConfigUtil.getIntValue("lineSize",0);
        //获取行游标
        int nextCursor = SimpleConfigUtil.getIntValue("nextCursor",1);
        //获取文章游标
        int row = SimpleConfigUtil.getIntValue("row",0);
        //获取每句长度
        int length = SimpleConfigUtil.getIntValue("length",20);
        String contentsLine;
        nextCursor = nextCursor-2;
        if(nextCursor <=0){
            row--;
            SimpleConfigUtil.save("row",row);
        }
        contentsLine = readLine(row);
        List<String> contents= getStrList(contentsLine,length);
        if(nextCursor <=0){
            nextCursor = SimpleConfigUtil.getIntValue("lineSize",0);
        }
        String resdContent =  contents.get(nextCursor-1);
        SimpleConfigUtil.save("nextCursor",nextCursor+1);
        return resdContent.trim();
    }

    public static void automatic(){
        int readTime = SimpleConfigUtil.getIntValue("readTime",2);
        if(scheduledFuture == null){
            scheduledFuture = (RunnableScheduledFuture<?>) executor.scheduleAtFixedRate(new NextSentenceTask(), readTime, readTime, TimeUnit.SECONDS);
        }else{
            executor.remove(scheduledFuture);
            scheduledFuture = null;
        }
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     * @param inputString  原始字符串
     * @param length   指定长度
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        //当前行分割后长度
        SimpleConfigUtil.save("lineSize",size);
        return getStrList(inputString, length, size);
    }
    /**
     * 把原始字符串分割成指定长度的字符串列表
     * @param inputString 原始字符串
     * @param length 指定长度
     * @param size  指定列表大小
     */
    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }
    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     * @param str  原始字符串
     * @param f 开始位置
     * @param t  结束位置
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length()) {
            return null;
        }
        if (t > str.length()) {
            return str.substring(f);
        } else {
            return str.substring(f, t);
        }
    }
}
