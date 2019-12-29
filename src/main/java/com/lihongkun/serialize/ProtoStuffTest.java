package com.lihongkun.serialize;

import com.lihongkun.serialize.constant.Constants;
import com.lihongkun.serialize.entity.DemoResponse;
import com.lihongkun.serialize.utils.InitializeUtils;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.ProtostuffOutput;
import io.protostuff.runtime.RuntimeSchema;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import static com.lihongkun.serialize.constant.Constants.LOOP_SIZE;

/**
 * @author lihongkun
 */
public class ProtoStuffTest {
    public static void main(String[] args) throws IOException {
        DemoResponse demoResponse = InitializeUtils.initDemoResponse();

        RuntimeSchema runtimeSchema = RuntimeSchema.createFrom(DemoResponse.class);
        LinkedBuffer  linkedBuffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        long timeWatch = System.currentTimeMillis();
        for (int i = 0; i < Constants.LOOP_SIZE; i++) {
            linkedBuffer.clear();
            ProtostuffIOUtil.toByteArray(demoResponse,runtimeSchema,linkedBuffer);
        }
        System.out.println(System.currentTimeMillis() - timeWatch);

        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream("stuff.txt"));
        for (int i = 0; i < LOOP_SIZE; i++) {
            linkedBuffer.clear();
            gzipOutputStream.write(ProtostuffIOUtil.toByteArray(demoResponse,runtimeSchema,linkedBuffer));
        }

        gzipOutputStream.flush();
        gzipOutputStream.close();

    }
}
