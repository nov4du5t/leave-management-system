# Stage 1: Build the application
FROM maven:3.8.4-openjdk-11 AS builder

WORKDIR /app

# Copy the entire project
COPY . .

# Build the project with Maven
RUN mvn clean install -DskipTests

# Stage 2: Runtime
FROM tomcat:9.0-jdk11-openjdk-slim

# Install PostgreSQL client
RUN apt-get update && apt-get install -y postgresql-client && rm -rf /var/lib/apt/lists/*

# Copy the built war file from builder stage
COPY --from=builder /app/target/leave-management-system-1.0.war /usr/local/tomcat/webapps/ROOT.war

# Copy database initialization script
COPY src/main/resources/app-DB-Script.sql /docker-entrypoint-initdb.d/

# Create startup script
RUN echo '#!/bin/bash\n\
set -e\n\
\n\
# Start Tomcat in background\n\
catalina.sh start\n\
\n\
# Wait for Tomcat to start\n\
sleep 10\n\
\n\
# Keep container running\n\
tail -f /usr/local/tomcat/logs/catalina.out' > /usr/local/tomcat/bin/startup.sh && \
chmod +x /usr/local/tomcat/bin/startup.sh

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
