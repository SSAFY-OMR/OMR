const prod = process.env.NODE_ENV === 'production';

const runtimeCaching = require('next-pwa/cache');

const withPWA = require('next-pwa')({
  dest: 'public',
  register: true,
  skipWaiting: true,
  customWorkerDir: 'worker',
  runtimeCaching,
  disable: prod ? false : true,
});

const nextConfig = withPWA({
  // next config
  eslint: {
    ignoreDuringBuilds: true,
  },
  reactStrictMode: true,
  webpack(config) {
    config.module.rules.push({
      test: /\.svg$/,
      use: ['@svgr/webpack'],
    });

    return config;
  },
});

module.exports = nextConfig;
