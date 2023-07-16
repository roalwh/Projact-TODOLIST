
// 설정파일에서 애플리케이션이 사용할 백엔드 URL를 동적으로 가져오도록 구현
// 도메인이 바뀌는 경우를 대비함

let backendHost;

const hostname = window && window.location && window.location.hostname;

if(hostname === "localhost"){
    backendHost = "http://localhost:8080";
} else{
    backendHost="http://RoalwhtodoListApi.ap-northeast-2.elasticbeanstalk.com";
}
export const API_BASE_URL = `${backendHost}`