import React from 'react';

import type { Metadata } from 'next';

import Providers from '@/components/Providers';
import Header from '@/components/UI/Header';
import { pretendard } from '@/styles/font';

import './global.scss';

export const metadata: Metadata = {
  title: 'OMR',
  description: 'OMR:오늘의 문제를 알려줘',
  manifest: '/manifest.json',
  icons: {
    icon: '/favicon.ico',
    apple: './apple-touch-icon.png',
  },
  themeColor: '#18b3e7',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en" className={pretendard.className}>
      <body className={pretendard.className} suppressHydrationWarning={true}>
        <Providers>
          <div id="rootDiv" className="fixed-width">
            <Header />
            {children}
          </div>
        </Providers>
      </body>
    </html>
  );
}
