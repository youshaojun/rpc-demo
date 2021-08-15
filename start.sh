#! /bin/sh
while getopts ":e:v:" params
do
 case ${params} in
  e)
  if [[ "${OPTARG}" == "dev" ]]  || [[ "${OPTARG}" == "test" ]] || [[ "${OPTARG}" == "prod" ]];
  then
      read -r -p "你确定将环境激活为[ ${OPTARG} ]吗? [Y/N] " input
      case ${input} in
        [yY][eE][sS]|[yY])
		sed -i 's#SPRING_PROFILES_ACTIVE=.*$#SPRING_PROFILES_ACTIVE='${OPTARG}'#g' environment.env
		;;
        [nN][oO]|[nN])
		echo "退出......"
		exit 1
       	;;
        *)
		echo "请输入[Y/N]"
		exit 1
		;;
        esac
  else
      echo "请输入[-e dev|test|prod]"
      exit 1
  fi
  ;;
  v)
  echo "当前版本设置为==> " ${OPTARG}
  sed -i 's#APP_VERSION=.*$#APP_VERSION='${OPTARG}'#g' environment.env
  ;;
  ?)
  echo "请输入[-e|-v]"
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




