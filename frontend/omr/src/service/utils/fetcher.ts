import { axiosInstance } from '.';

export const fetcher = async (url: string, params: any) => {
  let reqUrl = `${process.env.NEXT_PUBLIC_API_URL}${url}`;

  if (params !== '') {
    reqUrl = `${reqUrl}${params}`;
  }

  let res = await axiosInstance.get(reqUrl);
  if (res.status === 200) return res.data.data;

  throw new Error(`HTTP error! Status: ${res.status}`);
};
