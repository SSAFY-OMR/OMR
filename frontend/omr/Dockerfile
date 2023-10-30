FROM node:18.18.0-alpine
RUN mkdir -p /frontend
WORKDIR /frontend

COPY ./ ./

RUN npm i
RUN npm run build
EXPOSE 3000
CMD ["npm", "start"]
