# nacos 2.0 支持grpc ，需要额外开放2个端口  9848 9848
<alibaba-cloud.version>2.2.7.RELEASE</alibaba-cloud.version>
docker run -d   -p 8848:8848 -p 9848:9848 -p 9849:9849  --env MODE=standalone --env NACOS_SERVER_IP=139.159.134.189 nacos/nacos-server