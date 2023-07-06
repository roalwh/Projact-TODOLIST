import { API_BASE_URL } from "../config/app-config";

export function call(api, method, request) {

  let headers = new Headers({
    "Content-Type": "application/json",
  });

  // 로컬 스토리지에서 ACCESS TOKEN 가져오기
  const accessToken = localStorage.getItem("ACCESS_TOKEN");
  if (accessToken && accessToken !== null) {
    headers.append("Authorization", "Bearer " + accessToken);
  }

  let options = {
    headers: headers,
    url: API_BASE_URL + api,
    method: method,
  };

  if (request) {
    // GET method
    options.body = JSON.stringify(request);
  }

  return fetch(options.url, options)
    .then((response) => {
      if (!response.ok) {
        // response.ok가 true이면 정상적인 리스폰스를 받은것, 아니면 에러 리스폰스를 받은것.
        return Promise.reject(response);
      }
      console.log(response);
      return response.json();
    })
    .catch((error) => {
      console.log(error);
      if (error.status === 403 || error.name === "TypeError") {
        window.location.href = "/login"; // redirect
      }
      return Promise.reject(error);
    });
}



// 로그인
export function signin(userDTO) {

  return call("/auth/signin", "POST", userDTO).then((response) => {
    if (response.token) {
      // 로컬 스토리지에 토큰 저장
      localStorage.setItem("ACCESS_TOKEN", response.token);
      alert(`로그인 성공`);
      // token이 존재하는 경우 Todo 화면으로 리디렉트
      window.location.href = "/";
    }
  }).catch((error) => {
    alert("로그인 실패");
    window.location.href = "/login"; // redirect
  });


}
// 로그아웃
export function signout() {
  localStorage.setItem("ACCESS_TOKEN", null);
  window.location.href = "/login";
}
// 회원가입
export function signup(userDTO) {
  return call("/auth/signup", "POST", userDTO);
}