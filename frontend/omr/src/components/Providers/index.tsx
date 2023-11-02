'use client';

import type { ReactNode } from 'react';
import React from 'react';

import { SessionProvider } from 'next-auth/react';

interface Props {
  children: ReactNode;
}
function Providers({ children }: Props) {
  return <SessionProvider>{children}</SessionProvider>;
}

export default Providers;
