# 阶段1：构建 jar 包
FROM docker.m.daocloud.io/library/maven:3.8.5-openjdk-8-slim AS builder
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

# 阶段2：运行 jar 包
FROM docker.m.daocloud.io/library/openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /build/target/book-user.jar app.jar
EXPOSE 9002
ENTRYPOINT ["java", "-jar", "app.jar"]
