package com.lihongkun.serialize;

import com.caucho.hessian.io.HessianOutput;
import com.lihongkun.serialize.constant.Constants;
import com.lihongkun.serialize.entity.DemoResponse;
import com.lihongkun.serialize.utils.InitializeUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @author lihongkun
 */
public class HessionTest {
    public static void main(String[] args) throws IOException {
        DemoResponse demoResponse = InitializeUtils.initDemoResponse();

        HessianOutput hessianOutput = new HessianOutput(new ByteArrayOutputStream());
        long timeWatch = System.currentTimeMillis();
        for (int i = 0; i < Constants.LOOP_SIZE; i++) {
            hessianOutput.writeObject(demoResponse);
        }
        System.out.println(System.currentTimeMillis() - timeWatch);

        hessianOutput = new HessianOutput(new GZIPOutputStream(new FileOutputStream("hession.txt")));
        for (int i = 0; i < Constants.LOOP_SIZE; i++) {
            hessianOutput.writeObject(demoResponse);
        }
        hessianOutput.flush();
        hessianOutput.close();

    }
}
