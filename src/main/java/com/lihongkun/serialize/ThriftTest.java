package com.lihongkun.serialize;

import com.lihongkun.serialize.constant.Constants;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;


/**
 * @author lihongkun
 */
public class ThriftTest {

    public static void main(String[] args) throws IOException, TException {
        ThriftResponse response = initProtoResponse();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        TTransport transport = new TIOStreamTransport(out);
        TBinaryProtocol protocol = new TBinaryProtocol(transport);

        for(int i=0;i< Constants.LOOP_SIZE;i++){
            response.write(protocol);
        }

        long timeWatch = System.currentTimeMillis();
        for(int i=0;i<Constants.LOOP_SIZE;i++){
            response.write(protocol);
        }
        System.out.println(System.currentTimeMillis() - timeWatch);

        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream("thrift.txt"));
        TTransport gzTransport = new TIOStreamTransport(gzipOutputStream);
        TBinaryProtocol gzProtocol = new TBinaryProtocol(gzTransport);
        for(int i=0;i<Constants.LOOP_SIZE;i++){
            response.write(gzProtocol);
        }
        gzipOutputStream.flush();
        gzipOutputStream.close();
    }

    private static ThriftResponse initProtoResponse() {
        List<ThriftEntity> data = new ArrayList<>();

        for (long index = 0; index < Constants.DATA_SIZE; index++) {
            ThriftEntity thriftEntity = new ThriftEntity();
            thriftEntity.setAppId(index);
            thriftEntity.setCategory(String.valueOf(index));
            thriftEntity.setCtr(index);
            thriftEntity.setId((int) index);
            thriftEntity.setName(String.valueOf(index));
            thriftEntity.setPrice(index);
            data.add(thriftEntity);
        }

        ThriftResponse  response = new ThriftResponse();
        response.setCode((int) Constants.DATA_SIZE);
        response.setData(data);

        return response;
    }
}
