# Use Eclipse Temurin JDK 22 as the base image
FROM eclipse-temurin:22-jdk-jammy as build

# Set the working directory in the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Install necessary dependencies (if any)
RUN apt-get update && apt-get install -y curl unzip

# Download and install Gradle 8.8
RUN curl -L https://services.gradle.org/distributions/gradle-8.8-bin.zip -o gradle.zip
RUN unzip gradle.zip && rm gradle.zip
ENV PATH=$PATH:/app/gradle-8.8/bin

# Build the WASM target
RUN gradle wasmJsBrowserProductionWebpack

# Use a lightweight web server to serve the WASM content
FROM nginx:alpine

# Copy the built files from the previous stage
COPY --from=build /app/composeApp/build/dist/wasmJs/productionExecutable/ /usr/share/nginx/html

# Copy the custom Nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 3001
EXPOSE 3001

# The default command will start Nginx
CMD ["nginx", "-g", "daemon off;"]