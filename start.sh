#! /bin/sh
while getopts ":e:v:" params
do
 case ${params} in
  e)
  if [[ "${OPTARG}" == "dev" ]]  || [[ "${OPTARG}" == "test" ]] || [[ "${OPTARG}" == "prod" ]];
  then
      echo "当前环境设置为==> " ${OPTARG}
      sed -i 's#SPRING_PROFILES_ACTIVE=.*$#SPRING_PROFILES_ACTIVE='${OPTARG}'#g' environment.env
  else
      echo "输入环境不合法, -e [dev|test|prod]"
      exit 1
  fi
  ;;
  v)
  echo "当前版本设置为==> " ${OPTARG}
  sed -i 's#APP_VERSION=.*$#APP_VERSION='${OPTARG}'#g' environment.env
  ;;
  ?)
  echo "未知参数, params[-e|-v]"
  exit 1;;
 esac
done

c1=consumer
c2=producer 
c3=register-center
docker stop $c1 $c2 $c3
docker rm $c1 $c2 $c3
docker rmi $c1 $c2 $c3
docker-compose up -d




