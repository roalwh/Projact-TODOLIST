#!/bin/bash

# 처음 빌드시 /var/app/staing 만 존재
sudo /bin/chmod 775 /var/app/staging/node_modules/.bin/react-scripts
# #eb deploy 시 staing이 current 로 변경? 됨(삭제후 생성인지 이름변경인지 모름)
# sudo /bin/chmod 776 /var/app/current/node_modules/.bin/react-scripts
# # npm 시작시 ec2-user로 실행하는 듯함 -> ?? root 권한??
# sudo usermod -G webapp -a ec2-user

