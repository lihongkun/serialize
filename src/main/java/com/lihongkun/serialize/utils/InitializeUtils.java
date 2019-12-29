package com.lihongkun.serialize.utils;

import com.lihongkun.serialize.constant.Constants;
import com.lihongkun.serialize.entity.DemoEntity;
import com.lihongkun.serialize.entity.DemoResponse;

import java.util.ArrayList;
import java.util.List;

public class InitializeUtils {

    public static DemoResponse initDemoResponse() {
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
