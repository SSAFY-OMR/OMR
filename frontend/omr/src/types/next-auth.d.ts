import { JWT } from 'next-auth/jwt';

import type { DefaultSession } from 'next-auth';

declare module 'next-auth' {
  interface Session {
    user: DefaultSession['user'] & {
      accessToken: string;
      refreshToken: string;
    };
  }

  interface User {
    accessToken: any & DefaultSession['user'];
    refreshToken: any & DefaultSession['user'];
  }
}

declare module 'next-auth/jwt' {
  interface JWT {
    user: {
      accessToken: string;
      refreshToken: string;
    };
  }
}
