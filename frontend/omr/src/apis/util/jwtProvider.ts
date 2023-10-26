import jwt_decode from "jwt-decode";

interface JwtToken {
    id: number,
    role: string,
    nickname: string,
    iat: number,
    exp: number
  }
export const isExpired = (token : string) => {

    const payload = jwt_decode<JwtToken>(token);

    return compareExpiration(payload.exp);
};

const compareExpiration = (exp : number) => {
    return new Date(exp) < new Date();
} 