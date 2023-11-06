"use client"

import React, { useEffect, useState } from 'react';

import { useForm } from 'react-hook-form';

import { getUserInfo, updateUserPassword } from '@/service/member';
import { BLACK } from '@/styles/color';
import { EditIcon } from 'public/icons';

import styles from './index.module.scss';
import FeedbackMessage from '../../components/FeedbackMessage';
import Button from '../../components/UI/Button';

import type { User } from '@/types/user';
import type { FieldValues} from 'react-hook-form';

const ProfilePage = () => {
  const [user, setUser] = useState<User | undefined>(undefined);

  const {
    register,
    watch,
    handleSubmit,
    formState: { errors },
  } = useForm({ mode: 'onChange' });
  
  const handleSaveButton = async (data: FieldValues) => {
    console.log(data);
    const res = await updateUserPassword(data.password);

    if (res?.status === 200) {
      console.log("success");
    } else {
      console.log("fail");
    }
  };

  useEffect(() => {
    (async () => {
      const res = await getUserInfo();
      if (res?.status === 200) {
        const user = res.data.data;
        setUser(user);
      }
    })();
  }, []);

  return (
    <div className={styles.ProfilePage}>
      <div className={styles.profileContainer}>
        <div className={styles.profileContents}>
          {/* 사용자 정보 바인딩  */}
          <div className={styles.profileEmojiContainer}>
            <div className={styles.emoji}>
              {user?.emoji}
            </div>
            <div className={styles.editButton}>
              <EditIcon width={20} height={20} fill={BLACK} />
            </div>
          </div>
          <div className={styles.nickname}>
            {user?.nickname}
          </div>
          
          <div className={styles.profileContent}>
            <label className={styles.label}>
              아이디
            </label>
            <div className={styles.loginId}>
              {user?.loginId}
            </div>
          </div>
        </div>

        {/* 패스워드 변경 폼 */}
        <form className={styles.updatePasswordForm}>
          <div className={styles.profileContent}>
            <label htmlFor="password" className={styles.label}>
              비밀번호 변경
            </label>
            <input
              id="password"
              type="password"
              placeholder="비밀번호를 입력하세요."
              className={styles.labelContent}
              {...register('password', {
                required: '비밀번호를 입력해주세요.',
                pattern: {
                  value: /^[A-Za-z0-9]+$/,
                  message: '비밀번호는 영문과 숫자로 구성되어야 합니다.',
                },
                minLength: {
                  value: 8,
                  message: '비밀번호는 8자 이상이어야 합니다.',
                },
                maxLength: {
                  value: 16,
                  message: '비밀번호는 16자 이하여야 합니다.',
                }
              })}
            />
          </div>
          <div className="validateMessage">
            {errors.password && (
              <FeedbackMessage type="error">
                {errors.password.message?.toString()}
              </FeedbackMessage>
            )}
          </div>
          
          <div className={styles.profileContent}>
            <label htmlFor="passwordConfirm" className={styles.label}>
              비밀번호 확인
            </label>
            <input
              id="passwordConfirm"
              type="password"
              placeholder="비밀번호를 입력하세요."
              className={styles.labelContent}
              {...register('passwordConfirm', {
                required: '비밀번호를 확인해주세요.'
              })}
            />
          </div>
          <div className="validateMessage">
            { (watch("password") !== watch("passwordConfirm")) &&
              <FeedbackMessage type="error">
                비밀번호가 일치하지 않습니다.
              </FeedbackMessage>
            }
          </div>

          <div className={styles.btnGroup}>
            <Button
              color="primary"
              size="medium"
              type="submit"
              width="fitContent"
              iconType="complete"
              onClick={handleSubmit(handleSaveButton)}
            >
              저장
            </Button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ProfilePage;
