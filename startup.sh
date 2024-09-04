#!/bin/bash

# Wait for the application to be ready
timeout=900
start_time=$(date +%s)

echo "Waiting for application to be ready..."
while ! curl -s http://localhost:3001 > /dev/null; do
    current_time=$(date +%s)
    elapsed=$((current_time - start_time))

    if [ $elapsed -ge $timeout ]; then
        echo "Timeout waiting for application to start"
        exit 1
    fi

    echo "Application not ready, waiting..."
    sleep 5
done

echo "Application is ready, starting Nginx"
exec nginx -g 'daemon off;'