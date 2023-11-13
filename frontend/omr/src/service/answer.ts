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
      `/omr-api/answers/create`,
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
    const res = await axiosInstance.delete(
      `/omr-api/answers/delete/${answerId}`,
    );

    return res;
  } catch (e) {
    console.error(e);
  }
};

export const updateLikeAnswer = async (answerId: number) => {
  try {
    const res = await axiosInstance.post(`/omr-api/answers/like`, {
      answerId: answerId,
    });

    return res;
  } catch (e) {
    console.error(e);
  }
};

export const getSampleAnswer = async (question: string) => {
  try {
    const res = await axiosInstance.post(`/omr-chatbot/chat`, {
      question: question,
    });

    return res;
  } catch (e) {
    console.error(e);
  }
};
