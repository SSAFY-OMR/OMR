import React from 'react';

import type { Metadata } from 'next';

import RecoilRootProvider from '@/components/RecoilRootProvider';
import Footer from '@/components/UI/Footer';
import Header from '@/components/UI/Header';
import { NEUTRAL_20 } from '@/styles/color';
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
  themeColor: NEUTRAL_20,
  appleWebApp: {
    capable: true,
  },
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en" className={pretendard.className}>
      <body className={pretendard.className} suppressHydrationWarning={true}>
        <RecoilRootProvider>
          <div id="rootDiv" className="fixed-width">
            <Header />
            {children}
            <div id="portal" />
            <Footer />
          </div>
        </RecoilRootProvider>
      </body>
    </html>
  );
}
