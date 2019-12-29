package com.lihongkun.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.lihongkun.serialize.constant.Constants;
import com.lihongkun.serialize.entity.DemoEntity;
import com.lihongkun.serialize.entity.DemoResponse;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * @author lihongkun
 */
public class KryoTest {

    public static void main(String[] args) throws IOException {
        DemoResponse response = initDemoResponse();

        Kryo kryo = new Kryo();
        kryo.register(DemoResponse.class);
        kryo.register(DemoEntity.class);
        kryo.register(ArrayList.class);

        Output output = new Output(new ByteArrayOutputStream());
        long timeWatch = System.currentTimeMillis();
        for (int i = 0; i < Constants.LOOP_SIZE; i++) {
            kryo.writeObject(output, response);
        }
        System.out.println(System.currentTimeMillis() - timeWatch);

        output = new Output(new GZIPOutputStream(new FileOutputStream("kryo.txt")));
        for (int i = 0; i < Constants.LOOP_SIZE; i++) {
            kryo.writeObject(output, response);
        }
        output.flush();
        output.close();
    }


    private static DemoResponse initDemoResponse() {
        List<DemoEntity> data = new ArrayList<DemoEntity>();

        for (long index = 0; index < Constants.DATA_SIZE; index++) {
            DemoEntity entity = new DemoEntity();
            entity.setAppId(index);
            entity.setCategory(String.valueOf(index));
            entity.setCtr(index);
            entity.setId((int) index);
            entity.setName(String.valueOf(index));
            entity.setPrice(index);
            data.add(entity);
        }

        DemoResponse response = new DemoResponse();
        response.setCode((int) Constants.DATA_SIZE);
        response.setData(data);
        return response;
    }

}
