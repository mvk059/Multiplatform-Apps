# Start with a base image that includes Java 17
FROM eclipse-temurin:17-jdk

# Install necessary tools
RUN apt-get update && apt-get install -y curl unzip

# Install Gradle
ENV GRADLE_VERSION=8.4
RUN curl -L https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip \
    && unzip gradle.zip \
    && rm gradle.zip \
    && mv gradle-${GRADLE_VERSION} /opt/gradle
ENV PATH=$PATH:/opt/gradle/bin

# Set the working directory in the container
WORKDIR /

# Copy the project files into the container
COPY . .

# Run the Gradle task
RUN chmod +x ./gradlew
RUN ./gradlew wasmJsBrowserProductionWebpack
RUN ./gradlew wasmJsBrowserProductionRun

# Set the output directory as a volume
VOLUME /composeApp/build/dist/wasmJs/productionExecutable/

# Command to run when the container starts
CMD ["sh", "-c", "echo 'Build completed. Output is in /composeApp/build/dist/wasmJs/productionExecutable/'"]