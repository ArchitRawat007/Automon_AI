
*****Author: Archit Rawat****
AI Based log monitoring system, automon.

Components:
1. Websockets:
WebSockets enable real-time communication between the server and clients. Unlike HTTP, which follows a request-response model, WebSockets allow bi-directional, persistent connections.

In AutoMon AI, WebSockets will: 
-Stream real-time system metrics (CPU, memory usage, etc.).
- Push alerts instantly when an anomaly is detected.
-Reduce network overhead by avoiding frequent polling.

2. Redis Cache
Redis will help cache system metrics, so we can: 
- Reduce database queries for frequently accessed data.
-Improve API response time for real-time monitoring.
-Store and retrieve system metrics efficiently.

