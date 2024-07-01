FROM openjdk:21-slim

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR da aplicação para o contêiner
COPY target/HealthCareApp.jar /app/HealthCareApp.jar

# Exponha a porta em que a aplicação vai rodar
EXPOSE 8080

# Comando para executar a aplicação Java
CMD ["java", "-jar", "HealthCareApp.jar"]