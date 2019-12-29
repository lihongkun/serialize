package com.lihongkun.serialize;

import com.lihongkun.serialize.constant.Constants;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * @author lihongkun
 */
public class ProtobufTest {

    public static void main(String[] args) throws IOException {
        ProtoDemo.ProtoResponse response = initProtoResponse();

        for (int i = 0; i < Constants.LOOP_SIZE; i++) {
            response.toByteString();
        }

        long timeWatch = System.currentTimeMillis();
        for (int i = 0; i < Constants.LOOP_SIZE; i++) {
            response.toByteString();
        }
        System.out.println(System.currentTimeMillis() - timeWatch);

        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream("proto.txt"));
        for (int i = 0; i < Constants.LOOP_SIZE; i++) {
            gzipOutputStream.write(response.toByteArray());
        }
        gzipOutputStream.flush();
        gzipOutputStream.close();
    }

    private static ProtoDemo.ProtoResponse initProtoResponse() {
        List<ProtoDemo.ProtoEntity> data = new ArrayList<>();

        for (long index = 0; index < Constants.DATA_SIZE; index++) {
            ProtoDemo.ProtoEntity.Builder builder = ProtoDemo.ProtoEntity.newBuilder();
            builder.setAppId(index);
            builder.setCategory(String.valueOf(index));
            builder.setCtr(index);
            builder.setId((int) index);
            builder.setName(String.valueOf(index));
            builder.setPrice(index);
            data.add(builder.build());
        }

        ProtoDemo.ProtoResponse.Builder builder = ProtoDemo.ProtoResponse.newBuilder();
        builder.setCode((int) Constants.DATA_SIZE);
        builder.addAllData(data);

        return builder.build();
    }
}
