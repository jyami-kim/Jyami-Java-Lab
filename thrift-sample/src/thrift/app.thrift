namespace java com.jyami.java.thrift.service
namespace py com.jyami.python.thrift.service

struct CommonResponse {
  1:required i32 status,
  2:optional binary bsonBody
}

struct RequestWithFlag {
  1:required binary bsonData,
  2:required bool flag
}

service PostService {

  void ping()

  CommonResponse read(1:RequestWithFlag request),

  CommonResponse save(1:binary bsonData),

  CommonResponse remove(1:binary bsonData)
}
