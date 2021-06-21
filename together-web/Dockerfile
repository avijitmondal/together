# Build stage
FROM node:current-alpine3.13 AS build
LABEL maintainer="avijitmondal38@gmail.com"

# Copying source code
WORKDIR /app
ADD package.json package.json
ADD src src/
ADD public public/
ADD nginx nginx/

# Prepare by downloading dependencies main module
RUN npm install
RUN npm run build --prod

# Deployment stage
FROM nginx:1.19-alpine
LABEL maintainer="avijitmondal38@gmail.com"

COPY --from=build /app/public /usr/share/nginx/html
COPY --from=build /app/nginx/conf.d/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
