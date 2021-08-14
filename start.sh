#! /bin/sh
c1=consumer
c2=producer 
c3=register-center
docker stop $c1 $c2 $c3
docker rm $c1 $c2 $c3
docker rmi $c1 $c2 $c3
docker-compose up -d




