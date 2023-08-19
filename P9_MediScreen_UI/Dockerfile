# Étape de construction
FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app

# Copier les fichiers de configuration du projet
COPY pom.xml .
COPY src ./src

# Construire le projet Maven
RUN mvn clean package -DskipTests

# Utiliser une image de base Java
FROM openjdk:17-slim

# Définir le répertoire de travail dans l'image
WORKDIR /app

# Copier le fichier JAR de l'application dans l'image
COPY --from=build /app/target/*.jar ./app.jar

# Exposer le port sur lequel l'application Spring Boot écoute
EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=production

# Commande pour exécuter l'application lorsque le conteneur démarre
CMD ["java", "-jar", "app.jar"]
