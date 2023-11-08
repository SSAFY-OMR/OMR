import { axiosInstance } from './utils';

export const createAnswer = async ({
  questionId,
  content,
}: {
  questionId: string;
  content: string;
}) => {
  try {
    const res = await axiosInstance.post(
      `/answers/create`,
      {
        questionId: questionId,
        content: content,
      },
      {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    );

    return res;
  } catch (e) {
    console.error(e);
  }
};

export const deleteAnswer = async (answerId: number) => {
  try {
    const res = await axiosInstance.delete(`/answers/delete/${answerId}`);

    return res;
  } catch (e) {
    console.error(e);
  }
};
