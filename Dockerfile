# il faut generer le fichier jar ./mvnw clean package -Dskiptests
FROM openjdk:21-oracle
VOLUME /tmp
COPY target/MonBeauJardinBackV2-0.0.1-SNAPSHOT.jar app.jar
#pour la mise en prod dans ec2
COPY MonBeauJardinBackV2-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8074
ENTRYPOINT ["java", "-jar", "app.jar"]