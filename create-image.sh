#mvn clean package
docker build -t shaman58/sfsb:0.2.3 .
docker push https://5.35.85.162:32768/shaman58/sfsb:0.2.3