namespace java com.lihongkun.serialize

struct ThriftEntity{
    1:i64 id;
    2:string name;
    3:string category;
    4:i64 price;
    5:i64 appId;
    6:i64 ctr;
}

struct ThriftResponse{
    1:i32 code;
    2:list<ThriftEntity> data;
}