FROM ubuntu:bionic as server-build
RUN apt update && apt install -y chromium-browser openjdk-11-jre-headless curl && apt clean
RUN ln -s /usr/bin/chromium-browser /usr/bin/google-chrome
RUN curl -fsSL https://deb.nodesource.com/setup_14.x | bash - && apt install -y nodejs


WORKDIR /app

COPY .mvn ./.mvn
COPY mvnw ./

RUN ls -R . && ./mvnw --version

COPY . ./
#Build application
RUN --mount=type=cache,target=/root/.m2 --mount=type=cache,target=node_modules ./mvnw verify -P it,production
#Split fat jar into layers
RUN cd target && java -Djarmode=layertools -jar application.jar extract


# The final image - Bellsoft alpine OpenJDK images are the smallest
FROM bellsoft/liberica-openjdk-alpine:11 as final
RUN adduser -S user

WORKDIR /app

COPY --from=server-build /app/target/dependencies/ ./
COPY --from=server-build /app/target/snapshot-dependencies/ ./
COPY --from=server-build /app/target/spring-boot-loader/ ./
COPY --from=server-build /app/target/application/ ./

# Run under non-privileged user with minimal write permissions
#USER user

CMD java org.springframework.boot.loader.JarLauncher

# Heroku redefines exposed port
ENV PORT=8080
EXPOSE $PORT
