
FROM adoptopenjdk/openjdk11:ubi as build

ADD target/CodeInspect-0.0.1-SNAPSHOT.war CodeInspect-0.0.1-SNAPSHOT.war

ENTRYPOINT ["java", "-jar", "CodeInspect-0.0.1-SNAPSHOT.war"]


# /////////////////////////////////////////////////////////////////////////////////////
# /////////////////////////////////////////////////////////////////////////////////////
# /////////////////////////////////////////////////////////////////////////////////////
# /////////////////////////////////////////////////////////////////////////////////////
# /////////////////////////////////////////////////////////////////////////////////////

# #### Stage 1: Build the application
# FROM adoptopenjdk/openjdk11:ubi as build

# # Set the current working directory inside the image
# WORKDIR /app

# # Copy maven executable to the image
# COPY mvnw .
# COPY .mvn .mvn

# # Copy the pom.xml file
# COPY pom.xml .

# # Build all the dependencies in preparation to go offline.
# # This is a separate step so the dependencies will be cached unless
# # the pom.xml file has changed.
# RUN ./mvnw dependency:go-offline -B

# # Copy the project source
# COPY . .

# # Package the application
# RUN ./mvnw package
# RUN mkdir -p target/dependency
# RUN java -jar /app/target/CodeInspect-0.0.1-SNAPSHOT.war

# #RUN jar -xf ../*.jar


# #### Stage 2: A minimal docker image with command to run the app
# FROM adoptopenjdk/openjdk11:ubi

# ARG DEPENDENCY=/app/target/dependency

# # Copy project dependencies from the build stage
# COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
# COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app


# #CMD ["python"]
# # ENTRYPOINT ["java","-cp","app:app/lib/*","io.github.vahansahakyan.CodeInspectApplication"]
# ENTRYPOINT ["java","-cp","app:app/lib/*:app/target/*","io.github.vahansahakyan.CodeInspectApplication"]