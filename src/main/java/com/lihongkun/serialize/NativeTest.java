package com.lihongkun.serialize;

import com.lihongkun.serialize.constant.Constants;
import com.lihongkun.serialize.entity.DemoResponse;
import com.lihongkun.serialize.utils.InitializeUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author lihongkun
 */
public class NativeTest {

    public static void main(String[] args) throws IOException {
        DemoResponse demoResponse = InitializeUtils.initDemoResponse();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new ByteArrayOutputStream());
        long timeWatch = System.currentTimeMillis();
        for (int i = 0; i < Constants.LOOP_SIZE; i++) {
            objectOutputStream.writeObject(demoResponse);
        }
        objectOutputStream.flush();
        System.out.println(System.currentTimeMillis() - timeWatch);


        objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("native.txt")));
        objectOutputStream.writeObject(demoResponse);
        objectOutputStream.flush();
        objectOutputStream.close();

    }

}
