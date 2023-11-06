"use client"

import React from 'react';

import Button from '../../components/UI/Button';
import { BLACK } from '@/styles/color';
import { useForm } from 'react-hook-form';
import { EditIcon } from 'public/icons';
import styles from './index.module.scss';

const ProfilePage = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ mode: 'onChange' });

  return (
    <div className={styles.ProfilePage}>
      <div className={styles.profileContainer}>
        <div className={styles.profileContents}>
          <div className={styles.profileEmojiContainer}>
            <div className={styles.emoji}>
              🐰
            </div>
            <div className={styles.editButton}>
              <EditIcon width={20} height={20} fill={BLACK} />
            </div>
          </div>
          <div className={styles.nickname}>
            배고픈 돼지님
          </div>
          

          <div className={styles.profileContent}>
            <label className={styles.label}>
              아이디
            </label>
            <div className={styles.loginId}>
              hungrypig123
            </div>
          </div>

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
                },
              })}
            />
          </div>

          <div className={styles.profileContent}>
            <label htmlFor="passwordValidate" className={styles.label}>
              비밀번호 확인
            </label>
            <input
              id="passwordValidate"
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
                },
              })}
            />
          </div>
        </div>
      </div>

      <div className={styles.btnGroup}>
        <Button
          color="primary"
          size="medium"
          type="submit"
          width="fitContent"
          iconType="complete"
          onClick={handleSubmit(()=>{})}
        >
          저장
        </Button>
      </div>
    </div>
  );
};

export default ProfilePage;
