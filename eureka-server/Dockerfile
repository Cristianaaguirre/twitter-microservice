ARG MSVC_NAME=eureka-server

FROM eclipse-temurin:11.0.16_8-jdk as builder

ARG MSVC_NAME

WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:11.0.16_8-jre
WORKDIR /app

ARG MSVC_NAME
ARG TARGET_FOLDER=/app/$MSVC_NAME/target

#Los arguments son durante tiempo de construccion de images
COPY --from=builder $TARGET_FOLDER/eureka-server-0.0.1-SNAPSHOT.jar .
ARG PORT_ARG=8001
ENV PORT $PORT_ARG
EXPOSE $PORT

CMD ["java", "-jar", "eureka-server-0.0.1-SNAPSHOT.jar"]