# Build environment
FROM eclipse-temurin:22-jdk-jammy AS gradle-cache
WORKDIR /gradle-cache

# Download and install Gradle
RUN apt-get update && \
    apt-get install -y --no-install-recommends curl unzip && \
    curl -L https://services.gradle.org/distributions/gradle-8.8-bin.zip -o gradle.zip && \
    unzip gradle.zip && \
    rm gradle.zip && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

ENV PATH=$PATH:/gradle-cache/gradle-8.8/bin

# Build stage
FROM gradle-cache AS build
WORKDIR /app

# Copy only the necessary files for dependency resolution
COPY build.gradle.kts settings.gradle.kts gradle.properties ./

# Download dependencies (this layer will be cached if the gradle files don't change)
RUN #gradle --no-daemon dependencies

# Copy the rest of the project files
COPY . .

# Build the WASM target
RUN gradle --no-daemon wasmJsBrowserProductionWebpack

# Production stage
FROM nginx:alpine
COPY --from=build /app/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 3001
CMD ["nginx", "-g", "daemon off;"]