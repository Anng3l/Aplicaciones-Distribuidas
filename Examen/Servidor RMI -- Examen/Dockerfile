# Usa una imagen base con JDK 24
FROM eclipse-temurin:24-jdk

# Crea un directorio para la app
WORKDIR /app

# Copia tu JAR (ajusta el nombre si es necesario)
COPY servidor-rmi-examen.jar /app/servidor-rmi-examen.jar

# Expone el puerto del registro RMI
EXPOSE 1099 2099

# Comando de inicio: pasa la IP del host desde la variable de entorno
ENTRYPOINT ["sh", "-c", "java -Djava.rmi.server.hostname=$JAVA_RMI_SERVER_HOSTNAME -jar /app/servidor-rmi-examen.jar"]
