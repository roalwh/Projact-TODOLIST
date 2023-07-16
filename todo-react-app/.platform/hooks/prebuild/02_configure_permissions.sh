#!/bin/bash

# eb create 시 ssh 활성화 필요 합니다.
# ec2-user 가 기본 계정일것이고 이것은 보통 관리자 권한이 없습니다. 그래서 앞에 sudo 가 붙습니다.

# npm 시작시 ec2-user로 실행하는 듯함 -> ?? root 권한??-> 이 부분은 정확하지 않습니다. 
#기본적으로 react-scripts 가 실행이 되야되는 애 같은 데 기본이 왜 666 인지는 ;;

#저같은 경우 아래 명령어로 그룹 권한 을 넘겨준 뒤 그냥 실행시엔 안됬으나 스크립트 돌때 퍼미션에러가 나타나지 않았습니다.
sudo usermod -G webapp -a ec2-user
# eb create 시 /var/app/staing 만 존재
sudo /bin/chmod 776 /var/app/staging/node_modules/.bin/react-scripts
# eb deploy 시 staing이 current 로 변경? 됨(삭제후 생성인지 이름변경인지 모름)
sudo /bin/chmod 776 /var/app/current/node_modules/.bin/react-scripts
# xdg 오류시 아래와 같이 설치
# eb ssh로 접근 후
# sudo yum install xdg-utils -y
# sudo /var/app/current/npm/node_modules start 로 테스트
# 정상적으로 올라온다면 crtl+z 하시면 됩니다. or 종료 후 eb deploy
