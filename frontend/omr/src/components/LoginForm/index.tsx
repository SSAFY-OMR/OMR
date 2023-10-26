import React from 'react';
import { useForm } from 'react-hook-form';

import styles from './index.module.scss';
import Button from '../UI/Button';

type FormValue = {
    loginId: string;
    password?: string;
};

const LoginForm = (props: any) => {

    const {
        register,
        handleSubmit,
        formState: { errors },
        getValues,
      } = useForm<FormValue>({ mode: "onChange" });

    return (
        <form onSubmit={handleSubmit(props.onSubmit)}>
            <div className={styles.loginInputBox}>
            <label htmlFor="loginId" className={styles.loginInputTitle}>이메일</label>
            <input
                id="loginId"
                type="text"
                placeholder="아이디를 입력하세요."
                // input의 기본 config를 작성
                {...register("loginId", {
                pattern: {
                    value:
                    /^[a-z]+[a-z0-9]{8,16}$/,
                    message: "올바르지 않은 아이디 형식입니다.",
                },
                })}
            />
            {errors.loginId && <small role="alert">{errors.loginId.message}</small>}
            </div>
            <div className={styles.loginInputBox}>
                <label htmlFor="password" className={styles.loginInputTitle}>비밀번호</label>
                <input
                id="password"
                type="password"
                placeholder="비밀번호를 입력하세요."
                {...register("password", {
                    pattern: {
                        value: /^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$/,
                        message: "올바르지 않은 비밀번호 형식입니다.",
                    },
                })}
                />
                {errors.password && (
                <small role="alert">{errors.password.message}</small>
                )}
            </div>
            <Button color="primary" size="small" type="edit" width="full">
                로그인
            </Button>
      </form>
    )
}

export default LoginForm;