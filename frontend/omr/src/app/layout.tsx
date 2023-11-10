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
  applicationName: 'OMR',
  description: 'OMR:오늘의 문제를 알려줘',
  manifest: '/manifest.json',
  icons: {
    icon: '/favicon.ico',
    apple: './apple-touch-icon.png',
  },
  themeColor: NEUTRAL_20,
  appleWebApp: {
    capable: true,
    title: 'OMR',
    startupImage: [
      {
        media:
          '(device-width: 414px) and (device-height: 736px) and (-webkit-device-pixel-ratio: 3)',
        url: '../../public/images/iPhone_8_Plus__iPhone_7_Plus__iPhone_6s_Plus__iPhone_6_Plus_portrait.png',
      },
      {
        media:
          '(device-width: 375px) and (device-height: 667px) and (-webkit-device-pixel-ratio: 2)',
        url: '../../public/images/iPhone_8__iPhone_7__iPhone_6s__iPhone_6__4.7__iPhone_SE_portrait.png',
      },
      {
        media:
          '(device-width: 414px) and (device-height: 896px) and (-webkit-device-pixel-ratio: 2)',
        url: '../../public/images/iPhone_11__iPhone_XR_portrait.png',
      },
      {
        media:
          '(device-width: 414px) and (device-height: 896px) and (-webkit-device-pixel-ratio: 3)',
        url: '../../public/images/iPhone_11_Pro_Max__iPhone_XS_Max_portrait.png',
      },
      {
        media:
          '(device-width: 375px) and (device-height: 812px) and (-webkit-device-pixel-ratio: 3)',
        url: '../../public/images/iPhone_13_mini__iPhone_12_mini__iPhone_11_Pro__iPhone_XS__iPhone_X_portrait.png',
      },
      {
        media:
          '(device-width: 390px) and (device-height: 844px) and (-webkit-device-pixel-ratio: 3)',
        url: '../../public/images/iPhone_14__iPhone_13_Pro__iPhone_13__iPhone_12_Pro__iPhone_12_portrait.png',
      },
      {
        media:
          '(device-width: 428px) and (device-height: 926px) and (-webkit-device-pixel-ratio: 3)',
        url: '../../public/images/iPhone_14_Plus__iPhone_13_Pro_Max__iPhone_12_Pro_Max_portrait.png',
      },
      {
        media:
          '(device-width: 393px) and (device-height: 852px) and (-webkit-device-pixel-ratio: 3)',
        url: '../../public/images/iPhone_15_Pro__iPhone_15__iPhone_14_Pro_portrait.png',
      },
      {
        media:
          '(device-width: 430px) and (device-height: 932px) and (-webkit-device-pixel-ratio: 3)',
        url: '../../public/images/iPhone_15_Pro_Max__iPhone_15_Plus__iPhone_14_Pro_Max_portrait.png',
      },
    ],
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
