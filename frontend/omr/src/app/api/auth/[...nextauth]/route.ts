import NextAuth from 'next-auth/next';
import CredentialsProvider from 'next-auth/providers/credentials';

import { login } from '@/service/auth';

const handler = NextAuth({
  providers: [
    CredentialsProvider({
      id: 'credentials',
      name: 'Credentials',
      credentials: {
        loginId: {
          label: 'loginId',
          type: 'text',
          placeholder: '아이디를 입력하세요.',
        },
        password: { label: 'password', type: 'password' },
      },
      async authorize(credentials, req) {
        const res = await login({
          loginId: credentials?.loginId!,
          password: credentials?.password!,
        });

        const data = res.data.data;
        const user = {
          id: credentials?.loginId as string,
          accessToken: data.accessToken,
          refreshToken: data.refreshToken,
        };

        if (res.status === 200) {
          return user;
        } else {
          // eslint-disable-next-line no-null/no-null
          return null;
        }
      },
    }),
  ],
  callbacks: {
    async jwt({ user, token }) {
      if (user) {
        token.accessToken = user.accessToken;
        token.refreshToken = user.refreshToken;
      }

      return token;
    },
    async session({ token, session }) {
      session.user.accessToken = token.accessToken as string;
      session.user.refreshToken = token.refreshToken as string;

      return session;
    },
  },
  pages: {
    signIn: '/login',
  },
});

export { handler as GET, handler as POST };
