FROM amazoncorretto:21.0.4-alpine
LABEL maintainer-name="Rafael Pereira"
LABEL maintainer-email="rafael.informa@gmail.com"
WORKDIR /app
COPY target/*.jar /app/app.jar
EXPOSE 25000
CMD java -jar app.jar