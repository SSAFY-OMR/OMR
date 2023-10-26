import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { FieldValues } from 'react-hook-form';

import styles from './index.module.scss';
import Button from '../UI/Button';
import FeedbackMessage from '../FeedbackMessage';

type FormValue = {
    loginId: string;
    password: string;
}

const handleLogin = async (data : FieldValues) => {

};

const LoginForm = () => {

    const {
        register,
        handleSubmit,
        formState: { errors },
      } = useForm<FormValue>({ mode: "onChange" });

    const [isLoginTried, setIsLoginTried] = useState(false);
    const [isLoginSucceed, setIsLoginSucceed] = useState(false);

    return (
        <form>
            <div className={styles.loginInputs}>
                <div className={styles.loginInput}>
                    <label htmlFor="loginId" className={styles.loginInputTitle}>아이디</label>
                    <input
                        id="loginId"
                        type="text"
                        placeholder="아이디를 입력하세요."
                        className={styles.loginInputBox}
                        // input의 기본 config를 작성
                        {...register("loginId", {
                        pattern: {
                            value: /^[a-z]+[a-z0-9]{8,16}$/,
                            message: "올바르지 않은 아이디 형식입니다.",
                        },
                        })}
                    />
                    {errors.loginId && (
                        <FeedbackMessage type="error">
                            {errors.loginId.message}
                        </FeedbackMessage>
                    )}
                </div>
                <div className={styles.loginInput}>
                    <label htmlFor="password" className={styles.loginInputTitle}>비밀번호</label>
                    <input
                    id="password"
                    type="password"
                    placeholder="비밀번호를 입력하세요."
                    className={styles.loginInputBox}
                    {...register("password", {
                        pattern: {
                            value: /^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$/,
                            message: "올바르지 않은 비밀번호 형식입니다.",
                        },
                    })}
                    />
                    {isLoginTried && !isLoginSucceed && (
                    <FeedbackMessage type="error">
                        아이디 또는 비밀번호가 일치하지 않습니다.
                    </FeedbackMessage>
                    )}
                </div>
            </div>
            <Button
                color="primary"
                size="medium"
                type="submit"
                width="full"
                iconType='complete'
                onClick={handleSubmit(handleLogin)}
            >
                로그인
            </Button>
      </form>
    )
}

export default LoginForm;