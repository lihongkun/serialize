package com.lihongkun.serialize;

import com.lihongkun.serialize.constant.Constants;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lihongkun.serialize.entity.DemoEntity;
import com.lihongkun.serialize.entity.DemoResponse;
import com.lihongkun.serialize.utils.InitializeUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * @author lihongkun
 */
public class JsonTest {

    public static void main(String[] args) throws IOException {

        DemoResponse response = InitializeUtils.initDemoResponse();

        long timeWatch = System.currentTimeMillis();
        for(int i=0;i<Constants.LOOP_SIZE;i++){
            JSON.toJSONString(response);
        }
        System.out.println("fastjson : "+(System.currentTimeMillis() - timeWatch));

        Gson gson = new Gson();

        timeWatch = System.currentTimeMillis();
        for(int i=0;i<Constants.LOOP_SIZE;i++){
            gson.toJson(response) ;
        }
        System.out.println("gson : "+(System.currentTimeMillis() - timeWatch));

        ObjectMapper objectMapper = new ObjectMapper();
        timeWatch = System.currentTimeMillis();
        for(int i=0;i<Constants.LOOP_SIZE;i++){
            objectMapper.writeValueAsString(response);
        }
        System.out.println("jackson : "+(System.currentTimeMillis() - timeWatch));

        timeWatch = System.currentTimeMillis();
        for(int i=0;i<Constants.LOOP_SIZE;i++){
            buildResponseJson(response) ;
        }
        System.out.println("raw : "+(System.currentTimeMillis() - timeWatch));

        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream("json.txt"));
        for(int i=0;i<Constants.LOOP_SIZE;i++){
            gzipOutputStream.write(objectMapper.writeValueAsString(response).getBytes());
        }
        gzipOutputStream.flush();
        gzipOutputStream.close();

    }

    private static String buildResponseJson(DemoResponse response){

        StringBuffer stringBuffer = new StringBuffer(2000);

        stringBuffer.append("{");
        stringBuffer.append("\"").append("code").append("\":").append(response.getCode()).append(",");
        stringBuffer.append("\"").append("data").append("\":").append("[").append(buildDataJson(response.getData())).append("]");
        stringBuffer.append("}");

        return stringBuffer.toString();
    }

    private static String buildDataJson(List<DemoEntity> data){
        StringBuffer stringBuffer = new StringBuffer(200);

        for(int i = 0; i < data.size() ; i++){
            stringBuffer.append("{");
            stringBuffer.append("\"id\":").append(data.get(i).getId()).append(",");
            stringBuffer.append("\"appId\":").append(data.get(i).getAppId()).append(",");
            stringBuffer.append("\"category\":").append(data.get(i).getCategory()).append(",");
            stringBuffer.append("\"ctr\":").append(data.get(i).getCtr()).append(",");
            stringBuffer.append("\"name\":\"").append(data.get(i).getName()).append("\",");
            stringBuffer.append("\"price\":").append(data.get(i).getPrice());
            stringBuffer.append("}").append(",");
        }

        stringBuffer.deleteCharAt(stringBuffer.length()-1);

        return stringBuffer.toString();
    }

}
