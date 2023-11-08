export const isTokenExpired = (token: string) => {
  // 토큰 파싱
  const tokenParts = token.split('.');
  if (tokenParts.length !== 3) {
    // 유효한 JWT 토큰이 아닙니다.
    return true;
  }

  // 토큰의 본문 부분 디코딩
  const payload = JSON.parse(atob(tokenParts[1]));

  // "exp" 클레임 확인
  if (payload.exp) {
    // 현재 시간을 초로 변환
    const currentTime = Math.floor(Date.now() / 1000);

    // 만료 시간과 비교
    return currentTime > payload.exp;
  } else {
    // "exp" 클레임이 없으면 토큰은 만료되지 않았다고 가정
    return false;
  }
};
