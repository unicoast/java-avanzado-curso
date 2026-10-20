# Etapa de construcción: compilar la aplicación con JDK
FROM eclipse-temurin:21-jdk AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todos los archivos del proyecto al contenedor
COPY . .

# Ejecuta Maven para limpiar y compilar el proyecto, generando el .jar (sin tests)
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución: usar solo JRE para correr la aplicación en producción
FROM eclipse-temurin:21-jre

# Establece el directorio de trabajo en esta nueva imagen
WORKDIR /app

# Copia el archivo .jar generado en la etapa anterior al directorio actual y lo renombra
COPY --from=build /app/target/*.jar cv.jar

# Expone el puerto 8080 para acceder a la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "cv.jar"]