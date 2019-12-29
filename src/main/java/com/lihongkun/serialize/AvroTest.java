package com.lihongkun.serialize;

import com.lihongkun.serialize.constant.Constants;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import static com.lihongkun.serialize.constant.Constants.LOOP_SIZE;

/**
 * @author lihongkun
 */
public class AvroTest {

    public static void main(String[] args) throws IOException {
        AvroResponse avroResponse = initProtoResponse();


        DatumWriter<AvroResponse> recordDatumWriter = new SpecificDatumWriter<>(AvroResponse.class);
        DataFileWriter<AvroResponse> dataFileWriter = new DataFileWriter<>(recordDatumWriter);
        dataFileWriter.create(AvroResponse.getClassSchema(),new ByteArrayOutputStream());

        long timeWatch = System.currentTimeMillis();
        for (int i = 0; i < LOOP_SIZE; i++) {
            dataFileWriter.append(avroResponse);
        }
        System.out.println(System.currentTimeMillis() - timeWatch);

        dataFileWriter = new DataFileWriter<>(recordDatumWriter);
        dataFileWriter.create(AvroResponse.getClassSchema(),new GZIPOutputStream(new FileOutputStream("avro.txt")));
        for (int i = 0; i < LOOP_SIZE; i++) {
            dataFileWriter.append(avroResponse);
        }

        dataFileWriter.flush();
        dataFileWriter.close();

    }

    private static AvroResponse initProtoResponse() {
        List<AvroEntity> data = new ArrayList<>();

        for (long index = 0; index < Constants.DATA_SIZE; index++) {
            AvroEntity.Builder builder = AvroEntity.newBuilder();
            builder.setAppId(index);
            builder.setCategory(String.valueOf(index));
            builder.setCtr(index);
            builder.setId((int) index);
            builder.setName(String.valueOf(index));
            builder.setPrice(index);
            data.add(builder.build());
        }

        AvroResponse.Builder builder = AvroResponse.newBuilder();
        builder.setCode(0);
        builder.setData(data);

        return builder.build();
    }
}
