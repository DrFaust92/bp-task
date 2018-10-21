FROM maven:3.5 as inter

WORKDIR /app

ADD pom.xml /app/pom.xml

RUN mvn verify clean --fail-never


ADD src /app/src
ADD assembly.xml /app/assembly.xml



RUN mvn package
RUN echo $(ls -1l)


FROM openjdk:8-jre

COPY --from=inter /app/target /target
WORKDIR /target/release
RUN ls -ll

CMD java -jar test-proj-1.0-SNAPSHOT.jar
